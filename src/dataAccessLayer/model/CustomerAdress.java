package dataAccessLayer.model;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAdress {

	private String number;
	private String street;
	private String city;
	private int zipcode;
	
	public CustomerAdress(){
		
	}

	public CustomerAdress(String number,String street,String city,int zipcode){
		this.number=number;
		this.street=street;
		this.city=city;
		this.zipcode=zipcode;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
}
