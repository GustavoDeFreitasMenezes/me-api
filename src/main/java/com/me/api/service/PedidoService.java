package com.me.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.me.api.model.Item;
import com.me.api.model.Pedido;
import com.me.api.model.PedidoItem;
import com.me.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pr;
	
	@Autowired
	private ItemService is;
	@Autowired
	private PedidoItemService pis;
	
	public List<Pedido> encontrarTodos(){
		//return pr.findAll();
		return pr.pesquisarTodos();
	}
	
	public Pedido encontrarPorId(Long id){
		//Pedido p = pr.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));	// se não encontrar retorna exceção
		Pedido p = pr.pesquisarPorId(id);
		return p;
	}
	
	public void excluir(Long id){
		//pr.deleteById(id);
		pr.excluir(id);
	}
	
	public Pedido salvar(Pedido pedido){
		
		// verificando os valores de itens e preco do pedido
		if(!StringUtils.isEmpty(pedido.getItens())) {
			Integer qtde = 0;
			BigDecimal precoUnit = BigDecimal.valueOf(0);
			BigDecimal precoTotal = BigDecimal.valueOf(0);
			
			Integer itensAprovados = 0;
			BigDecimal valorAprovado = BigDecimal.valueOf(0);
			
			for (Item item : pedido.getItens()) {
				qtde = item.getQuantidade();
				precoUnit = item.getPrecoUnitario();
				precoTotal = precoUnit.multiply(BigDecimal.valueOf(qtde));
				
				itensAprovados += qtde;
				valorAprovado = valorAprovado.add(precoTotal);
			}
			
			pedido.setItensAprovados(itensAprovados);
			pedido.setValorAprovado(valorAprovado);
		}
		
		// salvando pedido
		Pedido p = pr.save(pedido);
		
		if(!StringUtils.isEmpty(p.getItens())) {
						
			for (Item item : p.getItens()) {
				
				// salvando item
				Item i = is.salvar(item);
				
				// salvando pedido item
				PedidoItem pi = new PedidoItem();
				pi.setPedidoId(p);
				pi.setItemId(i);
				pi.setQuantidade(i.getQuantidade());
				pis.salvar(pi);
				
			}
			
		}
		
		return p;
	}
	
	public Pedido atualizar(Long id, Pedido pedido){
		Pedido p = encontrarPorId(id);				// consultando registro no BD 		
		BeanUtils.copyProperties(pedido, p, "id");	// pegando as propriedades do front-end e setando no registro q veio do BD
		return pr.save(p);
	}
	
}
