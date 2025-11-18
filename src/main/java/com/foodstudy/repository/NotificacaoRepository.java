// src/main/java/com/foodstudy/repository/NotificacaoRepository.java
package com.foodstudy.repository;

import com.foodstudy.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
