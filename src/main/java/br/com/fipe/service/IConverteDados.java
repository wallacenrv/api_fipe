package br.com.fipe.service;

import java.util.List;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe); // esse <T> T GENERIC DIZ QUE VAI RETONAR ALGUMA COISA, MAS NAO SEI O QUE

    <T> List<T> obterLista(String json, Class<T> classe);// classe generica para qualquer list
    //List<DadosVeiculo> obterLista(String json, Class<DadosVeiculo> classe);
}

