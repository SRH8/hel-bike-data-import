package com.sergiofraga.helbikedataimport.station;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

/**
 * Validator for Station objects
 */
public class StationValidator implements Validator<Station> {

    /**
     * Validates a station
     *
     * @param station to be validated
     * @throws ValidationException if the station object is not valid
     */
    @Override
    public void validate(Station station) throws ValidationException {
        if(station.getX() < -180 || station.getX() > 180){
            throw new ValidationException("longitude should be a valid value");
        }

        if(station.getY() < -90 || station.getY() > 90){
            throw new ValidationException("latitude should be a valid value");
        }
    }
}