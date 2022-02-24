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
    private List<Test> testList;

    public Doctor() {
    }

    public Doctor(Long id, String doctorName, String specialization, List<Patient> patientList, List<Test> testList) {
        this.id = id;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.patientList = patientList;
        this.testList = testList;
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

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", patientList=" + patientList +
                ", testList=" + testList +
                '}';
    }
}
