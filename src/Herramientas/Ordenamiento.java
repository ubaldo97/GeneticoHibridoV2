/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.util.ArrayList;
import entidades.*;
/**
 *
 * @author Roberto Cruz Leija
 */
public class Ordenamiento {
    
    
    private ArrayList<Individuo> datos;
     public Ordenamiento() {
     
    }
 
    public void ordenar(Poblacion pob){
        
        this.datos = new ArrayList<>();
        for (Individuo aux: pob.getIndividuos()){
        this.datos.add(new Individuo(aux.getGenotipo().clone()));
        }
       
       
        ordenar(pob.getIndividuos(), 0, pob.getIndividuos().size()-1);
        
    }
    
    private void ordenar(ArrayList<Individuo> tmpArray, int left, int right) {
        // verificar por lo menos left sea menor que right
        if (left < right){
           int center = (left+right)/2;
            ordenar(tmpArray, left, center);
            ordenar(tmpArray, center+1, right);
            merge(tmpArray,left,center+1,right);
        }
        
    }

    private void merge(ArrayList<Individuo> tmpArray, int leftpos, int rightpos, int rightEnd) {
        // establecer unos limites
        int leftEnd = rightpos - 1;
        int tmpPos = leftpos;
        // calcular un numero de elementos 
        int numElements = rightEnd - leftpos + 1;
        
        // generar los cambios en el arreglo temporal 
        while (leftpos <= leftEnd && rightpos<=rightEnd) {
           // if (this.getDatos()[leftpos]<this.getDatos()[rightpos]){
            if (this.datos.get(leftpos).getFitness()<this.datos.get(rightpos).getFitness()){
            // tmpArray[tmpPos++] = this.getDatos()[leftpos++];
               //tmpArray.set(tmpPos++, new Individuo(this.datos.get(leftpos++).getGenotipo()));
               tmpArray.set(tmpPos++, this.datos.get(leftpos++));
            }else {
                //tmpArray.set(tmpPos++, new Individuo(this.datos.get(rightpos++).getGenotipo()));
              // tmpArray[tmpPos++] = this.getDatos()[rightpos++];
                tmpArray.set(tmpPos++, this.datos.get(rightpos++));
            }
        }
        // copiar el resto de la primera mitad
        while (leftpos <= leftEnd){
          //tmpArray[tmpPos++] = this.getDatos()[leftpos++];
          //tmpArray.set(tmpPos++, new Individuo(this.datos.get(leftpos++).getGenotipo()));
          tmpArray.set(tmpPos++, this.datos.get(leftpos++));
        }        
        // copiar el resto de la segunda mitad
         while (rightpos <= rightEnd){
          //tmpArray[tmpPos++] = this.getDatos()[rightpos++];
          //tmpArray.set(tmpPos++, new Individuo(this.datos.get(rightpos++).getGenotipo()));
          tmpArray.set(tmpPos++ , this.datos.get(rightpos++));
         }  
        
        // actualizar el arreglo 
        // recorrer el arreglo
        for (int i = 0; i < numElements; i++, rightEnd--){
        //this.datos[rightEnd] = tmpArray[rightEnd];
        //this.datos.set(rightEnd,new Individuo(tmpArray.get(rightEnd).getGenotipo()));
        this.datos.set(rightEnd, tmpArray.get(rightEnd));
        }
            
    }

    /**
     * @return the datos
     */
    public ArrayList<Individuo> getDatos() {
        return datos;
    }

    
}
