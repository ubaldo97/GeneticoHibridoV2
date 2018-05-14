/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Individuo {

 
     public static ArrayList<Integer[]> clausulas;
    private int[] genotipo;
    private Integer fitness;
    public static Integer dimGenotipo=100;
    
    
    public Individuo() {
        this.genotipo = new int[dimGenotipo];
        // generar de forma aleatoria el genotipo del ind
        generarAleatorio();
        
    }
     private void generarAleatorio() {
       Random ran = new Random();
      
       // asignar 0 o 1 a cada uno de los genes
       for (int x=0; x<dimGenotipo;x++){
           this.genotipo[x]= ran.nextInt(2);
        calcularFitness();
       }
      
      
    }
    public Individuo(int dimGen) {
        this.genotipo = new int[dimGen];
        // todo: agregar un criterio de creación aleatoria
        generarAleatorio();
    }
    public Individuo(int[]genotipo){
        this.genotipo = genotipo.clone();
        calcularFitness();
    }
     public Individuo(Individuo i) {
        this.genotipo = i.getGenotipo().clone();
        calcularFitness();
    }
    public void calcularFitness(){
      this.fitness = 0;
      // recorrer las diferentes clausulas 
      for (Integer[] c: this.clausulas){
      boolean resultado  = verificarClausula(c);
      if(resultado)this.fitness++;
      }
    
    }

    private boolean verificarClausula(Integer[] c) {
        // recorrer la clausula 
        for (int x=0; x<c.length;x++){
        // obtener el valor de la clausula
        Integer valorC = c[x];
        Integer valorG = this.getGenotipo()[checarSigno(valorC)-1];
        if(valorC<0){
          if(valorG==0){
              valorG=1;
          }else{
          valorG=0;
          }
         }
        if(valorG==1){
        return true;
        }
        }
        return false;
    }

    private int checarSigno(int valorC) {
       if(valorC<0){
       
       valorC =valorC*-1;
       }
       return valorC;
    }

    /**
     * @return the genotipo
     */
    public int[] getGenotipo() {
        return genotipo;
    }

    /**
     * @return the fitness
     */
    public Integer getFitness() {
        return fitness;
    }
}
