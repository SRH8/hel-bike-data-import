package com.sergiofraga.helbikedataimport.station;

public class Station {
    private int fid;
    private int id;
    private String nimi;
    private String namn;
    private String name;
    private String osoite;
    private String adress;
    private String kaupunki;
    private String stad;
    private String operaattor;
    private int kapasiteet;
    private double x;
    private double y;

    public static String[] fields() {
        return new String[]{"fid", "id", "nimi", "namn", "name", "osoite", "adress",
                "kaupunki", "stad", "operaattor", "kapasiteet", "x", "y"};
    }

    public Station() {
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getOperaattor() {
        return operaattor;
    }

    public void setOperaattor(String operaattor) {
        this.operaattor = operaattor;
    }

    public int getKapasiteet() {
        return kapasiteet;
    }

    public void setKapasiteet(int kapasiteet) {
        this.kapasiteet = kapasiteet;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Station{" +
                "fid=" + fid +
                ", id=" + id +
                ", nimi='" + nimi + '\'' +
                ", namn='" + namn + '\'' +
                ", name='" + name + '\'' +
                ", osoite='" + osoite + '\'' +
                ", adress='" + adress + '\'' +
                ", kaupunki='" + kaupunki + '\'' +
                ", stad='" + stad + '\'' +
                ", operaattor='" + operaattor + '\'' +
                ", kapasiteet=" + kapasiteet +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}