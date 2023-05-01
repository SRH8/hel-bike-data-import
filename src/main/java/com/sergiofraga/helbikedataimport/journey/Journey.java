package com.sergiofraga.helbikedataimport.journey;

import java.time.LocalDateTime;

public class Journey {
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private int departureStationId;
    private String departureStationName;
    private int returnStationId;
    private String returnStationName;
    private int distanceCoveredM;
    private int durationS;

    public static String [] fields(){
        return new String[] {"departureDate", "returnDate", "departureStationId","departureStationName","returnStationId",
                "returnStationName","distanceCoveredM","durationS"};
    }

    public Journey() {
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = LocalDateTime.parse(departureDate);
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = LocalDateTime.parse(returnDate);
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(int returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getReturnStationName() {
        return returnStationName;
    }

    public void setReturnStationName(String returnStationName) {
        this.returnStationName = returnStationName;
    }

    public int getDistanceCoveredM() {
        return distanceCoveredM;
    }

    public void setDistanceCoveredM(int distanceCoveredM) {
        this.distanceCoveredM = distanceCoveredM;
    }

    public int getDurationS() {
        return durationS;
    }

    public void setDurationS(int durationS) {
        this.durationS = durationS;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", departureStationId=" + departureStationId +
                ", departureStationName='" + departureStationName + '\'' +
                ", returnStationId=" + returnStationId +
                ", returnStationName='" + returnStationName + '\'' +
                ", distanceCoveredM=" + distanceCoveredM +
                ", durationS=" + durationS +
                '}';
    }
}