package com.me.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.api.model.PedidoItem;
import com.me.api.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository pir;
	
	public List<PedidoItem> listar(){
		return pir.findAll();
	}
	
}
