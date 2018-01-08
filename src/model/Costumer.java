package model;

import java.util.Date;

public class Costumer {
	
	private int costumer_ID;
	private String title;
	private String first_name;
	private String last_name;
	private String drivers_licens_number;
	private Date birth_date;
	private String email;
	private String post_code;
	private String street;
	private String house_number;
	private String appartment_number;
	private String town;
	private String country;
	private String pwd_hash;
	private String salt;
	private Boolean active;
	
	public String toString(){
		return getTitle() + getFirst_name()+ getLast_name() + getDrivers_licens_number() +
			   getBirth_date() + getEmail() + getCountry() + getTown() + getPost_code() + 
			   getStreet() + getHouse_number() + getAppartment_number();
	}
	public int getCostumer_ID() {
		return costumer_ID;
	}
	public void setCostumer_ID(int costumer_ID) {
		this.costumer_ID = costumer_ID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDrivers_licens_number() {
		return drivers_licens_number;
	}
	public void setDrivers_licens_number(String drivers_licens_number) {
		this.drivers_licens_number = drivers_licens_number;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouse_number() {
		return house_number;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
	}
	public String getAppartment_number() {
		return appartment_number;
	}
	public void setAppartment_number(String appartment_number) {
		this.appartment_number = appartment_number;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPwd_hash() {
		return pwd_hash;
	}
	public void setPwd_hash(String pwd_hash) {
		this.pwd_hash = pwd_hash;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Costumer(int costumer_ID, String title, String first_name, String last_name, String drivers_licens_number,
			Date birth_date, String email, String post_code, String street, String house_number,
			String appartment_number, String town, String country, String pwd_hash, String salt, Boolean active) {
		super();
		this.costumer_ID = costumer_ID;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
		this.drivers_licens_number = drivers_licens_number;
		this.birth_date = birth_date;
		this.email = email;
		this.post_code = post_code;
		this.street = street;
		this.house_number = house_number;
		this.appartment_number = appartment_number;
		this.town = town;
		this.country = country;
		this.pwd_hash = pwd_hash;
		this.salt = salt;
		this.active = active;
	}
}
