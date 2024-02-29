package com.practo.service.Impl;

import com.practo.entity.Booking;
import com.practo.payload.BookingDto;
import com.practo.repository.BookingRepository;
import com.practo.repository.DoctorRepository;
import com.practo.repository.PatientRepository;
import com.practo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void addAnAppoinment(BookingDto bookingDto) {

        List<String> availableSlot = new ArrayList<>();
        availableSlot.add("10:15 AM");
        availableSlot.add("11:15 AM");
        availableSlot.add("12:15 PM");

        Booking booking = new Booking();

//        List<String> availableSlots = Arrays.asList("10:15 AM", "11:15 AM", "12:15PM");


//        Doctor doctor = doctorRepository.findById(bookingDto.getDoctorId()).orElseThrow(
//                () -> new ResourceNotFoundException("doctor not found with id: " + bookingDto.getDoctorId())
//        );
//        Patient patient = patientRepository.findById(bookingDto.getPatientId()).orElseThrow(
//                () -> new ResourceNotFoundException("patient not found with id: " + bookingDto.getPatientId())
//        );

        for (String slots : availableSlot) {
            if (slots.equals(bookingDto.getBookingTime())) {
                booking.setBookingTime(bookingDto.getBookingTime());
                availableSlot.remove(slots);
                break;
            }
        }
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Define the task to be executed
        Runnable task = () -> {
            List<String> availableTimeSlot = new ArrayList<>();
            availableSlot.add("10:15 AM");
            availableSlot.add("11:15 AM");
            availableSlot.add("12:15 PM");

            // Perform the desired actions with availableSlot
            System.out.println("Executing task at: " + java.time.LocalTime.now());
            System.out.println("Available slots: " + availableTimeSlot);
        };
        // Schedule the task to run every 24 hours starting from now
        scheduler.scheduleAtFixedRate(task, 0, 24, TimeUnit.HOURS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down the scheduler.");
            scheduler.shutdown();
        }));

//        System.out.println(availableSlot);
        booking.setDoctorId(bookingDto.getDoctorId());
        booking.setPatientId(bookingDto.getPatientId());

        if (booking.getBookingTime() != null) {
            bookingRepository.save(booking);
        } else {
            System.out.println("Time slot not available");

        }
    }

}