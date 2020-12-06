package com.me.api.model.transiente;

import java.util.List;

import com.me.api.model.enumeration.StatusPedidoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class StatusPedidoResposta {

	private String pedido;
	private List<StatusPedidoEnum> status;
	
}
