/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

/**
 * SuperClasse abstrata para objetos do tipo pessoa.
 * Pessoas se movem, tem uma posição inicial (X,Y) e uma cor que define se ela está doente ou saudável
 * @author unifwpereira
 */
public abstract class Pessoa {
    private int x, y, cor;
    
    /**
     * Método que deverá ser copiado pelas subclasses para que a pessoa se mova
     */
    public abstract void mover();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

}
