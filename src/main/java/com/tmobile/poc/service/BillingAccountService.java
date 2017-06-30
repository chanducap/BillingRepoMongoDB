package com.tmobile.poc.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tmobile.poc.common.IConstants;
import com.tmobile.poc.dao.BillingRepositoryDAO;
import com.tmobile.poc.vo.BillingAccountSummaryVO;
import com.tmobile.poc.vo.CustomerVO;

@Service
public class BillingAccountService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${customerInfo.uri}")
	private String customerInfoUri;

	@Autowired
	private BillingRepositoryDAO billingDAO;
	
	/*@Autowired
	private MongoTemplate template;
*/
	/**
	 * Method is used for getting the Billing Account Balances.
	 * 
	 * @param phoneNumber
	 * @return
	 */
	private static final Logger logger = Logger.getLogger("BillingAccountService.class");
	/*	This method is used for retreving the customer balance by using the customer phone number.It will be interacting with Customer MicroService to get the Customer Data.*/

	public BillingAccountSummaryVO getAccountBalance(String phoneNumber) {

		CustomerVO customer = null;
		BillingAccountSummaryVO bAcct = null;
		try {
			customer = restTemplate.getForObject(customerInfoUri + phoneNumber, CustomerVO.class);
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			bAcct = billingDAO.findByCustomerId(customer.getCustomerId());

			if (bAcct == null)// if account is not there then save it.
			{
				System.out.println();
				bAcct = prepareObject(customer, 0.00, 0.00, 0.00, 0.00, 0.00,0);
				bAcct = billingDAO.save(bAcct);
			}

		} catch (Exception ee) {
			logger.info(ee.getMessage());
		}

		System.out.println(bAcct.toString());

		return bAcct;

	}

	private BillingAccountSummaryVO prepareObject(CustomerVO customer, Double currentBal, Double lastStmtBal,
			Double unbilledCredits, Double unbilledDebits, Double unbilledPayments, Integer acctId) {
		BillingAccountSummaryVO acct = new BillingAccountSummaryVO();
		acct.setAcctId(acctId);
		acct.setCurrentBal(0.00);
		acct.setLastStmtBal(0.00);
		acct.setUnbilledCredits(0.00);
		acct.setUnbilledDebits(0.00);
		acct.setUnbilledPayments(0.00);
		acct.setCustomerId(customer.getCustomerId());
		return acct;
	}

	/**
	 * Method used to update the Balances by Customer Id.
	 * 
	 * @param customerId
	 * @param transAmt
	 * @param transType
	 * @return
	 */
	/*Updating the Customer balance by using the three attributes*/
	public BillingAccountSummaryVO updateBalances(Integer customerId, Double transAmt, Integer transType) {

		BillingAccountSummaryVO bAcctSummary = null;
		CustomerVO customer = null;
		bAcctSummary = billingDAO.findByCustomerId(customerId);

		if (bAcctSummary.getAcctId() != null) {
			switch (transType) {
			case IConstants.DEBIT_ADJUSTMENT:
				bAcctSummary.setUnbilledDebits(bAcctSummary.getUnbilledDebits() + transAmt);
				bAcctSummary.setCurrentBal(bAcctSummary.getCurrentBal() + transAmt);
				break;
			case IConstants.CREDIT_ADJUSTMENT:
				bAcctSummary.setUnbilledCredits(bAcctSummary.getUnbilledCredits() + transAmt);
				bAcctSummary.setCurrentBal(bAcctSummary.getCurrentBal() - transAmt);
				break;
			case IConstants.PAYMENT:
				bAcctSummary.setUnbilledCredits(bAcctSummary.getUnbilledPayments() + transAmt);
				bAcctSummary.setCurrentBal(bAcctSummary.getCurrentBal() - transAmt);
				break;
			}
			bAcctSummary = billingDAO.save(bAcctSummary);
		}

		return bAcctSummary;

	}

}
