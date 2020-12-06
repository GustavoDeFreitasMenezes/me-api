package com.me.api.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.me.api.model.Pedido;
import com.me.api.model.PedidoItem;
import com.me.api.repository.query.PedidoItemRepositoryQuery;

public class PedidoItemRepositoryImpl implements PedidoItemRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PedidoItem> pesquisarTodosPorPedidoId(Long id) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PedidoItem> criteria = builder.createQuery(PedidoItem.class);
		
		Root<PedidoItem> root = criteria.from(PedidoItem.class);
		
		List<Predicate> lp = new ArrayList<>();
		
		if (!StringUtils.isEmpty(id)) {
			Join<PedidoItem, Pedido> joinPedido = root.join("pedidoId");
			
			lp.add(
				builder.equal(
					joinPedido.get("id"), 
					id
				)
			);			
		}
		
		if(lp.size() > 0) {
			Predicate[] predicates = lp.toArray(new Predicate[lp.size()]);
			criteria.where(predicates);
		}		
		
		TypedQuery<PedidoItem> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
