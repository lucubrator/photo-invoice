
$initForeground = (Get-Host).UI.RawUI.ForegroundColor
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

# Navigate to the script's directory
$initDir = (Get-Location).Path
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $scriptDir

if (-not ($initDir -eq (Get-Location).Path)) {
    Write-Host "[full-rebuild] Navigated from '$initDir' to script directory '$scriptDir'."
} else {
    Write-Host "[full-rebuild] Script is run from the root project directory ('$scriptDir')."
}

Write-Host "[full-rebuild] Stopping Docker containers..."
docker-compose down --volumes --remove-orphans
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

Write-Host "[full-rebuild] Cleaning Docker images..."
$imageIds = docker images -q photo-invoice-backend
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"
if ($imageIds) {
    docker rmi $imageIds -f
    Write-Host "[full-rebuild] Removed images for 'photo-invoice-backend'."
} else {
    Write-Host "[full-rebuild] No images found for 'photo-invoice-backend'."
}
docker image prune -f # Remove dangling images
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

Write-Host "[full-rebuild] Listing any Docker images left..."
docker images -a
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

Write-Host "[full-rebuild] Performing a clean Gradle build with scan..."
# Define the Gradle wrapper path
$gradlewPath = ".\backend\gradlew.bat"
if (-not (Test-Path $gradlewPath)) {
    Write-Error "[full-rebuild] Gradle wrapper (gradlew.bat) not found at '$gradlewPath'. Exiting..."
    exit 1
}

# Run Gradle build
Push-Location ./backend
& Write-Output "yes" | ./gradlew clean build --refresh-dependencies --scan
$buildExitCode = $LASTEXITCODE
Pop-Location

if ($buildExitCode -eq 0) {
    Write-Host "[full-rebuild] Gradle build successful. Rebuilding Docker containers..."
    docker-compose up --build --attach-dependencies --force-recreate --watch
} else {
    Write-Error "[full-rebuild] Gradle build failed (exit code: $buildExitCode). Please check the logs."
    docker-compose up --build --attach-dependencies --force-recreate --watch
}

# Return to the initial directory
Set-Location $initDir
(Get-Host).UI.RawUI.ForegroundColor = $initForeground
exit $buildExitCode
