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

import com.me.api.model.Item;
import com.me.api.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemResource {

	@Autowired
	private ItemService is;
	
	@GetMapping
	public List<Item> encontrarTodos() {
		return is.encontrarTodos();		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Item> encontrarPorId(@PathVariable Long id){
		Item i = is.encontrarPorId(id);
		return i != null ? ResponseEntity.ok(i) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
	@DeleteMapping("{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		is.excluir(id);
	}
	
	@PostMapping
	public ResponseEntity<Item> salvar(@RequestBody Item item, HttpServletResponse response) {
		Item i = is.salvar(item);
		
		// preenchendo uma URL no cabeçalho da resposta (Location) 
		// pro cliente saber qual URL ele pode consultar o objeto que acabou de ser criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
											.path("/{id}")
											.buildAndExpand(i.getId())
											.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(i);	// devolvendo o objeto criado no body
	}
	
	@PutMapping("{id}")	
	public ResponseEntity<Item> atualizar(@PathVariable Long id, @RequestBody Item item){
		Item i = is.atualizar(id, item);		
		return ResponseEntity.ok(i);
	}
	
}
