package com.billingmongo.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@JsonInclude(value = Include.NON_NULL)
public class CustomerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer customerId;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String ssn;
	private String dob;

	public CustomerVO() {

	}

	public CustomerVO(Integer customerId, String phoneNumber, String firstName, String lastName, String ssn,
			String dob) {
		super();
		this.customerId = customerId;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.dob = dob;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "CustomerVO [customerId=" + customerId + ", phoneNumber=" + phoneNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", ssn=" + ssn + ", dob=" + dob + "]";
	}

}
