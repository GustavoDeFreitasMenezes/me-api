package com.me.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.api.model.Pedido;
import com.me.api.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService ps;
	
	@GetMapping
	public List<Pedido> listar() {
		return ps.listar();		
	}
	
}
