package com.example.Hospital.service;

import com.example.Hospital.model.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements IPatientService{
    private List<Patient> patients = new ArrayList<Patient>();
    private Long currentId = 1L;

    /*public PatientServiceImpl() {
        Patient patient = Patient.builder()
                .id(currentId++)
                .lastname("DOUMBOUYA")
                .firstname("Fatou Marieme")
                .birthDate(LocalDate.ofEpochDay(17/11/2009))
                .build();

        patients.add(patient);*/

    @Override
    public Patient createPatient(Patient patient) {
        patient.setId(currentId++);
        patients.add(patient);
        return patient;
    }

    @Override
    public Patient getPatientById(Long id) {
        return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patients;
    }

    @Override
    public List<Patient> searchPatients(String search) {
        return patients.stream()
                .filter(patient -> patient.getFirstname().toLowerCase().contains(search.toLowerCase()) ||
                        patient.getLastname().toLowerCase().contains(search.toLowerCase())).toList();
    }

    @Override
    public Patient updatePatient(Long id, Patient updatePatient) {
        Patient patientExist = getPatientById(id);
        if (patientExist != null) {
            patientExist.setLastname(updatePatient.getLastname());
            patientExist.setFirstname(updatePatient.getFirstname());
            patientExist.setBirthDate(updatePatient.getBirthDate());
            patientExist.setConsultations(updatePatient.getConsultations());
        }
        return patientExist;
    }

    @Override
    public void deletePatient(Long id) {
        patients.removeIf(patient -> patient.getId().equals(id));

    }
}
