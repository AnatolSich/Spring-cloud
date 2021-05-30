package com.springcloud;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {
    private ReservationRepository repository;

    public ReservationWebServices(ReservationRepository repository) {
        super();
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Reservation> getReservations(@RequestParam(name = "date", required = false) Date date) {
        if (null != date) {
            return this.repository.findAllByDate(date);
        }
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        return this.repository.findById(id).get();
    }
}
