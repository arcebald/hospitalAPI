package com.hospital.hospitalapi.repository;

import com.hospital.hospitalapi.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByName(String doctorName);

    Doctor findDoctorByName(String name);
}
