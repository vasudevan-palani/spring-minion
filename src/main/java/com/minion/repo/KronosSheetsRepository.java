package com.minion.repo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.KronosSheets;

public interface KronosSheetsRepository extends CrudRepository<KronosSheets, Serializable >{

	@Query("SELECT ks FROM Kronos_sheets ks where ks.date=:date and ks.hours = :hours and ks.last_name = :last_name and ks.first_name = :first_name")
	List<KronosSheets> findObject(@Param("date")Date date, @Param("hours")Double hours, @Param("last_name")String last_name, @Param("first_name")String first_name);
}
