package com.me.api.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.me.api.model.Pedido;
import com.me.api.model.StatusPedido;
import com.me.api.model.enumeration.StatusPedidoEnum;
import com.me.api.model.transiente.StatusPedidoResposta;
import com.me.api.repository.PedidoRepository;
import com.me.api.repository.StatusPedidoRepository;

@Service
public class StatusPedidoService {
	
	@Autowired
	private PedidoRepository pr;
	
	@Autowired
	private StatusPedidoRepository spr;

	public StatusPedidoResposta verificarStatusPedido(StatusPedido statusPedido){
		
		// salvando o status pedido
		StatusPedido sp = spr.save(statusPedido);
		
		// procurando o pedido previamente salvo no BD 
		Pedido p = pr.findByPedido(statusPedido.getPedido());
		
		// preparando resposta do status do pedido
		StatusPedidoResposta spr = new StatusPedidoResposta();
		spr.setPedido(sp.getPedido());
		spr.setStatus(new ArrayList<>());
		
		if(StringUtils.isEmpty(p)) {	// se p == null o pedido nao existe
			spr.getStatus().add(StatusPedidoEnum.CODIGO_PEDIDO_INVALIDO);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.CODIGO_PEDIDO_INVALIDO));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("REPROVADO")) {	// se status de "StatusPedido" for reprovado
			spr.getStatus().add(StatusPedidoEnum.REPROVADO);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.REPROVADO));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("APROVADO") &&
				p.getItensAprovados() > sp.getItensAprovados()) {	// se itens do produto > itens status
			spr.getStatus().add(StatusPedidoEnum.APROVADO_QTD_A_MENOR);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.APROVADO_QTD_A_MENOR));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("APROVADO") && 
				p.getItensAprovados() < sp.getItensAprovados()) {	// se itens do produto < itens status
			spr.getStatus().add(StatusPedidoEnum.APROVADO_QTD_A_MAIOR);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.APROVADO_QTD_A_MAIOR));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("APROVADO") &&
				p.getValorAprovado().compareTo(sp.getValorAprovado()) == 1) {	// se valor do produto > valor status
			spr.getStatus().add(StatusPedidoEnum.APROVADO_VALOR_A_MENOR);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.APROVADO_VALOR_A_MENOR));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("APROVADO") &&
				p.getValorAprovado().compareTo(sp.getValorAprovado()) == -1) {	// se valor do produto < valor status
			spr.getStatus().add(StatusPedidoEnum.APROVADO_VALOR_A_MAIOR);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.APROVADO_VALOR_A_MAIOR));
		}
		
		if(!StringUtils.isEmpty(p) && 
				sp.getStatus().equalsIgnoreCase("APROVADO") &&
				p.getValorAprovado().compareTo(sp.getValorAprovado()) == 0 &&
				p.getItensAprovados() == sp.getItensAprovados()) {
			spr.getStatus().add(StatusPedidoEnum.APROVADO);
			//spr.setStatus(Arrays.asList(StatusPedidoEnum.APROVADO));
		}
		
		return spr;
	}
	
}
