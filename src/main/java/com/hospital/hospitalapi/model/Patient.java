package com.hospital.hospitalapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patients")

public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String insurance;

    @Column
    private String dateAdmited;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    //some comments
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PatientTest> patientTestList;

    public Patient() {
    }

    public Patient(Long id, String name, String insurance, String dateAdmited, Doctor doctor, List<PatientTest> testsList) {
        this.id = id;
        this.name = name;
        this.insurance = insurance;
        this.dateAdmited = dateAdmited;
        this.doctor = doctor;
        this.patientTestList = testsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getDateAdmited() {
        return dateAdmited;
    }

    public void setDateAdmited(String dateAdmited) {
        this.dateAdmited = dateAdmited;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<PatientTest> getTestsList() {
        return patientTestList;
    }

    public void setTestsList(List<PatientTest> testsList) {
        this.patientTestList = testsList;
    }
}
