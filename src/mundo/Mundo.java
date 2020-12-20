/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;
import hospital.Hospital;
import java.util.Random;
import java.util.ArrayList;
import pessoa.Pessoa;
import pessoaDoente.PessoaDoente;
import pessoaSaudavel.PessoaSaudavel;
import virus.Virus;
/**
 * Classe para objetos do tipo mundo, este contem pessoas e hospitais por composição
 * @author unifwpereira
 */
public class Mundo {
    public final int mapa[][] = new int [30][60];
    public static int tempo = 0;
    ArrayList<Hospital> hs = new ArrayList();
    ArrayList<Pessoa> pHealth = new ArrayList();
    ArrayList<Pessoa> pSick = new ArrayList();
    
    /**
     * Construtor para objetos do tipo mundo
     * @param quant quantidade de pessoas saudáveis que vivem no mundo
     */
    public Mundo(int quant) {
        //Cria 3 atributos do tipo hospital e os coloca no array
        for (int i = 0; i < 3; i++) {
            hs.add(new Hospital());
        }
        
        //Delimita os muros e o terreno do mapa
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                if( i==0 || j==0 || i==29 || j == 59 ){
                    mapa[i][j] = 1;
                }
                else{
                    mapa[i][j] = 0;
                }
            }
        }
        
        //Coloca os hospitais no mapa
        hospitais(3, 5, hs.get(0).getCor());
        hospitais(20, 25, hs.get(1).getCor());        
        hospitais(3, 47, hs.get(2).getCor());
        
        // Coloca as pessoas saudaveis em um array
        int x,y;
        Random r = new Random();
        for (int i = 0; i < quant; i++) {
            x = r.nextInt(29);
            y = r.nextInt(59);
            Pessoa p = new PessoaSaudavel(x,y);
            pHealth.add(p);
        }
        
        //Coloca uma pessoa doente em um array
        Pessoa pDoente = new PessoaDoente(r.nextInt(29), r.nextInt(59));
        Virus v = new Virus();
        ((PessoaDoente)pDoente).infecta(v);
        pSick.add(pDoente);
        
    }
    
    /** Método principal para imprimir o mundo, além de imprimir o mundo (limites, hospitais e pessoas) 
    * ele movimenta as pessoas e chama outro metodo para imprimir as informações do mundo
    */
    public void desenhaMundo(){
        imprimeDados();
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                
                if(!ehPessoa(i ,j)){
                    if( mapa[i][j] == 1 ){
                        System.out.print("\033[46m \033[m");
                    }
                    else if(mapa[i][j] == 2){
                        System.out.print("\033[47m \033[m");
                    }
                    else if( mapa[i][j] == 3){
                        System.out.print("\033[41m \033[m");
                    }
                    else if( mapa[i][j] == 4){
                        System.out.print("\033[44m \033[m");
                    }
                    else{
                        System.out.printf(" ", i,j);
                    }
                }  
            }
            System.out.println("");
        } 
        System.out.println("\n\n\n");
        
        //move as pessoas
        for(Pessoa p: pHealth){
            p.mover();
        }
        for(Pessoa p: pSick){
            p.mover();
        }
    }
    
    /**
     * Método que analisa se em determinada posição da matriz tem uma pessoa, se sim, analisa o que tem nesse local (hospital ou pessoa) e toma as devidas providencias
     * @param i coordenada do eixo x
     * @param j coordenda do eixo y
     * @return Booleano que retorna true se a pessoa estiver na coordenada dada
     */
    public boolean ehPessoa(int i, int j){
        boolean veredito = false;
        int cor = 42;
        
        if(!pHealth.isEmpty()){
            
            ArrayList<Pessoa> copiaHealth = new ArrayList<>(pHealth);
            
            for (Pessoa p : copiaHealth) {

                if(i == p.getX() && j == p.getY()){

                    if(contaminouSe(p)){
                        cor = 45;
                    }
                    else{
                        cor = 42;
                    } 

                    veredito = true;
                }     
            }
        }
        
        if(!pSick.isEmpty()){
            
            ArrayList<Pessoa> copiaSick = new ArrayList<>(pSick);
            
            for (Pessoa p : copiaSick) {

                if(i == p.getX() && j == p.getY()){

                    if(mapa[p.getX()][p.getY()] >= 2 && mapa[p.getX()][p.getY()] <= 4){
                        int pos = pSick.indexOf(p);
                        pHealth.add( new PessoaSaudavel(p.getX(), p.getY()));
                        pSick.remove(pos);
                        cor = 42;
                    }
                    else{
                        cor = 45;
                    }

                    veredito = true;                
                }     
            }
        }
        if(veredito == true){
            System.out.printf("\033[%dm \033[m", cor);
        }
        
        return veredito;
    }
    /**
     * Método que analisa e retorna se uma pessoa se contaminou ao entrar em contato com uma pessoa doente
     * @param p Uma pessoa saudável
     * @return Um booleano que retorna true caso a pessoa tenha se contaminado
     */
    public boolean contaminouSe(Pessoa p){
        boolean veredito = false;
        
        if(!pSick.isEmpty() ){
            
            ArrayList<Pessoa> copiaSick = new ArrayList<>(pSick);
            
            for(Pessoa p2 : copiaSick){
            
                if(p.getX() == p2.getX() && p.getY() == p2.getY()){

                    int pos = pHealth.indexOf(p);

                    if (pos != -1){
                        
                        Pessoa pD = new PessoaDoente(p.getX(), p.getY());
                        Virus v = new Virus();
                        ((PessoaDoente)pD).infecta(v);
                        pSick.add(pD);  
                        pHealth.remove(pos);            
                    }

                    veredito = true;
                }
            }
        }

        return veredito;
    }
    
    /**
     * Método que imprime informações do mundo, como o tempo decorrido, quantidade de pessoas vivas, 
     * assim como a quantidade de saudáveis e doentes
     */
    public void imprimeDados(){
        System.out.println("============================================================");
            System.out.println("Tempo de simulação: " + tempo);
            System.out.println("");
            System.out.printf("\033[%dm \033[m Saudaveis: \033[%dm%d\033[m           ", 42, 32, pHealth.size());
            System.out.printf("\033[%dm \033[m Doentes: \033[%dm%d\033[m\n", 45, 35, pSick.size());
            System.out.println("Total de pessoas: " + (pHealth.size() + pSick.size()));
            System.out.println("");
            System.out.println("");
    }
    /**
     * Método que coloca no mapa os hospitais
     * @param x posição em relação a x do priemiro pixel do hospital (o mais próximo do canto superior esquerdo)
     * @param y posição em relação a y do priemiro pixel do hospital (o mais próximo do canto superior esquerdo)
     * @param cor a cor que a cruz no centro do hospital deverá ser
     */
    private void hospitais(int x, int y, int cor){
        
        for (int i = x; i < x+5; i++) {
            for (int j = y; j < y+8; j++) {
                if( ((j == y+3 || j == y+4) && i>x && i<x+4 ) || ((i==x+2 || i == x+6) && j>y &&j<y+7 ) ){
                   mapa[i][j] = cor;
                }
                else{
                   mapa[i][j] = 2; 
                } 
            }
        }
    }
    
    /**
     * Método que analisa se uma pessoa está a mais de 30 segundos doente, em caso afirmativo, ela morre
     */
    public void virusKiller(){
        if(!pSick.isEmpty()){
            
            ArrayList<Pessoa> copiaSick = new ArrayList<>(pSick);
            
            for(Pessoa p : copiaSick){
                if( ((PessoaDoente)(p)).getVirus().getTempoRestante() == 0 ){
                    int pos = pSick.indexOf(p);            
                    pSick.remove(pos);
                }
            }
        }
    }
    
    /**
     * Método que diminui o tempo de vida que uma pessoa doente tem
     */
    public void diminuiTempoVida(){
        if(!pSick.isEmpty()){
            
            for(Pessoa p : pSick){
                ((PessoaDoente)p).getVirus().setTempoRestante();
            }
        }
    }
    
}
