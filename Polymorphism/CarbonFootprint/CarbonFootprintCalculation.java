package com.example;

import java.util.ArrayList;

import com.example.Bicycle.PowerSource;

public class CarbonFootprintCalculation {

    public static void main(String[] args) {
	ArrayList<CarbonFootprint> carbonItems = new ArrayList<CarbonFootprint>();
	
	carbonItems.add(new Building(3000, 228));
	carbonItems.add(new Car(15000));
	carbonItems.add(new Bicycle(15000, PowerSource.BANANAS));
	
	for(CarbonFootprint currentCarbonItem : carbonItems) {
	    System.out.println(currentCarbonItem);
	    System.out.printf("%s: %.2f%n%n", "carbon footprint", currentCarbonItem.getCarbonFootprint());
	}
    }

}
