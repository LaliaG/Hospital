package com.example.Hospital.service;

import com.example.Hospital.model.Consultation;
import com.example.Hospital.model.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationServiceImpl implements IConsultationService {
    private List<Consultation> consultations = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public Consultation create(Consultation consultation, Patient patient) {
        consultation.setId(currentId++);
        consultation.setPatient(patient);
        consultations.add(consultation);
        return consultation;
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultations;
    }

    @Override
    public List<Consultation> searchConsultations(String search, Patient patient) {
        return consultations.stream()
                .filter(consultation -> consultation.getPatientId().toLowerCase().contains(search.toLowerCase()) ||
                        consultation.getNameDoctor().toLowerCase().contains(search.toLowerCase())).toList();
    }

    @Override public void delete(Long id) {
        consultations.removeIf(consultation -> consultation.getId().equals(id));

    }

    @Override
    public Consultation getById(Long id) {
        return consultations.stream().filter(consultation -> consultation.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Consultation update(Long id, Consultation consultation, Patient patient) {
        Consultation consultationExist = getById(id);
        if (consultationExist != null) {
            consultationExist.setDateConsultation(consultation.getDateConsultation());
            consultationExist.setNameDoctor(consultation.getNameDoctor());
            consultationExist.setTreatmentSheets(consultation.getTreatmentSheets());
            consultationExist.setPatient(patient);
        }
        return consultationExist;

    }

}
