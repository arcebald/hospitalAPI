package com.hospital.hospitalapi.repository;

import com.hospital.hospitalapi.model.PatientTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientTestRepository extends JpaRepository<PatientTest, Long> {
}
