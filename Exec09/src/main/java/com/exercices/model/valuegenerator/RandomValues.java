package com.exercices.model.valuegenerator;


import com.exercices.model.Produto;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.*;


public class RandomValues {

    //8 itens
    static final List<String> nomes_um = List.of("Notebook","Desktop","Monitor","Mouse","Teclado","Headset","Headphone","Mousepad");
    static final List<String> nomes_dois = List.of(" Vermelho"," Azul"," Preto"," Branco"," Violeta"," Cinza"," Verde"," Rosa");
    static final List<String> nomes_tres = List.of(" Dell"," Acer"," HP"," Lenovo"," Gamer"," Positivo"," Razer"," Alienware");

    //3 itens
    static final List<String> desc_um = List.of("novo","semi-novo","usado");
    static final List<String> desc_dois = List.of("Lorem","Ipsum","Dolor");

    public Produto generator(){
        Produto produto = new Produto();

        produto.setNome(nameGenerator());
        produto.setDescricao(descGenerator());
        produto.setDesconto(numberGenerator());
        produto.setData_inicio(dateGenerator());

        return produto;
    }

    private int mathRandom(int x){
        int y = (int)(round(random()*x));
        return y;
    }

    private String nameGenerator() {
        String nome = nomes_um.get(mathRandom(7))+nomes_dois.get(mathRandom(7))+nomes_tres.get(mathRandom(7));
        return nome;
    }

    private String descGenerator() {
        String descricao = "Produto "+desc_um.get(mathRandom(2))+" vendido na loja "+desc_dois.get(mathRandom(2));
        return descricao;
    }

    private Double numberGenerator(){
        //Imaginei descontos de 10 a 80%
        Double desconto = (double) ((mathRandom(70)) + 10);
        return desconto;
    }

    private Date dateGenerator(){
        Date date = Date.valueOf(LocalDate.of(2022,(mathRandom(7)+5),(mathRandom(28)+1)));
        return date;
    }

}
