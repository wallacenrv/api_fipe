package br.com.fipe.principal;

import br.com.fipe.model.Dados;
import br.com.fipe.model.Modelos;
import br.com.fipe.model.Veiculo;
import br.com.fipe.service.ConsumoApi;
import br.com.fipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Principal {

    private Scanner leitura= new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    String endereco;
    public void exibir(){

        String menu = """
                        ___________________________________
                        Tipos de veículo :
                        
                        1- carros
                        2- motos 
                        3- caminhoes
                        ___________________________________
                      """;
        System.out.println(menu);
        System.out.println("Digite o veiculo que deseja ");
        String tipo = leitura.nextLine();


        if(tipo.toLowerCase().contains("car")){
           endereco  = URL_BASE+"carros/marcas";
        } else if (tipo.toLowerCase().contains("mot")) {
            endereco  = URL_BASE+"motos/marcas";
        }else if(tipo.toLowerCase().contains("cam")) {
            endereco  = URL_BASE+"caminhoes/marcas";
        }


        var json = consumo.obterDados(endereco); // pega o json Dados
        System.out.println(json);

        List<Dados> marcas = converte.obterLista(json, Dados.class); // Gera uma lista de dados

             marcas.stream()
               .sorted(Comparator.comparing(Dados::codigo)) // colocando em ordem por codigo (Interger)
               .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta : ");
        String codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        var json2 =consumo.obterDados(endereco); // pegando json

        Modelos modelosList = converte.obterDados(json2, Modelos.class); // nesse caso irei utilizar o metodo obterDadops
        //ao inves de obterList, porque nesse caso a classe modelo ja esta representada como uma List (veja a clase Modelos)

        modelosList.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro para ser buscado : ");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modelosList.modelos().stream() // Aqui esta realizando uma busca pelo nome do carro.
                .filter(m->m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(toList());
//explicando : pega a lista de modelos que voce filtrou pela marca ... filtra com o nome que o usuario digitou e retorna

        System.out.println("\n Modelos Filtrados " );
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o codigo do modelo  para buscar os valores de avaliacao ");
        var codigoModelo = leitura.nextLine();

        endereco=endereco+ "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anos = converte.obterLista(json, Dados.class);// obtive uma lista dos anos
        List<Veiculo> veiculos = new ArrayList<>();

      for (int i = 0; i < anos.size() ; i++) {
           var enderecoAnos = endereco + "/" + anos.get(i).codigo(); // percorrendo uma array.
           json = consumo.obterDados(enderecoAnos);
           Veiculo veiculo = converte.obterDados(json, Veiculo.class);
           veiculos.add(veiculo);
        }

        System.out.println("\n Todos os veiculos filtrados com avaliacoes por ano : ");
        veiculos.forEach(System.out::println);

     // percorrendo uma lista de anos

    }
}


/*
Utilizar o Postman para teste !

Objetivo :

A aplicar seus conhecimentos para implementar uma aplicação com objetivo de consultar o valor médio de
        veículos de acordo com a tabela FIPE, consumindo uma API e utilizando conceitos como coleções,
        listas e streams no Java.*/
