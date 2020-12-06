package com.me.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.api.model.Item;
import com.me.api.model.Pedido;
import com.me.api.model.StatusPedido;
import com.me.api.model.enumeration.StatusPedidoEnum;
import com.me.api.model.transiente.StatusPedidoResposta;
import com.me.api.service.StatusPedidoService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatusPedidoRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager tem;
	
	//@Autowired
	//private StatusPedidoService sps;
	
	@Autowired
	private PedidoRepository pr;
	
	@Test
	public void testarSalvarPedidoVerificarStatus() {
	
		// SALVANDO PEDIDO
		Integer qtde = 0;
		BigDecimal valor = BigDecimal.valueOf(0);
		
		// preenchendo pedido
		Pedido p = new Pedido();
		p.setPedido("123456");
		p.setItens(new ArrayList<Item>());
		
		// preenchendo itens
		Item i1 = new Item();
		i1.setDescricao("Item A");
		i1.setPrecoUnitario(BigDecimal.valueOf(10));
		i1.setQuantidade(1);
		
		qtde += i1.getQuantidade();																	// totalizando itens aprovados
		valor = valor.add(i1.getPrecoUnitario().multiply(BigDecimal.valueOf(i1.getQuantidade())));	// totalizando valor aprovado
		
		Item i2 = new Item();
		i2.setDescricao("Item B");
		i2.setPrecoUnitario(BigDecimal.valueOf(5));
		i2.setQuantidade(2);
		
		qtde += i2.getQuantidade();																	// totalizando itens aprovados
		valor = valor.add(i2.getPrecoUnitario().multiply(BigDecimal.valueOf(i2.getQuantidade())));	// totalizando valor aprovado
				
		p.setItensAprovados(qtde);	// somatorio dos itens aprovados ao pedido
		p.setValorAprovado(valor);	// somatorio dos valores aprovados ao pedido
		
		// adicionando itens ao pedido
		p.getItens().add(i1);
		p.getItens().add(i2);
		
		// gravando pedido
		tem.persist(p);
	    tem.flush();
		
	    // buscando pedido no banco de dados
	    Pedido b = pr.findByPedido(p.getPedido());
		
		// testando pedido salvo
	    assertThat(b.getPedido()).isEqualTo(p.getPedido());
	    assertThat(b.getItensAprovados() == 3);
	    assertThat(b.getValorAprovado().compareTo(BigDecimal.valueOf(20)) == 0);
	    
	    
		
	    // VERIFICANDO STATUS DO PEDIDO
	    StatusPedidoService sps = new StatusPedidoService();
	    
	    StatusPedido sp6 = new StatusPedido();
	    sp6.setPedido("123456-N");
	    sp6.setStatus("APROVADO");
	    sp6.setItensAprovados(0);
	    sp6.setValorAprovado(BigDecimal.valueOf(20));
	    
	    Pedido b6 = pr.findByPedido(sp6.getPedido());
	    	    
	    StatusPedidoResposta spr6 = sps.retornaStatusPedidoResposta(sp6, b6);	    	    
	    
	    // testando status do pedido	    	    
	    List ls6 = new ArrayList();
	    ls6.add(StatusPedidoEnum.CODIGO_PEDIDO_INVALIDO);	    	    	   
	    
	    assertThat(spr6.getStatus()).isEqualTo(ls6);
	    assertThat(spr6.getPedido()).isEqualTo("123456-N");
	    
	    
	    
	    StatusPedido sp5 = new StatusPedido();
	    sp5.setPedido("123456");
	    sp5.setStatus("REPROVADO");
	    sp5.setItensAprovados(0);
	    sp5.setValorAprovado(BigDecimal.valueOf(0));
	    
	    Pedido b5 = pr.findByPedido(sp5.getPedido());
	    	    
	    StatusPedidoResposta spr5 = sps.retornaStatusPedidoResposta(sp5, b5);	    	    
	    
	    // testando status do pedido	    	    
	    List ls5 = new ArrayList();
	    ls5.add(StatusPedidoEnum.REPROVADO);	    	    	   
	    
	    assertThat(spr5.getStatus()).isEqualTo(ls5);
	    assertThat(spr5.getPedido()).isEqualTo("123456");
	    
	    
	    
	    StatusPedido sp4 = new StatusPedido();
	    sp4.setPedido("123456");
	    sp4.setStatus("APROVADO");
	    sp4.setItensAprovados(2);
	    sp4.setValorAprovado(BigDecimal.valueOf(20));
	    
	    Pedido b4 = pr.findByPedido(sp4.getPedido());
	    	    
	    StatusPedidoResposta spr4 = sps.retornaStatusPedidoResposta(sp4, b4);	    	    
	    
	    // testando status do pedido	    	    
	    List ls4 = new ArrayList();
	    ls4.add(StatusPedidoEnum.APROVADO_QTD_A_MENOR);	    	    	   
	    
	    assertThat(spr4.getStatus()).isEqualTo(ls4);
	    assertThat(spr4.getPedido()).isEqualTo("123456");
	    
	    
	    
	    StatusPedido sp3 = new StatusPedido();
	    sp3.setPedido("123456");
	    sp3.setStatus("APROVADO");
	    sp3.setItensAprovados(4);
	    sp3.setValorAprovado(BigDecimal.valueOf(21));
	    
	    Pedido b3 = pr.findByPedido(sp3.getPedido());
	    	    
	    StatusPedidoResposta spr3 = sps.retornaStatusPedidoResposta(sp3, b3);	    	    
	    
	    // testando status do pedido	    	    
	    List ls3 = new ArrayList();
	    ls3.add(StatusPedidoEnum.APROVADO_QTD_A_MAIOR);
	    ls3.add(StatusPedidoEnum.APROVADO_VALOR_A_MAIOR);
	    
	    assertThat(spr3.getStatus()).isEqualTo(ls3);
	    assertThat(spr3.getPedido()).isEqualTo("123456");
	    
	    
	    
	    StatusPedido sp2 = new StatusPedido();
	    sp2.setPedido("123456");
	    sp2.setStatus("APROVADO");
	    sp2.setItensAprovados(3);
	    sp2.setValorAprovado(BigDecimal.valueOf(10));
	    
	    Pedido b2 = pr.findByPedido(sp2.getPedido());
	    	    
	    StatusPedidoResposta spr2 = sps.retornaStatusPedidoResposta(sp2, b2);	    	    
	    
	    // testando status do pedido	    	    
	    List ls2 = new ArrayList();	    
	    ls2.add(StatusPedidoEnum.APROVADO_VALOR_A_MENOR);
	    
	    assertThat(spr2.getStatus()).isEqualTo(ls2);
	    assertThat(spr2.getPedido()).isEqualTo("123456");
	    
	    
	    
	    StatusPedido sp1 = new StatusPedido();
	    sp1.setPedido("123456");
	    sp1.setStatus("APROVADO");
	    sp1.setItensAprovados(3);
	    sp1.setValorAprovado(BigDecimal.valueOf(20));
	    
	    Pedido b1 = pr.findByPedido(sp1.getPedido());
	    	    
	    StatusPedidoResposta spr1 = sps.retornaStatusPedidoResposta(sp1, b1);	    	    
	    
	    // testando status do pedido	    	    
	    List ls1 = new ArrayList();	    
	    ls1.add(StatusPedidoEnum.APROVADO);
	    
	    assertThat(spr1.getStatus()).isEqualTo(ls1);
	    assertThat(spr1.getPedido()).isEqualTo("123456");
	    
	}
	
	
}
