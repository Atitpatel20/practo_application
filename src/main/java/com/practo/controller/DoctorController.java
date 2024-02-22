package com.practo.controller;

import com.practo.payload.DoctorDto;
import com.practo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/add")
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctordto) {
        DoctorDto dto= doctorService.addDoctor(doctordto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<DoctorDto> searchDoctors(@RequestParam String keyword) {
        List<DoctorDto> doctors = doctorService.findByDoctorNameOrQualificationOrHospital(keyword);
        return doctors;
    }
    // Other methods as needed
}
