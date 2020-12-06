package com.me.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.api.model.Item;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager tem;
	
	@Autowired
	private ItemRepository ir;
	
	@Test
	public void testarFindByDescricao() {

		// em memoria
	    Item i = new Item();
	    i.setDescricao("Item Test");
	    i.setPrecoUnitario(BigDecimal.valueOf(10));	    
	    
	    // salvando
	    tem.persist(i);
	    tem.flush();

	    // objeto do banco de dados
	    Item b = ir.findByDescricao(i.getDescricao());

	    // teste
	    assertThat(b.getDescricao()).isEqualTo(i.getDescricao());

	}
	
}
