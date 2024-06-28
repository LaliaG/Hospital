package com.example.Hospital.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    private Long id;

    @NotNull(message = "Please enter your date of consultation!")
    @NotBlank
    private LocalDate dateConsultation;


    @NotNull(message = "Please enter your nameDoctor!")
    @NotBlank
    private String nameDoctor;


    @NotNull
    @NotBlank
    @Size(min = 1, message = "There must be at least one treatmentSheet")
    private List<String> treatmentSheets;


    @NotNull
    @NotBlank
    @Size(min = 1, message = "There must be at least one prescription")
    private List<String> prescriptions;

    private Patient patient;


    @NotNull
    @NotBlank
    private String patientId;


}
