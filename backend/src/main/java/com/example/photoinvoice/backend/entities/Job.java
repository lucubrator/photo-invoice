package com.example.photoinvoice.backend.entities;

import java.time.LocalDate;

//import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "jobs") // Table name in database
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    @ManyToOne // Relationship: Many jobs can belong to one client
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "photography_date")
    private LocalDate photographyDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "number_of_photos")
    private Integer numberOfPhotos;

    @Column(name = "editor_names")
    private String editorNames;

    @Column(name = "number_of_edited_photos")
    private Integer numberOfEditedPhotos;

    @Column(name = "is_weekend_work")
    private Boolean isWeekendWork;

    @Column(name = "is_night_work")
    private Boolean isNightWork;

    @Column(name = "car_fee")
    private Double carFee;

    @Column(name = "extra_fee")
    private Double extraFee;

    @Column(name = "status")
    private String status; // Enum stored as a string

    public enum JobStatus {
        BOOKED,
        PHOTOS_TAKEN,
        PARTIALLY_EDITED,
        FINISHED,
        CANCELED
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getPhotographyDate() {
        return photographyDate;
    }

    public void setPhotographyDate(LocalDate photographyDate) {
        this.photographyDate = photographyDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getNumberOfPhotos() {
        return numberOfPhotos;
    }

    public void setNumberOfPhotos(Integer numberOfPhotos) {
        this.numberOfPhotos = numberOfPhotos;
    }

    public String getEditorNames() {
        return editorNames;
    }

    public void setEditorNames(String editorNames) {
        this.editorNames = editorNames;
    }

    public Integer getNumberOfEditedPhotos() {
        return numberOfEditedPhotos;
    }

    public void setNumberOfEditedPhotos(Integer numberOfEditedPhotos) {
        this.numberOfEditedPhotos = numberOfEditedPhotos;
    }

    public Boolean getIsWeekendWork() {
        return isWeekendWork;
    }

    public void setIsWeekendWork(Boolean isWeekendWork) {
        this.isWeekendWork = isWeekendWork;
    }

    public Boolean getIsNightWork() {
        return isNightWork;
    }

    public void setIsNightWork(Boolean isNightWork) {
        this.isNightWork = isNightWork;
    }

    public Double getCarFee() {
        return carFee;
    }

    public void setCarFee(Double carFee) {
        this.carFee = carFee;
    }

    public Double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(Double extraFee) {
        this.extraFee = extraFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
