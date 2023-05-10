package com.sergiofraga.helbikedataimport.journey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.validator.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for JourneyValidator class
 */
class JourneyValidatorTest {

    private final JourneyValidator validator = new JourneyValidator();

    @Test
    @DisplayName(value = "When the duration of a journey is less than 10 seconds it should throw an exception")
    void whenInvalidDuration_thenThrowsException() {
        Journey journey = new Journey(1, 2, 5, 11);
        journey.setDepartureDate("2021-06-23T16:10:11");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("journeys should last more than 10 seconds", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When the distance covered in a journey is shorter than 10 meters it should throw an exception")
    void whenInvalidDistanceCovered_thenThrowsException() {
        Journey journey = new Journey(1, 2, 10, 9);
        journey.setDepartureDate("2021-06-23T16:10:11");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("journeys should be longer than 10 meters", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When arrival happens before departure it should throw an exception")
    void whenInvalidDates_thenThrowsException() {
        Journey journey = new Journey(1, 2, 345, 2303);
        journey.setDepartureDate("2021-06-23T16:40:11");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("departure date should happen before arrival date", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When a journey has a negative departure station id it should throw and exception")
    void whenInvalidDepartureStationId_thenThrowsException() {
        Journey journey = new Journey(-126, 2, 345, 2303);
        journey.setDepartureDate("2021-06-23T16:10:11");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("departure and return station id should be a positive integer", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When a journey has a negative return station id it should throw and exception")
    void whenInvalidReturnStationId_thenThrowsException() {
        Journey journey = new Journey(1, -238, 345, 2303);
        journey.setDepartureDate("2021-06-23T16:10:11");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("departure and return station id should be a positive integer", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When a journey has valid data it should not throw an exception")
    void whenValidData_thenNoExceptionIsThrown() {
        Journey journey = new Journey(1, 238, 345, 2303);
        journey.setDepartureDate("2021-06-23T16:10:11");
        journey.setReturnDate("2021-06-23T16:20:25");

        assertDoesNotThrow(() -> validator.validate(journey));
    }

    @Test
    @DisplayName(value = "Given an incorrect departure date format it should throw an exception")
    void whenInvalidDepartureDateFormat_thenThrowsException() {
        Journey journey = new Journey(1, 238, 345, 2303);
        journey.setDepartureDate("2021-06-12");
        journey.setReturnDate("2021-06-23T16:20:25");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("departure date should have a valid format", exception.getMessage());
    }

    @Test
    @DisplayName(value = "Given an incorrect return date format it should throw an exception")
    void whenInvalidReturnDateFormat_thenThrowsException() {
        Journey journey = new Journey(1, 238, 345, 2303);
        journey.setDepartureDate("2021-06-23T16:20:25");
        journey.setReturnDate("2021-06-24");
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(journey));

        assertEquals("return date should have a valid format", exception.getMessage());
    }
}