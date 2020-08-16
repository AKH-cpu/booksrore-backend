package com.akhadam.kitabi.repository;

import com.akhadam.kitabi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    StockEntity findByStockId(String stockId);
}
