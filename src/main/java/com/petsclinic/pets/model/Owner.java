package com.petsclinic.pets.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;





@Entity
@Table(name = "owners")
public class Owner {
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer ownerId;
	
	public String OwnerFullName;
	public String phone;
	public String ownerAddress;
	public boolean isEmployed;
	public String email;
	public String password;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BookConsultation> bookings;
	
    public Owner() {
		
	}
	
    
    public Owner(Integer ownerId, String ownerFullName, String phone, String ownerAddress, boolean isEmployed, String email,
			String password) {
		
		this.ownerId = ownerId;
		OwnerFullName = ownerFullName;
		this.phone = phone;
		this.ownerAddress = ownerAddress;
		this.isEmployed = isEmployed;
		this.email = email;
		this.password = password;
		
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerFullName() {
		return OwnerFullName;
	}
	public void setOwnerFullName(String ownerFullName) {
		OwnerFullName = ownerFullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public boolean isEmployed() {
		return isEmployed;
	}
	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}
	
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<BookConsultation> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookConsultation> bookings) {
		this.bookings = bookings;
	}


	
	

	

	

}
