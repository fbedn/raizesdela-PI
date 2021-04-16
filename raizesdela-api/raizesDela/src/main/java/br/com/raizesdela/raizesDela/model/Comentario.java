package br.com.raizesdela.raizesDela.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "Comentario")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size (min=5, max=500)
	private String texto;
	@ManyToOne
	@JsonIgnoreProperties(value={"comentario","usuario"})
	private Produto produto;
	@ManyToOne
	@JsonIgnoreProperties(value={"comentario","produto"})
	private Usuario usuario;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}