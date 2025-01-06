package com.example.photoinvoice.backend.repositories;

import com.example.photoinvoice.backend.entities.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    // Additional query methods can go here if needed
}
