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

import com.me.api.model.Item;
import com.me.api.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemResource {

	@Autowired
	private ItemService is;
	
	@GetMapping
	public List<Item> buscarTodos() {
		return is.buscarTodos();		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Item> buscarPorId(@PathVariable Long id){
		Item i = is.buscarPorId(id);
		return i != null ? ResponseEntity.ok(i) : ResponseEntity.noContent().build();	// se nao tiver registro devolvo 404
	}
	
	@DeleteMapping("{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		is.deletar(id);
	}
	
}
