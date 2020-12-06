package com.me.api.repository.query;

import java.util.List;

import com.me.api.model.PedidoItem;

public interface PedidoItemRepositoryQuery {

	public List<PedidoItem> pesquisarTodosPorPedidoId(Long id);
	
}
