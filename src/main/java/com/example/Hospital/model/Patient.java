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
public class Patient {
    private Long id;

    @NotNull(message = "Please enter your lastname!")
    @NotBlank
    private String lastname;


    @NotNull(message = "Please enter your firstname!")
    @NotBlank
    private String firstname;

    @NotNull(message = "Please enter your birth of date!")
    @NotBlank
    private LocalDate birthDate;

    @NotNull
    @NotBlank
    @Size(min = 1, message = "There must be at least one consultation")
    private List<String> consultations;


}
