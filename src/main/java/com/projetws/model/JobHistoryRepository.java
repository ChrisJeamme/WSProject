package com.projetws.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jobHistory", path = "jobHistory")
public interface JobHistoryRepository extends PagingAndSortingRepository<JobHistory, Long>
{
	List<JobHistory> findAll();
}	