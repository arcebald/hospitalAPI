package com.hospital.hospitalapi.service;

import com.hospital.hospitalapi.exceptions.InformationExistsException;
import com.hospital.hospitalapi.model.Doctor;
import com.hospital.hospitalapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository){ this.doctorRepository = doctorRepository;}

    public Doctor createDoctor(Doctor doctorObject){
        if(!doctorRepository.existsByName(doctorObject.getDoctorName())){
            return doctorRepository.save(doctorObject);
        }else{
            throw new InformationExistsException("doctor with name" + doctorObject.getDoctorName() + " already exists");
        }
    }

    public Doctor findDoctorByName(String name){
        return doctorRepository.findDoctorByName(name);
    }

}
