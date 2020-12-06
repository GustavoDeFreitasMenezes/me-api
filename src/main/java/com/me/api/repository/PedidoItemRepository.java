package com.me.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.api.model.PedidoItem;
import com.me.api.repository.query.PedidoItemRepositoryQuery;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>, PedidoItemRepositoryQuery{

}
