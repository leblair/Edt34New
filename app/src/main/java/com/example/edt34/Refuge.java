package com.example.edt34;

public class Refuge {
    private String refugeName;
    private int imageName;
    private String parcName;
    private String comarca;
    private String regio;
    private String distancia;
    private String desnivell;
    private String altura;
    private String temps;
    private String openClose;

    public Refuge(String refugeName, int imageName, String parcName, String comarca, String regio, String distancia, String desnivell, String altura, String temps, String openClose) {
        this.refugeName = refugeName;
        this.imageName = imageName;
        this.parcName = parcName;
        this.comarca = comarca;
        this.regio = regio;
        this.distancia = distancia;
        this.desnivell = desnivell;
        this.altura = altura;
        this.temps = temps;
        this.openClose = openClose;
    }

    public String getRefugeName() {
        return refugeName;
    }

    public void setRefugeName(String refugeName) {
        this.refugeName = refugeName;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getParcName() {
        return parcName;
    }

    public void setParcName(String parcName) {
        this.parcName = parcName;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getDesnivell() {
        return desnivell;
    }

    public void setDesnivell(String desnivell) {
        this.desnivell = desnivell;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getOpenClose() {
        return openClose;
    }

    public void setOpenClose(String openClose) {
        this.openClose = openClose;
    }
}
