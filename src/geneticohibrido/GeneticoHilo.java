/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticohibrido;

import entidades.*;
import Herramientas.*;
import Operadores.*;
import java.util.ArrayList;


/**
 *
 * @author Roberto Cruz Leija
 */
public class GeneticoHilo implements Runnable{

    // flag de ejecución
    private boolean run;
    private Poblacion pobActual;
    private Double probMuta;
    private int nGeneraciones;
    private int porMuestra;
    private int tamPob;
    
    public GeneticoHilo(int tamP,Double probMuta,int generaciones) {
        this.probMuta = probMuta;
        this.nGeneraciones = generaciones;
        this.tamPob = tamP;
        this.pobActual = new Poblacion(tamPob);
        this.porMuestra = 25;
      
    }
    
    public void setIndividuo(Individuo ind){
        if (this.pobActual != null){        
        this.pobActual.getIndividuos().set(0,ind);
        GUI.mostrarI.setText(""+ind.getFitness());
        }
        
        
    }
    public Individuo getInividuo(int x){
    Individuo aux = null;
    if (this.pobActual != null){
        aux = new Individuo(this.pobActual.getIndividuos().get(x));
    }
    return aux;
    }
     public Individuo getInividuoM(){
    Individuo aux = null;
    if (this.pobActual != null){
        aux = new Individuo(this.pobActual.getMayor());
    }
    return aux;
    }
       
    public void evolucionar() {
        int x=0;
      // implementación del genético    
       Poblacion nuevaPoblacion;
       this.pobActual.calcularMayorMenor();
       Individuo mejor = this.pobActual.getMayor();
       ArrayList<Integer> datosG = new ArrayList<>();
      // se genera la población inicial (aleatoria
      
      do{
       // se va construir una nueva población 
        nuevaPoblacion = new Poblacion();
    // generar el muestreo
    int cantidadM = (int)((this.tamPob*this.porMuestra)/100);
    //generarMuestreo(cantidadM,nuevaPoblacion);
    nuevaPoblacion.recibirMuestra(this.pobActual.generarGrupoAleatorio(cantidadM));
       // muestreo
       // aplican sus operadores 
        int[] mask = Mascaras.generarMascaraAleatoria(100);
    for(int i=cantidadM;i<this.tamPob;i++){
       
        // seleccionar a una madre y un padre
        Individuo madre = Seleccion.seleccionTorneoMax(pobActual);
        Individuo padre = Seleccion.seleccionRuleta(pobActual);
        // cruza
        Individuo nuevoi = Cruza.cruzaBinaria(mask,padre, madre);
        // muta (evaluar la probabilidad)
        if(Math.random()<=this.probMuta){
            Muta.mutaAleatoria(nuevoi);
        }
        // agregamos el individuo a la nueva poblacion
        nuevaPoblacion.getIndividuos().add(nuevoi);
    }
      // actualizamos la población actual
    
    this.pobActual = new Poblacion(nuevaPoblacion);
    if (this.pobActual.getMayor().getFitness()>mejor.getFitness()) {
        mejor = this.pobActual.getMayor();
        datosG.add(mejor.getFitness());
    }
    x++;
    //System.out.println(x+" Mejor "+": "+this.pobActual.getMayor().getFitness());
    GUI.G1.setText("1 "+x+" Mejor "+": "+this.pobActual.getMayor().getFitness());
    
      } while((this.run)&&(x<nGeneraciones));
      
      
    }

    @Override
    public void run() {
        this.run = true;
        evolucionar();
        this.run = false;
    }

    /**
     * @return the probMuta
     */
    public Double getProbMuta() {
        return probMuta;
    }

    /**
     * @param probMuta the probMuta to set
     */
    public void setProbMuta(Double probMuta) {
        this.probMuta = probMuta;
        System.out.println();
    }
    
}
