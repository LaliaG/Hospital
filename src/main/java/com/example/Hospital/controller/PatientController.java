package com.example.Hospital.controller;

import com.example.Hospital.model.Patient;
import com.example.Hospital.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("patient/")
public class PatientController {
    private final PatientServiceImpl patientService;
    @Autowired
    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/list") // localhost:8080/patient/list
    public String list(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient-list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "patient-form";
        } else {
            if(patient.getId() != null){
                patientService.updatePatient(patient.getId(), patient);
            } else {
                patientService.createPatient(patient);
            }
        }
        return "redirect:/patient/list";
    }

    @GetMapping("/list")
    public String showAllPatients(@RequestParam(name="search", required = false) String search, Model model) {
        if(search == null) {
            model.addAttribute("patients", patientService.getAllPatients());
        } else {
            model.addAttribute("patients", patientService.searchPatients(search));
        }
        return "patient-list";
    }

    @GetMapping("/delete") // localhost:8080/patient/delete?id=1
    public String delete(@RequestParam("id") Long id) {
        patientService.deletePatient(id);
        return "redirect:/patient/list";
    }

    @GetMapping("/update") // localhost:8080/patient/update?id=1
    public String formUpdate(@RequestParam("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient-form";
    }

    @GetMapping("/detail/{patientId}") // localhost:8080/patient/detail/1
    public String detail(@PathVariable("patientId") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient-detail";
    }
}
