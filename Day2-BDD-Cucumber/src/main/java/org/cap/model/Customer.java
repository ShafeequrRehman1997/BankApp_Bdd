package org.cap.model;

public class Customer {
private String firstName;
private String lastName;
private Address address;
public Customer(String firstName, String lastName, Address address) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
}
public Customer() {
	
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
@Override
public String toString() {
	return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + "]";
}

}
