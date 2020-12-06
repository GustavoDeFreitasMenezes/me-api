package com.me.api.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public Pedido findByPedido(String pedido);
	
	public Pedido findByPedidoOrItensAprovadosOrValorAprovado(String pedido, Integer itensAprovados, BigDecimal valorAprovado);
	
}
