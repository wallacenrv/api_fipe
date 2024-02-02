package br.com.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();


//public DadosVeiculo obterDados(String json){}

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);  //mapper que é o objeto do Jackson que faz a conversão, e pediremos para realizar a leitura do json e tente transformar na classe que a pessoa passou
        } catch (JsonProcessingException e) {// lanca excecao
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()  // PODER CONSTRUIR UMA COLECAO PARA USAR A LIST
                .constructCollectionType(List.class, classe); // constri uma colecao com a interface list como nosssa classe, do tipo que a gente passar .
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
