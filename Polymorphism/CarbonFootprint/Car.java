package com.example;

public class Car implements CarbonFootprint {
    private double distance;
    private final double EF = 473; //kgCO2e/mile
    public Car(double kmDistance) {
	this.distance = kmDistance / 1.60934;
    }
    /**
     * @return the distance
     */
    protected double getDistance() {
        return distance;
    }
    /**
     * @param distance the distance to set
     */
    protected void setDistance(double distance) {
        this.distance = distance;
    }
    
    /**
     * @return the EF
     */
    protected double getEF() {
        return EF;
    }
    @Override
    public double getCarbonFootprint() {
        return getDistance() * getEF() / 1000;
    }
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: %.2f",
        	"Name", this.getClass().getSimpleName(),
        	"Distance in miles", getDistance());
    }
}
