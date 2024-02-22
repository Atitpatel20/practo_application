package com.practo.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String qualification;
    private String specialization;
    private int experience;
    private String description;
    private String hospital;
}
