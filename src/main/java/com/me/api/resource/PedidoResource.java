package com.me.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")	
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido p = ps.findById(id);
		return p != null ? ResponseEntity.ok(p) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
}
