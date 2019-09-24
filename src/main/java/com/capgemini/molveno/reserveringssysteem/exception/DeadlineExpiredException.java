package com.capgemini.molveno.reserveringssysteem.exception;

import com.capgemini.molveno.reserveringssysteem.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class DeadlineExpiredException extends IllegalStateException {

    public DeadlineExpiredException(Booking booking) {
        super("The deadline of " + booking.getCreationDate().plusHours(1) + " has expired, therefore the booking cannot be canceled");
    }
}
