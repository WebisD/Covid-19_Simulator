/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoaDoente;
import java.util.Random;
import pessoa.Pessoa;
import virus.Virus;
/**
 * SubClasse de Pessoa para objetos do tipo Pessoa doente
 * @author unifwpereira
 */
public class PessoaDoente extends Pessoa {
    
    protected int x, y, cor;
    protected Virus virus;
    
    public PessoaDoente(int x, int y) {
        this.x = x;
        this.y = y;
        this.cor = 6;
    }
    /**
     * Método que infecta a pessoa com o vírus
     * @param v o virus com que a pessoa se infectará
     */
    public void infecta(Virus v){
        this.virus = v;
    } 
   
    /**
     * Método que veio da SuperClasse, escolhe aleatoriamente o proximo movimento da pessoa entre: cima, baixo, esquerda e direita
     */
    @Override
    public void mover(){
        Random r = new Random();
        // 0-up 1-down 2-left 3-right
        int indice = r.nextInt(4);
        
        if(indice == 0){
            setX(this.x-1);
        }
        else if(indice == 1){
            setX(this.x+1);
        }
        else if(indice == 2){
            setY(this.y-1);
        }
        else if(indice == 3){
            setY(this.y + 1);
        }
        
    }

    @Override
    public int getX() {
        return x;
    }
    /**
     * Método que seta a nova posição da pessoa analisando se ela não está em uma das bordas, caso estaja, ela aparecera do lado oposto
     * @param x a nova posiçõa da pessoa em relação a x
     */
    @Override
    public void setX(int x) {
        if(x < 0){
            this.x = 29;
        }
        else if (x>29){
            this.x = 0;
        }
        else{
            this.x = x;
        }   
    }

    @Override
    public int getY() {
        return y;
    }
    /**
     * Método que seta a nova posição da pessoa analisando se ela não está em uma das bordas, caso estaja, ela aparecera do lado oposto
     * @param y a nova posiçõa da pessoa em relação a y
     */
    @Override
    public void setY(int y) {
        if(y < 0){
            this.y = 59;
        }
        else if (y>59){
            this.y = 0;
        }
        else{
            this.y = y;
        }   
    }

    @Override
    public int getCor() {
        return cor;
    }

    @Override
    public void setCor(int cor) {
        this.cor = cor;
    }

    public Virus getVirus() {
        return virus;
    }
    
    
    
}
