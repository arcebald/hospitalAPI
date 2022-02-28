package com.hospital.hospitalapi.service;

import com.hospital.hospitalapi.exceptions.InformationExistsException;
import com.hospital.hospitalapi.exceptions.InformationNotFoundException;
import com.hospital.hospitalapi.model.Doctor;
import com.hospital.hospitalapi.model.Patient;
import com.hospital.hospitalapi.model.PatientTest;
import com.hospital.hospitalapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository){ this.doctorRepository = doctorRepository;}

    public Doctor createDoctor(Doctor doctorObject){
        if(!doctorRepository.existsByDoctorName(doctorObject.getDoctorName())){
            return doctorRepository.save(doctorObject);
        }else{
            throw new InformationExistsException("doctor with name" + doctorObject.getDoctorName() + " already exists");
        }
    }

    public Doctor findDoctorByName(String name){
        return doctorRepository.findDoctorByDoctorName(name);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctor = doctorRepository.findAll();

        if(doctor.isEmpty()){
            throw new InformationNotFoundException("no doctor found");

        }else{
            return doctor;
        }
    }
    public String deleteDoctor(Long doctorId){
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isEmpty()){
            throw new InformationNotFoundException("doctor with id " + doctorId + " does not exist");
        }else{
            doctorRepository.deleteById(doctorId);
            return "doctor with id " + doctorId + " has been successfully deleted";
        }
    }

    public Doctor updateDoctor(Long doctorId, Doctor doctorObject) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isEmpty()){
            throw new InformationNotFoundException("doctor with id " + doctorId + " not found");
        }else{
            doctor.get().setDoctorName(doctorObject.getDoctorName());
            doctor.get().setSpecialization(doctorObject.getSpecialization());
            return doctorRepository.save(doctor.get());
        }
    }

    public List<Patient> getDoctorPatients(Long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isEmpty()){
            throw new InformationNotFoundException("doctor with id " + doctorId+ " does not exist");

        }
        return doctor.get().getPatientList();
    }
}
