package com.hospital.hospitalapi.service;

import com.hospital.hospitalapi.exceptions.InformationExistsException;
import com.hospital.hospitalapi.exceptions.InformationNotFoundException;
import com.hospital.hospitalapi.model.Patient;
import com.hospital.hospitalapi.model.PatientTest;
import com.hospital.hospitalapi.repository.PatientRepository;
import com.hospital.hospitalapi.repository.PatientTestRepository;
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

    private PatientTestRepository patientTestRepository;

    @Autowired
    public void setPatientTestRepository(PatientTestRepository patientTestRepository){
        this.patientTestRepository = patientTestRepository;
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
            patient.get().setTestsList(patientObject.getTestsList());
            return patientRepository.save(patient.get());
        }
    }
    public PatientTest createPatientTest(Long patientId, PatientTest testObject){
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException("patient with id" + patientId + " does not exist");
        }
        Optional<PatientTest> patientTest = patientTestRepository.findByName(testObject.getName());
        if(patientTest.isPresent()){
            throw new InformationExistsException("Test with name " + patientTest.get().getName()+ " already exists");
        }
        testObject.setPatient(patient.get());
        return patientTestRepository.save(testObject);
    }

    public List<PatientTest> getPatientTests(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException("patient with id" + patientId+ " does not exist");
        }
        return patient.get().getTestsList();
    }

    public PatientTest getPatientTest(Long patientId, Long testId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException("patient with id "+patientId+ " does not exist");
        }
        Optional<PatientTest> patientTest = patientTestRepository.findByPatientId(patientId).stream().filter(p -> p.getId().equals(testId)).findFirst();
        if(!patient.isPresent()){
            throw new InformationNotFoundException("Test with id " + testId + " does not exist");
        }
        return patientTest.get();
    }

    public PatientTest updatePatientTest(Long patientId, Long testId, PatientTest testObject){
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty()){
            throw new InformationNotFoundException("patient with id " + patientId+ " does not exist");
        }
        Optional<PatientTest> patientTest = patientTestRepository.findById(testId).stream().filter(p -> p.getId().equals(testId)).findFirst();
        if(!patientTest.isPresent()){
            throw new InformationNotFoundException("test with id " + testId + " does not exist");
        }
        Optional<PatientTest> oldTestName = patientTestRepository.findByName(testObject.getName());
        if(!oldTestName.isEmpty()){
            throw new InformationExistsException("test with " + oldTestName+ " already exists");
        }
        patientTest.get().setName(testObject.getName());
        patientTest.get().setTestTime(testObject.getTestTime());
        patientTest.get().setTestDate(testObject.getTestDate());
        patientTest.get().setTestResult(testObject.getTestResult());
        return patientTestRepository.save(patientTest.get());
    }

    public void deletePatientTest(Long patientId, Long testId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
            if(patient.isEmpty()){
                throw new InformationNotFoundException("patient with id " + patientId+ " does not exist");
            }
        Optional<PatientTest> test = patientTestRepository.findById(testId).stream().filter(p -> p.getId().equals(testId)).findFirst();
            if (!test.isPresent()){
                throw new InformationNotFoundException(" test with id "+testId+ " does not exist");
            }
            patientTestRepository.deleteById(test.get().getId());
    }


}

