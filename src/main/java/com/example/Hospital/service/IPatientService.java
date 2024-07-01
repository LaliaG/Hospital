package com.example.Hospital.service;

import com.example.Hospital.model.Patient;

import java.util.List;

public interface IPatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    List<Patient> searchPatients(String search);
    Patient updatePatient(Long id, Patient updatePatient);
    void deletePatient(Long id);
}
