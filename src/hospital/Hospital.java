/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.util.Random;

/**
 * Classe para objetos do tipo Hospital
 * @author unifwpereira
 */
public class Hospital {
    private final int cor;

    public Hospital() {
        this.cor = setCor();
    }

    public int getCor() {
        return cor;
    }
    
    /**
     * Método que escolhe aleatoriamente entre duas cores para ser a cor da cruz do hospital
     * @return um inteiro que simboliza a cor a ser usada
     */
    private int setCor(){
        int opcoes[] = {3,4};
        
        Random r = new Random();
        int escolha = opcoes[r.nextInt(opcoes.length)];
        
        return escolha;
    }
   
}
