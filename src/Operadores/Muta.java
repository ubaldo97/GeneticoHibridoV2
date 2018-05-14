/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operadores;

import java.util.Random;
import entidades.*;

/**
 *
 * @author ruben
 */
public class Muta {
     public static Individuo mutaAleatoria(Individuo ind){
    
        Random ran = new Random();
        int pos = ran.nextInt(ind.getGenotipo().length);
        if(ind.getGenotipo()[pos]==0){
            ind.getGenotipo()[pos]=1;}else{
          ind.getGenotipo()[pos]=0;
        }
         //// System.out.println(ind.getFitness());
        
        return ind;
    }
}
