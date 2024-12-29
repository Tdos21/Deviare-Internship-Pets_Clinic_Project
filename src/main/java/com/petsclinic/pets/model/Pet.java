package com.petsclinic.pets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int petId;

    private String petName;
    private String petType;
    private String petWeight;
    private boolean isHealthy;
    private String petAddress;
    private String ownerName;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    // Constructors
    public Pet() {
        super();
    }

    public Pet(int petId, String petName, Owner owner, String ownerName, String petType, String petWeight,
               boolean isHealthy, String petAddress) {
        super();
        this.petId = petId;
        this.petName = petName;
        this.owner = owner;
        this.ownerName = ownerName;
        this.petType = petType;
        this.petWeight = petWeight;
        this.isHealthy = isHealthy;
        this.petAddress = petAddress;
    }

    // Getters and Setters
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    public String getPetAddress() {
        return petAddress;
    }

    public void setPetAddress(String petAddress) {
        this.petAddress = petAddress;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet [petId=" + petId + ", petName=" + petName + ", owner=" + owner + ", petType=" + petType
                + ", petWeight=" + petWeight + ", isHealthy=" + isHealthy + ", petAddress=" + petAddress + "]";
    }
}
