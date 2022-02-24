package com.hospital.hospitalapi.model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String doctorName;

    @Column
    private String specialization;

    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patientList;

    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PatientTest> patientTestList;

    public Doctor() {
    }

    public Doctor(Long id, String doctorName, String specialization, List<Patient> patientList, List<PatientTest> patientTestList) {
        this.id = id;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.patientList = patientList;
        this.patientTestList = patientTestList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<PatientTest> getTestList() {
        return patientTestList;
    }

    public void setTestList(List<PatientTest> patientTestList) {
        this.patientTestList = patientTestList;
    }


}
