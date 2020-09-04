package com.healthcare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="enrollee")
public class Enrollee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="ACTIVATION_STATUS")
	Boolean status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	Date birthDate;
	
	@Column(name="PHONE_NUMBER")
	String phoneNumber;
	
	@OneToMany(mappedBy="enrollee_id", cascade=CascadeType.ALL)
	private List<Dependent> dependents;

	public Enrollee() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}
	
	public void addDependent(Dependent dependent)
	{
		if(dependents == null)
			dependents=new ArrayList<>();
		
		dependents.add(dependent);
	}
	
}
