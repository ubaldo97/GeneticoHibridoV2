/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticohibrido;

import java.util.ArrayList;

/**
 *
 * @author Roberto Cruz Leija
 */
public class ManagerGeneticos {
    
    private ArrayList geneticos;

    public ManagerGeneticos(int numGeneticos) {
        /// proceso iterativo para la creacion de los geneticos
    }
    public ManagerGeneticos(ArrayList aux) {
        /// proceso iterativo para la creacion de los geneticos
        geneticos = aux;
    }
    
    public void intercambiarIndividuos() throws InterruptedException{
     // el criterio de intercambio es el primero
     GeneticoHilo2 destinoGen =  (GeneticoHilo2) this.geneticos.get(1);
     GeneticoHilo origenGen = (GeneticoHilo) this.geneticos.get(0);
     destinoGen.setIndividuo(origenGen.getInividuo(0));
   
    }
    
     public void intercambiarIndividuos2() throws InterruptedException{
     // el criterio de intercambio es el primero
     GeneticoHilo destinoGen =  (GeneticoHilo) this.geneticos.get(0);
     GeneticoHilo2 origenGen = (GeneticoHilo2) this.geneticos.get(1);
     destinoGen.setIndividuo(origenGen.getInividuo(0));
   
    }
    
    public void cambiarMuta(Double muta){
    GeneticoHilo destinoGen =  (GeneticoHilo) this.geneticos.get(0);
    destinoGen.setProbMuta(muta);
    GUI.mostrarM.setText(String.format("%.2f",muta));
    }
     public void cambiarMuta2(Double muta){
    GeneticoHilo2 destinoGen =  (GeneticoHilo2) this.geneticos.get(1);
    destinoGen.setProbMuta(muta);
    GUI.mostrarM.setText(String.format("%.2f",muta));
    }
    
    // TODO: MODIFCAR OPERADORES 
    public void ejecutar(){
        Thread auxHilo;
        // proceso iterativo para la asignacion de los hilos y su ejecución
        for (int x=0; x< this.geneticos.size();x++){
         auxHilo = new Thread((Runnable) this.geneticos.get(x));
         auxHilo.start();
        }
    
    }
    
}
