package com.billingmongo.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@Document(collection = "Statement")
@JsonInclude(value = Include.NON_NULL)
public class BillingAccountSummaryVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer acctId;

	private Integer customerId;

	private Double currentBal;

	private Double lastStmtBal;

	private Double unbilledDebits;

	private Double unbilledCredits;

	private Double unbilledPayments;

	public BillingAccountSummaryVO() {

	}

	/**
	 * @param acctId
	 * @param currentBal
	 * @param lastStmtBal
	 * @param unbilledDebits
	 * @param unbilledCredits
	 * @param unbilledPayments
	 */

	public BillingAccountSummaryVO(Integer acctId, Integer customerNum, Double currentBal, Double lastStmtBal,
			Double unbilledDebits, Double unbilledCredits, Double unbilledPayments) {
		super();
		this.acctId = acctId;
		this.customerId = customerId;
		this.currentBal = currentBal;
		this.lastStmtBal = lastStmtBal;
		this.unbilledDebits = unbilledDebits;
		this.unbilledCredits = unbilledCredits;
		this.unbilledPayments = unbilledPayments;
	}

	public Integer getAcctId() {
		return acctId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}

	public Double getLastStmtBal() {
		return lastStmtBal;
	}

	public void setLastStmtBal(Double lastStmtBal) {
		this.lastStmtBal = lastStmtBal;
	}

	public Double getUnbilledDebits() {
		return unbilledDebits;
	}

	public void setUnbilledDebits(Double unbilledDebits) {
		this.unbilledDebits = unbilledDebits;
	}

	public Double getUnbilledCredits() {
		return unbilledCredits;
	}

	public void setUnbilledCredits(Double unbilledCredits) {
		this.unbilledCredits = unbilledCredits;
	}

	public Double getUnbilledPayments() {
		return unbilledPayments;
	}

	public void setUnbilledPayments(Double unbilledPayments) {
		this.unbilledPayments = unbilledPayments;
	}

	@Override
	public String toString() {
		return "BillingAccountSummaryVO [acctId=" + acctId + ", customerId=" + customerId + ", currentBal=" + currentBal
				+ ", lastStmtBal=" + lastStmtBal + ", unbilledDebits=" + unbilledDebits + ", unbilledCredits="
				+ unbilledCredits + ", unbilledPayments=" + unbilledPayments + "]";
	}

}
