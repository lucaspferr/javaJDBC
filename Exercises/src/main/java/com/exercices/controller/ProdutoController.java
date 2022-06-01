package com.exercices.controller;

import com.exercices.dao.ProdutoDAO;
import com.exercices.factory.ConnectionFactory;
import com.exercices.model.Produto;
import com.exercices.model.valuegenerator.RandomValues;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Scanner;

public class ProdutoController{

    private ProdutoDAO produtoDAO;
    private RandomValues randomValues = new RandomValues();
    private Scanner scanner = new Scanner(System.in);

    public ProdutoController(){
        Connection connection = new ConnectionFactory().retrieveConnection();
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void createProduto(){
        for(int i=0; i<3; i++) {
            Produto produto = randomValues.generator();
            this.produtoDAO.createProduto(produto);
        }
    }

    public void updateProduto(int id){
        Produto produto = randomValues.generator();
        this.produtoDAO.updateProduto(produto, id);
    }

    public void deleteProduto(int id){
        this.produtoDAO.deleteProduto(id);
    }
    public void searchProduto(String keyword){
        HashSet<Produto> produtoList = produtoDAO.searchByWord(keyword);
        if(produtoList.isEmpty()) System.out.println("Nenhum resultado encotrado para o(s) termo(s): "+keyword);
        produtoList.forEach(produto -> {
            System.out.println("Id: "+produto.getId()+"       ---");
            System.out.println("Nome: "+produto.getNome()+"   | Descrição: "+produto.getDescricao());
            System.out.println("Desconto: "+produto.getDesconto()+"%   | Data de início: "+produto.getData_inicio());
        });
    }

    public void createDatabase(){
        this.produtoDAO.createDatabase();
    }
}
