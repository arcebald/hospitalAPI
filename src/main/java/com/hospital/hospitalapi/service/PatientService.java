package com.hospital.hospitalapi.service;

import com.hospital.hospitalapi.exceptions.InformationExistsException;
import com.hospital.hospitalapi.exceptions.InformationNotFoundException;
import com.hospital.hospitalapi.model.Patient;
import com.hospital.hospitalapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(){
        List<Patient> patient = patientRepository.findAll();
        if(patient.isEmpty()){
            throw new InformationNotFoundException("no patient found in the table");
        }else{
            return patient;
        }
    }

    public Patient createPatient(Patient patientObject) {
        Patient patient = patientRepository.findByName(patientObject.getName());
        if(patient != null){
            throw new InformationExistsException("patient with name " + patient.getName() + " already exists");
        }else{
            return patientRepository.save(patientObject);
        }
    }

    public String deletePatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException("patient with id " + patientId+ " not found");
        }else{
            patientRepository.deleteById(patientId);
            return "patient with id " + patientId +" has been succesfully deleted";
        }
    }

    public Patient updatePatient(Long patientId, Patient patientObject) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException(" patient with id " + patientId + " not found");
        }else{
            patient.get().setName(patientObject.getName());
            patient.get().setDateAdmited(patientObject.getDateAdmited());
            patient.get().setInsurance(patientObject.getInsurance());
            return patientRepository.save(patient.get());
        }
    }
}

