package com.me.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.me.api.model.Pedido;
import com.me.api.model.PedidoItem;
import com.me.api.service.PedidoItemService;

@RestController
@RequestMapping("/api/pedidoitem")
public class PedidoItemResource {

	@Autowired
	private PedidoItemService pis;
	
	@GetMapping
	public List<PedidoItem> encontrarTodos() {
		return pis.encontrarTodos();		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<PedidoItem> encontrarPorId(@PathVariable Long id){
		PedidoItem pi = pis.encontrarPorId(id);
		return pi != null ? ResponseEntity.ok(pi) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
	@DeleteMapping("{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		pis.excluir(id);
	}
	
	@PostMapping
	public ResponseEntity<PedidoItem> salvar(@RequestBody PedidoItem pedidoItem, HttpServletResponse response) {
		PedidoItem pi = pis.salvar(pedidoItem);
		
		// preenchendo uma URL no cabeçalho da resposta (Location) 
		// pro cliente saber qual URL ele pode consultar o objeto que acabou de ser criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
											.path("/{id}")
											.buildAndExpand(pi.getId())
											.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pi);	// devolvendo o objeto criado no body
	}
	
	@PutMapping("{id}")	
	public ResponseEntity<PedidoItem> atualizar(@PathVariable Long id, @RequestBody PedidoItem pedidoItem){
		PedidoItem pi = pis.atualizar(id, pedidoItem);		
		return ResponseEntity.ok(pi);
	}
	
}
