package com.hospital.hospitalapi.controller;
import com.hospital.hospitalapi.model.Patient;
import com.hospital.hospitalapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PatientController {

  private PatientService patientService;

  @Autowired
    public void setPatientService(PatientService patientService){
      this.patientService = patientService;
  }

  @GetMapping("/patients/")
    public List<Patient> getAllPatients(){
      return patientService.getAllPatients();
  }

  @PostMapping("/patients/")
    public Patient createPatient(@RequestBody Patient patientObject){
      return patientService.createPatient(patientObject);
  }

  @DeleteMapping("/patients/{patientId}")
  public String deletePatient(@PathVariable(value = "patientId") Long patientId){
    return patientService.deletePatient(patientId);
  }
  @PutMapping("/patients/{patientId}")
  public Patient updatePatient(@PathVariable(value = "patientId") Long patientId, @RequestBody Patient patientObject){
    return patientService.updatePatient(patientId, patientObject);
  }

}
