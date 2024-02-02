package br.com.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {}

// O
// aqui estamos representando o json ... esse é um pouco diferente perceba que aqui funciona como chave modelo.
// o List<Dados> modelos, nao coloquei o jsonAlias porque o modelos esta com o mesmo nome do json