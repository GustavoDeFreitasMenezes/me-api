package com.me.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String pedido;
	
	@Column(name = "itens_aprovados")
	private Integer itensAprovados;
	
	@Column(name = "valor_aprovado")
	private BigDecimal valorAprovado;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPedido() {
		return pedido;
	}
	
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	
	public Integer getItensAprovados() {
		return itensAprovados;
	}
	
	public void setItensAprovados(Integer itensAprovados) {
		this.itensAprovados = itensAprovados;
	}
	
	public BigDecimal getValorAprovado() {
		return valorAprovado;
	}
	
	public void setValorAprovado(BigDecimal valorAprovado) {
		this.valorAprovado = valorAprovado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
