// src/main/java/com/foodstudy/repository/EstabelecimentoRepository.java
package com.foodstudy.repository;

import com.foodstudy.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
