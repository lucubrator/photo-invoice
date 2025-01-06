package com.example.photoinvoice.backend.repositories;

import com.example.photoinvoice.backend.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Additional query methods can go here if needed
}
