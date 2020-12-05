package com.me.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.api.model.PedidoItem;
import com.me.api.service.PedidoItemService;

@RestController
@RequestMapping("/api/pedidoitem")
public class PedidoItemResource {

	@Autowired
	private PedidoItemService pis;
	
	@GetMapping
	public List<PedidoItem> listar() {
		return pis.listar();		
	}
	
}
