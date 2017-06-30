package com.tmobile.poc.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@JsonInclude(value = Include.NON_NULL)
public class AccountBalanceVO   implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private Double transAmt;
	private Integer transType;

	public AccountBalanceVO()
	{
		
	}
	
	/**
	 * @param customerId
	 * @param transAmt
	 * @param transType
	 */
	public AccountBalanceVO(Integer customerId, Double transAmt, Integer transType) {
		super();
		this.customerId = customerId;
		this.transAmt = transAmt;
		this.transType = transType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

}
