package com.practo.service;

import com.practo.payload.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto addDoctor(DoctorDto doctorDto);

    List<DoctorDto> findByDoctorNameOrQualificationOrHospital(String keyword);

    DoctorDto findByDoctorId(long doctorId);
}
