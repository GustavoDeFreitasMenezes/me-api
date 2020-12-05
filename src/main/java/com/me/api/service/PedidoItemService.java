package com.me.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.me.api.model.PedidoItem;
import com.me.api.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository pir;
	
	public List<PedidoItem> buscarTodos(){
		return pir.findAll();
	}
	
	public PedidoItem buscarPorId(Long id){
		PedidoItem pi = pir.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		return pi;
	}
	
	public void deletar(Long id){
		pir.deleteById(id);
	}
	
}
