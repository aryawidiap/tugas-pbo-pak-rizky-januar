package com.example;

public class Building implements CarbonFootprint {
    private double electricity_kWhPerYear;
    private double lpg_litrePerYear;
    private final double electricEF = 0.7610;
    private final double lpgEF = 6.8;

    public Building(double electricity_kWhPerYear, double lpg_litrePerYear) {
	this.electricity_kWhPerYear = electricity_kWhPerYear;
	this.lpg_litrePerYear = lpg_litrePerYear;
    }

    public double getElectricity_kWhPerYear() {
	return electricity_kWhPerYear;
    }

    public double getLpg_litrePerYear() {
	return lpg_litrePerYear;
    }

    @Override
    public double getCarbonFootprint() {
	double electricityFootprint = electricity_kWhPerYear * electricEF;
	double lpgFootprint = getLpg_litrePerYear() * lpgEF;
	return electricityFootprint + lpgFootprint;
    }
    @Override
    public String toString() {
        return String.format("%s: %s%n%s: %.2f; %s: %.2f",
        	"Name", this.getClass().getSimpleName(),
        	"kWh", getElectricity_kWhPerYear(),
        	"LPG", getLpg_litrePerYear());
    }
}
