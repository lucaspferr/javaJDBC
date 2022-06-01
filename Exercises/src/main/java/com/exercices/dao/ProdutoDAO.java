package com.exercices.dao;

import com.exercices.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProdutoDAO{

    private Connection connection;

    public ProdutoDAO(Connection connection){ this.connection = connection;}

    public void createProduto(Produto produto){
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, DESCONTO, DATA_INICIO) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setString(1,produto.getNome());
            pstm.setString(2,produto.getDescricao());
            pstm.setDouble(3,produto.getDesconto());
            pstm.setDate(4, produto.getData_inicio());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()){produto.setId(rst.getInt(1));}
            }

        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public void createDatabase(){
        String sql = "CREATE TABLE if not exists produto (id INTEGER NOT NULL AUTO_INCREMENT, nome VARCHAR(255), descricao VARCHAR(255), desconto DOUBLE, data_inicio DATE, PRIMARY KEY (id))";
        try(Statement stm = connection.createStatement()){
            stm.executeUpdate(sql);
        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public void updateProduto(Produto produto, int id){
        String sql = "UPDATE produto P SET p.nome = ?, P.descricao = ?, P.desconto = ?, P.data_inicio = ? WHERE id = ?";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){

            pstm.setString(1,produto.getNome());
            pstm.setString(2,produto.getDescricao());
            pstm.setDouble(3,produto.getDesconto());
            pstm.setDate(4,produto.getData_inicio());
            pstm.setInt(5,id);

            pstm.execute();
            if(pstm.getUpdateCount() == 0) {
                System.out.println("Produto com o ID " + id + " não foi encontrado, criando nova oferta...");
                createProduto(produto);}
            else System.out.println("Produto '"+produto.getNome()+"' com o ID " + id + " atualizado.");

        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public void deleteProduto(int id){
        String sql = "DELETE FROM produto WHERE id = ?";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setInt(1,id);

            pstm.execute();
            if(pstm.getUpdateCount() == 0) System.out.println("Produto com o ID "+id+" não foi encontrado.");
            else System.out.println("Produto com o ID "+id+" deletado.");
        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public List<Produto> searchProdutos(String keyword){
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";
        List<Produto> produtos = new ArrayList<>();
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1,keyword);
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()){
                while(rst.next()){
                    Produto produto = new Produto
                            (rst.getInt(1), rst.getString(2),rst.getString(3), rst.getDouble(4),rst.getDate(5));
                    produtos.add(produto);
                }
            }
        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
        return produtos;
    }

    public HashSet<Produto> searchByWord(String sent){
        String[] keyword = sent.split(" ");
        List<Produto> produtos = new ArrayList<>();
        HashSet<Produto> produtoHashSet = new HashSet<>();
        for(int i=0; i< keyword.length; i++){
            produtoHashSet.addAll(searchProdutos("%"+keyword[i]+"%"));
        }
        return produtoHashSet;
    }
}
