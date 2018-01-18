package com.billingmongo.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.billingmongo.service.BillingAccountService;
import com.billingmongo.vo.AccountBalanceVO;
import com.billingmongo.vo.BillingAccountSummaryVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BillingAccountController {

	@Autowired
	private BillingAccountService accountService;

	@ApiOperation(value = "This method is used for getting the Customer Billing Account Information!.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Billing Information is fetched Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })
	@GetMapping(value = "/v1/billing/account/info/{phoneNumber}", produces = "application/json")
	public ResponseEntity<?> getAccountBalance(
			@PathVariable(required = true, value = "phoneNumber") String phoneNumber) {
		if (phoneNumber != null) {
			return new ResponseEntity(accountService.getAccountBalance(phoneNumber), HttpStatus.OK);
		} else {
			return new ResponseEntity("Error Occurred while processing the request", HttpStatus.BAD_REQUEST);
		}
	}

@ApiOperation(value = "This method is used for updating the Customer Billing Account Balances!.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Balance Information is updated Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })
	@PutMapping(value = "/v1/billing/account/update", produces = "application/json")
	public ResponseEntity<?> updateAccountBalance(@RequestBody(required = true) AccountBalanceVO acct) {
		try {
			BillingAccountSummaryVO account = accountService.updateBalances(acct.getCustomerId(), acct.getTransAmt(),
					acct.getTransType());
			return new ResponseEntity(account, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Error occurred while processing the request!.", HttpStatus.BAD_REQUEST);
		}
	}

}
