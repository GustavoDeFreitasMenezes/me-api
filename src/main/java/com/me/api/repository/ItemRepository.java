package com.me.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public Item findByDescricao(String descricao);
	
}
