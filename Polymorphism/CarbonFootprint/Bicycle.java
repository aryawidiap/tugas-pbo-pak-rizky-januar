package com.example;

public class Bicycle implements CarbonFootprint {
     // km to miles conversion: miles = km / 1.60934 
    private double miles;
    private PowerSource powerSource;
    public enum PowerSource {
	AVERAGE(50),
	BANANAS(65),
	CEREAL_WITH_MILK(90),
	BACON(200),
	CHEESEBURGERS(260),
	AIR_FREIGHTED_ASPARAGUS(2800);
	
	private final double carbonFootprint;
	
	private PowerSource(double carbonFootprint) {
	    this.carbonFootprint = carbonFootprint;
	}
    }
    public Bicycle(double kilometers, PowerSource powerSource) {
	this.miles = kilometers / 1.60934;
	this.powerSource = powerSource;
	// TODO Auto-generated constructor stub
    }
    public double getMiles() {
	return miles;
    }
    public void setMiles(double miles) {
	this.miles = miles;
    }
    public PowerSource getPowerSource() {
	return powerSource;
    }
    public void setPowerSource(PowerSource powerSource) {
	this.powerSource = powerSource;
    }
    @Override
    public double getCarbonFootprint() {
        return getMiles() * getPowerSource().carbonFootprint / 1000000;
    }
    public String toString() {
        return String.format("%s: %s%n%s: %.2f; %s: %s;",
        	"Name", this.getClass().getSimpleName(),
        	"Distance in miles", getMiles(),
        	"Energy source", getPowerSource().name());
    }

}
