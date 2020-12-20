/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;
import mundo.Mundo;
import java.util.Scanner;
/**
 * Main
 * @see <a href="https://youtu.be/PNjIiIobBvk">Video da Simulação com 100 Pessoas</a>
 * @see <a href="https://youtu.be/e8rw4CBLdZk">Video da Simulação com 10 Pessoas</a><br>
 * Caso não funcione:<br>
 * https://youtu.be/PNjIiIobBvk<br>
 * https://youtu.be/e8rw4CBLdZk
 * @author unifwpereira
 */
public class Projeto {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int quant, conta = 0, intervalo = 250;
        Scanner input = new Scanner( System.in );
        
        do{
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - 100 pessoas saudáveis e 1 doente");
            System.out.println("2 - 10 pessoas saudáveis e 1 doente");
            System.out.print("Opção: ");
            try{
                quant = Integer.parseInt(input.nextLine()); 
                
                if(quant == 1)
                quant = 100;
                else if (quant == 2)
                    quant = 10;
                else
                    quant = 0;
            }catch (NumberFormatException e){
                System.out.println("Número inválido!");
                quant = 0;
            }
             
        }while(quant == 0);
        
        Mundo m = new Mundo(quant);
        
        while(true){
            if(conta == (1000/intervalo)){
                Mundo.tempo++;
                m.diminuiTempoVida();
                m.virusKiller();
                conta=0;
            }

            m.desenhaMundo();            
            
            try { 
                Thread.sleep (intervalo); 
            } 
            catch (InterruptedException ex) {
                System.out.println("Vish!");
            }
            
            System.out.println("");
            conta++;
        }  
    }
}
