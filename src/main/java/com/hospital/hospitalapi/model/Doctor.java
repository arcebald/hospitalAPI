package com.hospital.hospitalapi.model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;

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

    @OneToMany(mappedBy = "test");
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Test> testList;

}
