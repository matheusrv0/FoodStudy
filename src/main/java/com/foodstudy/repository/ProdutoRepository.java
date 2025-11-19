// src/main/java/com/foodstudy/repository/ProdutoRepository.java
package com.foodstudy.repository;

import com.foodstudy.domain.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
