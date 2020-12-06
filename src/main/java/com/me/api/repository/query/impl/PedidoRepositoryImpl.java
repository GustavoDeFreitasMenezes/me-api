package com.me.api.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.me.api.model.Pedido;
import com.me.api.model.PedidoItem;
import com.me.api.repository.ItemRepository;
import com.me.api.repository.PedidoItemRepository;
import com.me.api.repository.PedidoRepository;
import com.me.api.repository.query.PedidoRepositoryQuery;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PedidoRepository pr;
	@Autowired
	private ItemRepository ir;
	@Autowired
	private PedidoItemRepository pir;
	
	@Override
	public List<Pedido> pesquisarTodos() {

		List<Pedido> lp = pr.findAll();
		
		for (Pedido p : lp) {
						
			List<PedidoItem> lpi = pir.pesquisarTodosPorPedidoId(p.getId());
			
			p.setItens(new ArrayList());
			
			for (PedidoItem pi : lpi) {
				p.getItens().add(pi.getItemId());								
			}
			
		}
		
		return lp;
	}

	@Override
	public Pedido pesquisarPorId(Long id) {
		
		Pedido p = pr.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		List<PedidoItem> lpi = pir.pesquisarTodosPorPedidoId(p.getId());
		
		p.setItens(new ArrayList());
		
		for (PedidoItem pi : lpi) {
			p.getItens().add(pi.getItemId());								
		}
		
		return p;
	}
	
	public void excluir(Long id) {
		
		// consultando todos os pedido_item
		List<PedidoItem> lpi = pir.pesquisarTodosPorPedidoId(id);
		for (PedidoItem pi : lpi) {
			
			// excluindo pedido_item
			pir.deleteById(pi.getId());			
			
			// excluindo item
			ir.deleteById(pi.getItemId().getId());
		}
		
		// excluindo pedido
		pr.deleteById(id);
		
	}

	
	
}
