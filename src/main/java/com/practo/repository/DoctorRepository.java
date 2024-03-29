package com.practo.repository;

import com.practo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:keyword% OR d.qualification LIKE %:keyword% OR d.hospital LIKE %:keyword%")
    List<Doctor> findByDoctorNameOrQualificationOrHospital(@Param("keyword") String keyword);
}
