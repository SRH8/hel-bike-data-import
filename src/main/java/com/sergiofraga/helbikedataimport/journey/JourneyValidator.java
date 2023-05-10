package com.sergiofraga.helbikedataimport.journey;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import java.time.LocalDateTime;

/**
 * Validator for Journey objects
 */
public class JourneyValidator implements Validator<Journey> {

    /**
     * Validates a journey
     *
     * @param journey to be validated
     * @throws ValidationException if the journey is not valid
     */
    @Override
    public void validate(Journey journey) throws ValidationException {
        if(journey.getDepartureDate() == null) {
            throw new ValidationException("departure date should have a valid format");
        }

        if(journey.getReturnDate() == null) {
            throw new ValidationException("return date should have a valid format");
        }

        if(journey.getDurationS() < 10) {
            throw new ValidationException("journeys should last more than 10 seconds");
        }

        if(journey.getDistanceCoveredM() < 10) {
            throw new ValidationException("journeys should be longer than 10 meters");
        }

        if(compareDates(journey.getDepartureDate(), journey.getReturnDate())) {
            throw new ValidationException("departure date should happen before arrival date");
        }

        if(journey.getDepartureStationId() < 0 || journey.getReturnStationId() < 0) {
            throw new ValidationException("departure and return station id should be a positive integer");
        }
    }

    /**
     * Checks if the journey's arrival happens before departure
     *
     * @param departure departure date time
     * @param arrival arrival date time
     * @return true if the arrival happens before departure, otherwise false
     */
    private boolean compareDates(LocalDateTime departure, LocalDateTime arrival) {
        return arrival.isBefore(departure);
    }
}