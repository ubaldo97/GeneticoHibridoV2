/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Operadores.Cruza;
import java.util.ArrayList;
import java.util.Random;
import Herramientas.*;
/**
 *
 * @author Roberto Cruz Leija
 */
public class Poblacion {
 private ArrayList<Individuo> individuos;
    private Individuo menor;
    private Individuo mayor;
    private Integer fitnessPoblacion;
    
    // población solo en memoria 
    
    public Poblacion(){
    this.individuos = new ArrayList<>();
    this.menor = null;
    this.mayor = null;   
    }   
    // poblacion aleatoria 
    public Poblacion(int tamano){
        this.individuos = new ArrayList<>();
        for(int x=0;x<tamano;x++){
            Individuo i = new Individuo();
            i.getFitness();
            this.individuos.add(i);}
    }
    // la creacion de una poblacion en base a otra
    public Poblacion(Poblacion pob){
        this.individuos = new ArrayList<>();
        for (Individuo ind: pob.getIndividuos()){
            Individuo n = new Individuo(ind.getGenotipo());
            n.getFitness();
            this.individuos.add(n);
        }
        calcularMayorMenor();
//        if (pob.getMayor() !=null && pob.getMenor()!=null){
//           this.menor = new Individuo(pob.getMenor().getGenotipo());
//           this.menor.getFitness();
//           this.mayor = new Individuo(pob.getMayor().getGenotipo());
//           this.mayor.getFitness();
//        } else {
//           calcularMayorMenor();
//        }
        
    }
    
    public void calcularMayorMenor(){
        
      if (this.mayor == null || this.menor==null){
       // recorrer la población completa 
      this.mayor = new Individuo(this.individuos.get(0).getGenotipo());
      this.menor = new Individuo(this.individuos.get(0).getGenotipo());
      
      for(int x=1; x<this.individuos.size();x++){
          if(this.individuos.get(x).getFitness()
                  >this.mayor.getFitness()){
           this.mayor = new Individuo(this.individuos.get(x).getGenotipo());
          }
           if(this.individuos.get(x).getFitness()
                  <this.menor.getFitness()){
           this.menor = new Individuo(this.individuos.get(x).getGenotipo());
          }
      }
       
      }  
     
      
    }
    
      public ArrayList<Individuo> generarGrupoAleatorio(int numInd){
        ArrayList<Individuo> grupo = new ArrayList<>();
        Random ran = new Random();
        // seleccionar de forma aleatorio a los ind
        for(int x=0; x<numInd;x++){
         grupo.add(new Individuo
        (this.individuos.get(ran.nextInt(this.individuos.size())).getGenotipo()));
        }
          System.out.println();
     return grupo;
    }
    

    
   public ArrayList<Individuo> generarGrupoMejores(int num){
        Ordenamiento mezcla = new Ordenamiento();
        mezcla.ordenar(this);
         int inicio = this.individuos.size()-1;
        // crear el grupo de mejores
        ArrayList<Individuo> mejores = new ArrayList<>();
        for (int x=inicio;x>(inicio-num);x--){      
         mejores.add(new Individuo(this.individuos.get(x).getGenotipo()));
        
        } 
         System.out.println();
        return mejores;
   
    }
    
     public void recibirMuestra(ArrayList<Individuo> muestra){
        for (int x=0; x<muestra.size();x++)
            this.individuos.add(muestra.get(x));
     
    }
    

    /**
     * @return the individuos
     */
    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    /**
     * @return the menor
     */
    public Individuo getMenor() {
        return menor;
    }

    /**
     * @return the mayor
     */
    public Individuo getMayor() {
        return mayor;
    }

    private void generarIndividuos(ArrayList<Individuo> grupo,  ArrayList<Individuo> aux,int mascara[], int numInd,int numIndR ) {
         Random ran = new Random();
        // generamos los individuos que regreserá el metodo
        for (int y = 0; y < numIndR;y++){
         Individuo madre = new Individuo(grupo.get(ran.nextInt(numInd)).getGenotipo());
         Individuo padre = new Individuo(grupo.get(ran.nextInt(numInd)).getGenotipo());
         Individuo nuevo = Cruza.cruzaBinaria(mascara, madre, padre);
         aux.add(nuevo);
        }
    }
    
     public Integer getFitnessPoblacion() {
        Integer acum =0;
        for (Individuo ind: individuos){
        acum+=ind.getFitness();
        }
        this.fitnessPoblacion = acum;
        return fitnessPoblacion;
    }
    
    
}
