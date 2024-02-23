package com.practo.service.Impl;

import com.practo.entity.Doctor;
import com.practo.exception.ResourceNotFoundException;
import com.practo.payload.DoctorDto;
import com.practo.repository.DoctorRepository;
import com.practo.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor save = mapToEntity(doctorDto);
        Doctor saveDetailes = doctorRepository.save(save);
        return mapToDto(saveDetailes);
    }

    @Override
    public List<DoctorDto> findByDoctorNameOrQualificationOrHospital(String keyword) {
        List<Doctor> doctorList = doctorRepository.findByDoctorNameOrQualificationOrHospital(keyword);
        List<DoctorDto> collect = doctorList.stream().map(d -> mapToDto(d)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public DoctorDto findByDoctorId(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found with id:" + doctorId)
        );
        return  mapToDto(doctor);
    }

    Doctor mapToEntity(DoctorDto doctorDto) {
        Doctor dto = modelMapper.map(doctorDto, Doctor.class);
        return dto;
    }
    DoctorDto mapToDto(Doctor doctor) {
        DoctorDto dto = modelMapper.map(doctor, DoctorDto.class);
        return dto;
    }
}
