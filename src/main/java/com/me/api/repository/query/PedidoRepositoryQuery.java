package com.me.api.repository.query;

import java.util.List;

import com.me.api.model.Pedido;

public interface PedidoRepositoryQuery {

	public List<Pedido> pesquisarTodos();
	public Pedido pesquisarPorId(Long id);
	
}
