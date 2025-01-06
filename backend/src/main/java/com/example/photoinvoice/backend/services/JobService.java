package com.example.photoinvoice.backend.services;

import com.example.photoinvoice.backend.entities.Client;
import com.example.photoinvoice.backend.entities.Job;
import com.example.photoinvoice.backend.repositories.ClientRepository;
import com.example.photoinvoice.backend.repositories.JobRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ClientRepository clientRepository; // Add this

    public JobService(JobRepository jobRepository, ClientRepository clientRepository) {
        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Save a job
    public Job saveJob(Job job) {
        System.out.println("All clients in database: " + clientRepository.findAll());

        if (job.getClient() == null || job.getClient().getId() == null) {
            throw new IllegalArgumentException("Client must be provided with a valid ID!");
        }

        System.out.println("Job received: " + job);

        // Validate the client exists in the database
        Client existingClient = clientRepository.findById(job.getClient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + job.getClient().getId()));

        System.out.println("Client found: " + existingClient);

        // Ensure that all @NotNull fields are present in the database
        if (existingClient.getName() == null || existingClient.getContactPerson() == null) {
            throw new IllegalArgumentException("Client in database is missing required fields (name or contact person)!");
        }

        // Assign the existing client entity to the job to ensure proper persistence with Hibernate
        // Also ensures foreign key constraint is maintained and no new client is created
        job.setClient(existingClient);

        // Save and return the job
        return jobRepository.save(job);
    }

}
