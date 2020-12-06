package com.me.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public Pedido findByPedido(String pedido);
	
}
