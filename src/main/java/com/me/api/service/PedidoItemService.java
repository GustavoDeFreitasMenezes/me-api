package com.me.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.me.api.model.PedidoItem;
import com.me.api.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository pir;
	
	public List<PedidoItem> encontrarTodos(){
		return pir.findAll();
	}
	
	public PedidoItem encontrarPorId(Long id){
		PedidoItem pi = pir.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		return pi;
	}
	
	public void excluir(Long id){
		pir.deleteById(id);
	}
	
	public PedidoItem salvar(PedidoItem pi){
		return pir.save(pi);
	}
	
	public PedidoItem atualizar(Long id, PedidoItem pedidoItem){
		PedidoItem pi = encontrarPorId(id);				// consultando registro no BD 		
		BeanUtils.copyProperties(pedidoItem, pi, "id");	// pegando as propriedades do front-end e setando no registro q veio do BD
		return pir.save(pi);
	}
	
}
