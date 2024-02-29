package com.practo.repository;

import com.practo.entity.Booking;
import com.practo.payload.BookingDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
