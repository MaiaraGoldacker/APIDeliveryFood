package com.api.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@JsonIgnore
	@CreationTimestamp //adiciona data e hora atual quando propriedade for criada a 1ª vez
	@Column(nullable = false, columnDefinition = "datetime") //columnDefinition= adiciona data sem precisão de milissegundos
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp //adiciona data e hora atual quando propriedade for atualizada
	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;
	
	@Column(nullable=false) //espeficica coluna notnull no banco
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcozinha", nullable=false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
	joinColumns= @JoinColumn(name = "restaurante_id"), //coluna da relação manytomany da classe q está sendo criada o relacionamento
	inverseJoinColumns = @JoinColumn(name= "forma_pagamento_id")) //coluna da tabela inversa
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produto = new ArrayList<>();
}