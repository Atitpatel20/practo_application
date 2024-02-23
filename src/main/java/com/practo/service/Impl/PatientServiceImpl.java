package com.practo.service.Impl;

import com.practo.entity.Patient;
import com.practo.payload.PatientDto;
import com.practo.repository.PatientRepository;
import com.practo.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient savePatient = patientRepository.save(patient);
        return mapToDto(savePatient);
    }

    Patient mapToEntity(PatientDto patientDto) {
        Patient dto = modelMapper.map(patientDto, Patient.class);
        return dto;
    }

    PatientDto mapToDto(Patient patient) {
        PatientDto dto = modelMapper.map(patient, PatientDto.class);
        return dto;
    }
}
