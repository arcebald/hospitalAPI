package com.hospital.hospitalapi.controller;

import com.hospital.hospitalapi.model.Doctor;
import com.hospital.hospitalapi.model.Patient;
import com.hospital.hospitalapi.model.PatientTest;
import com.hospital.hospitalapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public void setDoctorService(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping(path = "/hello-world/")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/doctors/")
    public List<Doctor> getAllDoctors() {
        System.out.println("calling getAllDoctors");
        return doctorService.getAllDoctors();
    }


    @PostMapping("/doctors/register/")
    public Doctor createDoctor(@RequestBody Doctor doctorObject){
        return doctorService.createDoctor(doctorObject);
    }

    @DeleteMapping("/doctors/{doctorId}")
    public String deleteDoctor(@PathVariable(value = "doctorId") Long doctorId){
        return doctorService.deleteDoctor(doctorId);
    }

    @PutMapping("/doctors/{doctorId}")
    public Doctor updateDoctor(@PathVariable(value = "doctorId") Long doctorId, @RequestBody Doctor doctorObject ){
        return doctorService.updateDoctor(doctorId, doctorObject);
    }

    @GetMapping("/doctors/{doctorId}/patients/")
    public List<Patient> getDoctorPatients(@PathVariable(value = "doctorId") Long doctorId){
        return doctorService.getDoctorPatients(doctorId);
    }

}
