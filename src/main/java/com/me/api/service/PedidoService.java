package com.me.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.api.model.Pedido;
import com.me.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pr;
	
	public List<Pedido> listar(){
		return pr.findAll();
	}
	
}
