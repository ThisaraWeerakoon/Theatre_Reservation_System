package com.tui.Theatre_Reservation_System.request;

import com.tui.Theatre_Reservation_System.enums.Genre;
import com.tui.Theatre_Reservation_System.enums.Language;

import javax.xml.crypto.Data;
import java.util.Date;

public class MovieRequest {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
