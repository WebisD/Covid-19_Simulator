/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus;

/**
 * Classe para objetos do tipo Virus, ligado por agregação com PessoaDoente
 * @author unifwpereira
 */
public class Virus {
    private int tempoRestante = 30;

    public int getTempoRestante() {
        return tempoRestante;
    }
    /**
     * Método que diminui, com passo 1, o tempo de vida da pessoa doente, iniciando em 30
     */
    public void setTempoRestante() {
        this.tempoRestante--;
    }
   
}
