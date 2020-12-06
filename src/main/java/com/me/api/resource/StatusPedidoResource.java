package com.me.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.api.model.StatusPedido;
import com.me.api.model.transiente.StatusPedidoResposta;
import com.me.api.service.StatusPedidoService;

@RestController
@RequestMapping("/api/status")
public class StatusPedidoResource {
	
	@Autowired
	private StatusPedidoService sps;

	@PostMapping
	public ResponseEntity<StatusPedidoResposta> verificarStatusPedido(@RequestBody StatusPedido statusPedido){
		StatusPedidoResposta sp = sps.verificarStatusPedido(statusPedido);
		return sp != null ? ResponseEntity.ok(sp) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
}
