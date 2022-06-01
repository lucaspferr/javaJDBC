package com.exercices.controller;

import com.exercices.factory.ConnectionFactory;

import java.sql.*;
import java.util.Scanner;

public class EmoteController {

    private Connection connection = new ConnectionFactory().retrieveConnection();

    public void saveFeeling(String sentimento){
        String sql = "INSERT INTO analise (sentimento) VALUES (?)";
        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setString(1,sentimento);

            pstm.execute();
            System.out.println(sentimento);
        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public void createDatabase(){
        String sql = "CREATE TABLE if not exists analise (sentimento VARCHAR(255))";
        try(Statement stm = connection.createStatement()){stm.executeUpdate(sql);
        }catch (SQLException e){throw new RuntimeException("SQL Exception: "+e.getLocalizedMessage());}
    }

    public void messageInput(){
        createDatabase();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma mensagem:");
        String msg = scanner.nextLine();

        int x=0,y=0;
        if(msg.contains(":-)")) {x = msg.split(":-[)]",-1).length-1;}
        if(msg.contains(":-(")){y = msg.split(":-[(]",-1).length-1;}
        int total = x-y;

        if(total>0) saveFeeling("Divertido");
        else if(total==0) saveFeeling("Neutro");
        else saveFeeling("Chateado");
    }
}
