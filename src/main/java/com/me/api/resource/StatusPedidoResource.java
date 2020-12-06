package com.me.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.api.model.Pedido;
import com.me.api.model.transiente.StatusPedido;
import com.me.api.service.StatusPedidoService;

@RestController
@RequestMapping("/api/status")
public class StatusPedidoResource {
	
	@Autowired
	private StatusPedidoService sps;

	@PostMapping
	public ResponseEntity<StatusPedido> encontrar(@RequestBody Pedido pedido){
		StatusPedido sp = sps.encontrar(pedido);
		return sp != null ? ResponseEntity.ok(sp) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
}
