package com.example;

class DateTest {

    public static void main(String[] args) {
	/*Initialize a Date class object*/
	Date d = new Date(9, 16, 2021);
	
	/*Test getter methods*/
	System.out.println("Month: " + d.getMonth());
	System.out.println("Day: " + d.getDay());
	System.out.println("Year: " + d.getYear());
	System.out.println();
	
	/*Test setter methods*/
	d.setMonth(10);
	d.setDay(21);
	d.setYear(2022);
	
	/*Check changes*/
	System.out.println("Month: " + d.getMonth());
	System.out.println("Day: " + d.getDay());
	System.out.println("Year: " + d.getYear());
	System.out.print("The date in d is: ");
	
	/*Test displayDate method*/
	d.displayDate();
	
    }

}
