package br.com.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias({"codigo", "code"}) String codigo,
                    @JsonAlias("nome") String nome){

}


//@JsonIgnoreProperties(ignoreUnknown = true)  ignora os campos do json
//jsonProperty = ele serve para serealizar e desserializar