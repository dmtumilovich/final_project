package by.epam.rentacar.service.util;

import by.epam.rentacar.service.exception.DateParsingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public static Date parse(String dateStr) throws DateParsingException {

        try {
            Date date = DATE_FORMAT.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new DateParsingException("Exception while parsing date!", e);
        }

    }

    private DateParser() {

    }

}
