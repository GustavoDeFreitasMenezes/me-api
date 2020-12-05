package com.me.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.me.api.model.Pedido;
import com.me.api.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService ps;
	
	@GetMapping
	public List<Pedido> buscarTodos() {
		return ps.buscarTodos();		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id){
		Pedido p = ps.buscarPorId(id);
		return p != null ? ResponseEntity.ok(p) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
	@DeleteMapping("{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		ps.deletar(id);
	}
	
}
