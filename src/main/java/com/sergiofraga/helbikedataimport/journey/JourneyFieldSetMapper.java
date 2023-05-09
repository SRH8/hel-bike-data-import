package com.sergiofraga.helbikedataimport.journey;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

/**
 * Custom field set mapper for Journey objects
 */
@Component
public class JourneyFieldSetMapper implements FieldSetMapper<Journey> {

    /**
     * Creates a new instance of Journey class for every journey record in the file and sets the instance fields
     *
     * @param fieldSet FieldSet instance
     * @return Journey instance
     * @throws BindException
     */
    @Override
    public Journey mapFieldSet(FieldSet fieldSet) throws BindException {
        Journey journey = new Journey();
        journey.setDepartureDate(getDate(fieldSet, "departureDate"));
        journey.setReturnDate(getDate(fieldSet, "returnDate"));
        journey.setDepartureStationId(fieldSet.readInt("departureStationId", -1));
        journey.setDepartureStationName(fieldSet.readString("departureStationName"));
        journey.setReturnStationId(fieldSet.readInt("returnStationId", -1));
        journey.setReturnStationName(fieldSet.readString("returnStationName"));
        journey.setDistanceCoveredM(getDistanceCovered(fieldSet));
        journey.setDurationS(fieldSet.readInt("durationS" , 0));

        return journey;
    }

    /**
     * Reads distance covered from a journey record in the file
     *
     * @param fieldSet FieldSet instance
     * @return int distance covered if the record is not blank, otherwise defaults to 0
     */
    private int getDistanceCovered(FieldSet fieldSet) {
        String distanceFromFile = fieldSet.readString("distanceCoveredM");

        if(!distanceFromFile.isBlank()) {
            float aux = Float.parseFloat(distanceFromFile);

            return (int) aux;
        }

        return 0;
    }

    /**
     * Reads a date from a journey record in the file
     *
     * @param fieldSet FieldSet instance
     * @param date date field
     * @return String date if its format matches with the regular expression, otherwise returns an empty string
     */
    private String getDate(FieldSet fieldSet, String date) {
        String dateToCheck = fieldSet.readString(date);

        if (dateToCheck.matches("^(19|20)\\d\\d-(0[1-9]|1[012])-([012]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$")){
            return dateToCheck;
        }

        return "";
    }
}