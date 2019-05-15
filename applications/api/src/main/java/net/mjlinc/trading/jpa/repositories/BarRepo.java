package net.mjlinc.trading.jpa.repositories;

import net.mjlinc.trading.jpa.entities.BarEntity;
import net.mjlinc.trading.jpa.entities.BarKey;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepo extends CrudRepository<BarEntity, BarKey> {

	@Query(
	value = "select * from bar where s = :symbol and i = '1M' and t >= :startDate and t <= :endDate ", 
	nativeQuery = true)
	public List<BarEntity> findBySymboldAndDateRange(
			@Param("symbol") String symbol, 
			@Param("startDate") LocalDate startDate, 
			@Param("endDate") LocalDate endDate);
}
