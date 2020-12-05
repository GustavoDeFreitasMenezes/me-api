package com.me.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.me.api.model.Item;
import com.me.api.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository ir;
	
	public List<Item> encontrarTodos(){
		return ir.findAll();
	}
	
	public Item encontrarPorId(Long id){
		Item i = ir.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		return i;
	}
	
	public void excluir(Long id){
		ir.deleteById(id);
	}
	
	public Item salvar(Item i){
		return ir.save(i);
	}
	
}
