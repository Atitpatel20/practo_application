package com.practo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDto {
    private long id;
    private String name;
    private String email;
    private String mobile;
    private String diseases;
    private String age;
}
