package com.me.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.api.model.Pedido;
import com.me.api.repository.query.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQuery{

	public Pedido findByPedido(String pedido);		
	
}
