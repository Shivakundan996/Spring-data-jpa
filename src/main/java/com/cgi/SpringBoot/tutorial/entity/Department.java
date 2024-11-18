package com.cgi.SpringBoot.tutorial.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   //it will help in build the object and set values
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    @NotBlank(message = "please add department name")
    /*@Length(max = 5,min = 1)
    @Size(max = 10,min = 0)
    @Email
    @Positive
    @Negative
    @PositiveOrZero
    @NegativeOrZero
    @Future
    @FutureOrPresent
    @Past
    @PastOrPresent*/
    @JsonProperty("department_Name")   // useful when the JSON keys in a payload don't match the variable names in your Java classes
    private String departmentName;

    @JsonProperty("department_Address")
    private String departmentAddress;

    @JsonProperty("department_Code")
    private String departmentCode;

}
