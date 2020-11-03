package com.api.algafood.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) //só inclui no json se propriedade não estiver null
@Getter
@Builder
public class Problem {

	//padrão da especificação
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	//especialização
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Field> fields;

	@Getter
	@Setter
	@Builder
	public static class Field {
	
		private String name;
		private String userMessage;
	}
}

