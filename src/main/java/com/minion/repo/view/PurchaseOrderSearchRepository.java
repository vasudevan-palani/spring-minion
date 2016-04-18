package com.minion.repo.view;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.view.PurchaseOrderSearch;

public interface PurchaseOrderSearchRepository extends CrudRepository<PurchaseOrderSearch, Serializable >{

	List<PurchaseOrderSearch> findByPoNumber(String poNumber);

	List<PurchaseOrderSearch> findByProjectId(Integer projectId);
}
