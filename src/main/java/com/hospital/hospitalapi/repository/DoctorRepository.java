package com.hospital.hospitalapi.repository;

import com.hospital.hospitalapi.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByDoctorName(String doctorName);

    Doctor findDoctorByDoctorName(String name);
}
