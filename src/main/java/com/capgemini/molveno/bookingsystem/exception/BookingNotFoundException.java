package com.capgemini.molveno.bookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Booking not found")
public class BookingNotFoundException extends RuntimeException {

}
