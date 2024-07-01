package com.example.Hospital.service;

import com.example.Hospital.model.Consultation;
import com.example.Hospital.model.Patient;

import java.util.List;

public interface IConsultationService {
    Consultation create(Consultation consultation, Patient patient);
    List<Consultation> getAllConsultations();
    List<Consultation> searchConsultations(String search, Patient patient);

    void delete(Long id);
    Consultation getConsultationById(Long id);
    Consultation update(Long id, Consultation consultation, Patient patient);
}
