package com.api.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	@JsonIgnore //vai ignorar propriedade quando buscada Cozinha, para evitar referência circular no json
	@OneToMany(mappedBy = "cozinha") //nome da entidade utilizada para fazer mapeamento
	private List<Restaurante> restaurantes = new ArrayList();

}
