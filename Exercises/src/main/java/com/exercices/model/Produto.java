package com.exercices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private Double desconto;
    //Compreendi a data inicio como o momento em que foi registrado o produto
    private Date data_inicio;

}
