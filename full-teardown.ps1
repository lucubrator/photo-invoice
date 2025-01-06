
$initForeground = (Get-Host).UI.RawUI.ForegroundColor
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

# Navigate to the script's directory
$initDir = (Get-Location).Path
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $scriptDir
$hasMoved = -not ($initDir -eq (Get-Location).Path)

if ($hasMoved) {
    Write-Host "[full-rebuild] Navigated from '$initDir' to script directory '$scriptDir'."
} else {
    Write-Host "[full-rebuild] Script is run from the root project directory '$scriptDir'."
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
Write-Host "[full-rebuild] Removing dangling images..."
#docker image prune -f # Remove dangling images
docker system prune -a -f # Remove all unused images, volumes, networks, and containers
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

Write-Host "[full-rebuild] Listing any Docker images left..."
docker images -a
(Get-Host).UI.RawUI.ForegroundColor = "DarkGreen"

$dockerExitCode = $LASTEXITCODE

# Return to the initial directory
if ($hasMoved) {
    Write-Host "[full-rebuild] Moving back from '$scriptDir' to initial directory '$initDir'..."
}
Set-Location $initDir

if ($dockerExitCode -eq 0) {
    Write-Host "[full-rebuild] Teardown successful. Exiting..."
} else {
    Write-Error "[full-rebuild] Teardown failed (exit code: $buildExitCode). Please check the logs."
}

(Get-Host).UI.RawUI.ForegroundColor = $initForeground
exit $dockerExitCode
