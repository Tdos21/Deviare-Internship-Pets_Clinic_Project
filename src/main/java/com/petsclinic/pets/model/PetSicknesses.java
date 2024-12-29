package com.petsclinic.pets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sicknesses")

public class PetSicknesses {
	
	

	public PetSicknesses() {
		
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int sickId;
	
	public String sickName;
	
	public PetSicknesses(int sickId, String sickName) {
		super();
		this.sickId = sickId;
		this.sickName = sickName;
	}

	public int getSickId() {
		return sickId;
	}

	public void setSickId(int sickId) {
		this.sickId = sickId;
	}

	public String getSickName() {
		return sickName;
	}

	public void setSickName(String sickName) {
		this.sickName = sickName;
	}

	@Override
	public String toString() {
		return "PetSicknesses [sickId=" + sickId + ", sickName=" + sickName + "]";
	}
	
	

}
