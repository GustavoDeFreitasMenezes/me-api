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
import com.me.api.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService ps;
		
	@GetMapping	
	public List<Pedido> encontrarTodos() {
		return ps.encontrarTodos();		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Pedido> encontrarPorId(@PathVariable Long id){
		Pedido p = ps.encontrarPorId(id);
		return p != null ? ResponseEntity.ok(p) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
	@DeleteMapping("/{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		ps.excluir(id);
	}
	
	@PostMapping	
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido, HttpServletResponse response) {
		Pedido p = ps.salvar(pedido);
		
		// preenchendo uma URL no cabeçalho da resposta (Location) 
		// pro cliente saber qual URL ele pode consultar o objeto que acabou de ser criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
											.path("/{id}")
											.buildAndExpand(p.getId())
											.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(p);	// devolvendo o objeto criado no body
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
		Pedido p = ps.atualizar(id, pedido);		
		return ResponseEntity.ok(p);
	}
	
}
