package com.sergiofraga.helbikedataimport.station;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.validator.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for StationValidator class
 */
class StationValidatorTest {

    private final StationValidator validator = new StationValidator();

    @Test
    @DisplayName(value = "When a station has an invalid longitude it should throw an exception")
    void whenInvalidLongitude_thenThrowsException() {
        Station station = new Station();
        station.setX(-180.01d);
        Exception exception = assertThrows(ValidationException.class, () ->
            validator.validate(station));

        assertEquals("longitude should be a valid value", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When a station has a valid longitude it should not throw an exception")
    void whenValidLongitude_thenNoExceptionIsThrown() {
        Station station = new Station();
        station.setX(-80.01d);

        assertDoesNotThrow(() -> validator.validate(station));
    }

    @Test
    @DisplayName(value = "When a station has an invalid latitude it should throw an exception")
    void whenInvalidLatitude_thenThrowsException() {
        Station station = new Station();
        station.setY(90.01d);
        Exception exception = assertThrows(ValidationException.class, () ->
                validator.validate(station));

        assertEquals("latitude should be a valid value", exception.getMessage());
    }

    @Test
    @DisplayName(value = "When a station has a valid latitude it should not throw an exception")
    void whenValidLatitude_thenNoExceptionIsThrown() {
        Station station = new Station();
        station.setY(-89.99d);

        assertDoesNotThrow(() -> validator.validate(station));
    }
}