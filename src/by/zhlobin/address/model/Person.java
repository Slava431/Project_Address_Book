package by.zhlobin.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final StringProperty city;
	private final IntegerProperty postalCode;
	

	//  онструктор по умолчанию.

	public Person() {
		this(null, null);
	}

	//  онструктор с некоторыми начальными данными.

	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);

		this.street = new SimpleStringProperty("some street");
		this.city = new SimpleStringProperty("some city");
		this.postalCode = new SimpleIntegerProperty(247210);
		
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty getLastNameProperty() {
		return lastName;
	}

	public String getFirstName() {
		return this.firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty getFirstNameProperty() {
		return firstName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty getStreetProperty() {
		return street;
	}

	public Integer getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode.set(postalCode);
	}

	public IntegerProperty getPostalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty getCityProperty() {
		return city;
	}
}
