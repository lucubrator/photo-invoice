package com.example.photoinvoice.backend.controllers;

import com.example.photoinvoice.backend.entities.Job;
import com.example.photoinvoice.backend.services.JobService;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public Job saveJob(@Valid @RequestBody Job job) {
        return jobService.saveJob(job);
    }
}
