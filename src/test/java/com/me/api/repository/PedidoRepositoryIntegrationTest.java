package com.me.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.api.model.Pedido;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PedidoRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager tem;
	
	@Autowired
	private PedidoRepository pr;
	
	@Test
	public void testarFindByPedido() {

		// em memoria
	    Pedido p = new Pedido();
	    p.setPedido("20201205215700");
	    p.setValorAprovado(BigDecimal.valueOf(10));
	    p.setItensAprovados(1);
	    
	    // salvando
	    tem.persist(p);
	    tem.flush();

	    // objeto do banco de dados
	    Pedido b = pr.findByPedido(p.getPedido());

	    // teste
	    assertThat(b.getPedido()).isEqualTo(p.getPedido());

	}
	
}
