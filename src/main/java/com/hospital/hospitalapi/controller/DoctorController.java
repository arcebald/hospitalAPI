package com.hospital.hospitalapi.controller;

import com.hospital.hospitalapi.model.Doctor;
import com.hospital.hospitalapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public void setDoctorService(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    @PostMapping("/registerdoctor/")
    public Doctor createDoctor(@RequestBody Doctor doctorObject){
        return doctorService.createDoctor(doctorObject);
    }
}
