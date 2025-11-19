// src/main/java/com/foodstudy/repository/FoodCashRepository.java
package com.foodstudy.repository;

import com.foodstudy.domain.model.FoodCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCashRepository extends JpaRepository<FoodCash, Long> {
}
