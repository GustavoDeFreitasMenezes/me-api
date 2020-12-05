package com.me.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.me.api.model.Pedido;
import com.me.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pr;
	
	public List<Pedido> encontrarTodos(){
		return pr.findAll();
	}
	
	public Pedido encontrarPorId(Long id){
		Pedido p = pr.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		return p;
	}
	
	public void excluir(Long id){
		pr.deleteById(id);
	}
	
	public Pedido salvar(Pedido p){
		return pr.save(p);
	}
	
}
