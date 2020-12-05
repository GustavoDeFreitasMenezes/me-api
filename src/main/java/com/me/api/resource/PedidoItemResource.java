package com.me.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")	
	public ResponseEntity<PedidoItem> findById(@PathVariable Long id){
		PedidoItem pi = pis.findById(id);
		return pi != null ? ResponseEntity.ok(pi) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
}
