package com.tmobile.poc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tmobile.poc.vo.BillingAccountSummaryVO;

public interface BillingRepositoryDAO extends MongoRepository<BillingAccountSummaryVO, Integer> {

	@Query("{'customerId':?0}")
	BillingAccountSummaryVO findByCustomerId(@Param("customerId") Integer customerId);

}