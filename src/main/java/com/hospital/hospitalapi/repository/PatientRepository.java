package com.hospital.hospitalapi.repository;

import com.hospital.hospitalapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);
}
