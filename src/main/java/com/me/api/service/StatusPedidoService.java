package com.me.api.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.api.model.Pedido;
import com.me.api.model.enumeration.StatusPedidoEnum;
import com.me.api.model.transiente.StatusPedido;
import com.me.api.repository.PedidoRepository;

@Service
public class StatusPedidoService {
	
	@Autowired
	private PedidoRepository pr;

	public StatusPedido encontrar(Pedido pedido){
		
		//Pedido p = pr.findByPedidoOrItensAprovadosOrValorAprovado(pedido.getPedido(), pedido.getItensAprovados(), pedido.getValorAprovado());
		Pedido p = pr.findByPedido(pedido.getPedido());
		
		//Pedido p = pr.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		//return p;
		
		StatusPedido sp = new StatusPedido();
		sp.setPedido(p.getPedido());
		sp.setStatus(new ArrayList<>());		
		sp.setStatus(Arrays.asList(StatusPedidoEnum.values()));
		
		return sp;
	}
	
}
