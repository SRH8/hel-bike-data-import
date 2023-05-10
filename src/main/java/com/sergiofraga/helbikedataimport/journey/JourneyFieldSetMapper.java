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
        journey.setDepartureDate(fieldSet.readString("departureDate"));
        journey.setReturnDate(fieldSet.readString("returnDate"));
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
}