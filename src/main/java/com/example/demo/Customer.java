package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Customer {
	
	private @Id @GeneratedValue Long id;

	private String name;

	private String phone;

	Customer() {}

	Customer(String name, String phone) {

	    this.name = name;
	    this.phone = phone;
	  }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Customer))
	      return false;
	    Customer customer = (Customer) o;
	    return Objects.equals(this.id, customer.id) && Objects.equals(this.name, customer.name)
	        && Objects.equals(this.phone, customer.phone);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.name, this.phone);
	  }

	  @Override
	  public String toString() {
	    return "Customer{" + "id=" + this.id + ", name='" + this.name + '\'' + ", phone='" + this.phone + '\'' + '}';
	  }
}
