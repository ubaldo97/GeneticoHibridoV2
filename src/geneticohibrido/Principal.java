/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticohibrido;
import entidades.*;
import Herramientas.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Roberto Cruz Leija
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Individuo.clausulas=Herramientas.leeArchivo();
        GeneticoHilo gh1 = new GeneticoHilo(65,0.38,100000);
        GeneticoHilo2 gh2 = new GeneticoHilo2(50,0.4,200000);
        ArrayList listaG = new ArrayList();
        listaG.add(gh1);
        listaG.add(gh2);
        ManagerGeneticos MG = new ManagerGeneticos(listaG);
        MG.ejecutar();
    }
    
}
