package com.projetws.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "departement", path = "departement")
public interface DepartementRepository extends PagingAndSortingRepository<Department, Long>
{
	List<Department> findAll();
}