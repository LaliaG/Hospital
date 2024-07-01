package com.example.Hospital.controller;

import com.example.Hospital.model.Consultation;
import com.example.Hospital.model.Patient;
import com.example.Hospital.service.ConsultationServiceImpl;
import com.example.Hospital.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
    private final ConsultationServiceImpl consultationService;
    private final PatientServiceImpl patientService;

    @Autowired
    public ConsultationController(ConsultationServiceImpl consultationService, PatientServiceImpl patientService) {
        this.consultationService = consultationService;
        this.patientService = patientService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("consultations", consultationService.getAllConsultations());
        return "consultation-list";
    }

    @GetMapping("/add")
    public String form(Model model){
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("consultation", new Consultation());
        return "consultation-form";
    }

    @PostMapping("/add")
    public String addConsultation(@Valid @ModelAttribute("consultation") Consultation consultation, BindingResult result){
        if(result.hasErrors()) {
            return "patient-form";
        } else {
            Patient patient = patientService.getPatientById(Long.valueOf(consultation.getPatientId()));
            if (consultation.getId() != null) {
                consultationService.update(consultation.getId(), consultation, patient);
            } else {
                consultationService.create(consultation, patient);
                System.out.println(consultation);
            }
        }
        return "redirect:/consultation/list";
    }

    /*
    @PostMapping("/add")
public String addConsultation(@Valid @ModelAttribute("consultation") Consultation consultation, BindingResult result, Model model) {
    if (result.hasErrors()) {
        return "patient-form";
    } else {
        try {
            Long patientId = Long.valueOf(consultation.getPatientId());
            Patient patient = patientService.getPatientById(patientId);
            if (consultation.getId() != null) {
                consultationService.update(consultation.getId(), consultation, patient);
            } else {
                consultationService.create(consultation, patient);
                System.out.println(consultation);
            }
        } catch (NumberFormatException e) {
            result.rejectValue("patientId", "error.consultation", "Invalid patient ID format");
            return "patient-form";
        }
    }
    return "redirect:/consultation/list";
} ou
 @PostMapping("/add")
    public String addConsultation(@Valid @ModelAttribute Consultation consultation, BindingResult result) {
        if (result.hasErrors()) {
            return "consultation-form";
        }
        consultationService.saveConsultation(consultation);
        return "redirect:/patient/detail/" + consultation.getPatient().getId();
    }

    */

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        consultationService.delete(id);
        return "redirect:/consultation/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model){
        Consultation consultation = consultationService.getById(id);
        model.addAttribute("consultation", consultation);
        model.addAttribute("patients", patientService.getAllPatients());
        return "consultation-form";
    }

    @GetMapping("/detail/{id}")
    public String showConsultationDetail(@PathVariable("id") Long id, Model model) {
        Consultation consultation = consultationService.getConsultationById(id);
        model.addAttribute("consultation", consultation);
        return "consultation-detail";
    }
}
