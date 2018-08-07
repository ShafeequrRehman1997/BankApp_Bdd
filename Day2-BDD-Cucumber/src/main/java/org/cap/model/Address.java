package org.cap.model;

public class Address {
private int doorNo;
private String city;
public Address(int doorNo, String city) {
	super();
	this.doorNo = doorNo;
	this.city = city;
}
public Address() {
	
}
public int getDoorNo() {
	return doorNo;
}
public void setDoorNo(int doorNo) {
	this.doorNo = doorNo;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
@Override
public String toString() {
	return "Address [doorNo=" + doorNo + ", city=" + city + "]";
}


}
