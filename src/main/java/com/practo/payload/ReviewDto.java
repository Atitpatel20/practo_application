package com.practo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {
    private long id;
    private long doctorId;
    private long patientId;
    private int rating;
    private String description;
}
