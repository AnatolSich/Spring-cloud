package org.integration;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Reservation {
    private long id;
    private long roomId;
    private long guestId;
    private Date date;
}