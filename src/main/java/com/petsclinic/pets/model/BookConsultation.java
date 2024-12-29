package com.petsclinic.pets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultations")
public class BookConsultation {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int bookId;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	public Owner owner;
	
	@ManyToOne
	@JoinColumn(name="petId")
	public Pet pet;
	
	@ManyToOne
	@JoinColumn(name="sickId")
	private PetSicknesses sickness;
	
	public double consultTotalCost;
	
	
	//methods
	public BookConsultation() {
		
	}
	
	public BookConsultation(int bookId, Owner owner, Pet pet, PetSicknesses sickness, double consultTotalCost) {
		super();
		this.bookId = bookId;
		this.owner = owner;
		this.pet = pet;
		this.sickness = sickness;
		this.consultTotalCost = consultTotalCost;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public PetSicknesses getSickness() {
		return sickness;
	}

	public void setSickness(PetSicknesses sickness) {
		this.sickness = sickness;
	}

	public double getConsultTotalCost() {
		return consultTotalCost;
	}

	public void setConsultTotalCost(double consultTotalCost) {
		this.consultTotalCost = consultTotalCost;
	}

	@Override
	public String toString() {
		return "BookConsultation [bookId=" + bookId + ", owner=" + owner + ", pet=" + pet + ", sickness=" + sickness
				+ ", consultTotalCost=" + consultTotalCost + "]";
	}
	
	
	


	

}
