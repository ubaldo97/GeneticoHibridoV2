/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import entidades.*;
/**
 *
 * @author Roberto Cruz Leija
 */
public class Herramientas {
    public static ArrayList<Integer[]> leeArchivo() throws FileNotFoundException, IOException
    {
        // generar la ventana de dialogo para abrir la instancias
        String texto, aux, nombre;
        ArrayList<String> lista = new ArrayList();
        ArrayList<Integer[]> listaChida = new ArrayList();
         ArrayList<Integer[]> listaChida2 = new ArrayList();
       
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();
            nombre = abre.getName();

            //recorremos el archivo y lo leemos
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto = aux;
                    lista.add(texto);
                }
                lee.close();
                // calcular el numero de clausulas
                int numC = lista.size();  
                //leer los elementos y almacenarlos en la lista
                ArrayList<String> lista2 = new ArrayList<>();
                String clase = "";
                for (int i = 0; i < lista.size(); i++) {
                    StringTokenizer st = new StringTokenizer(lista.get(i), " ");
                    Integer[] e = new Integer[3];
                    while (st.hasMoreTokens()) {
                        lista2.add(st.nextToken());
                    }
                    for (int x = 0; x < lista2.size(); x++) {
                        e[x] = Integer.parseInt(lista2.get(x));
                    }
                    listaChida.add(i, e);
                    lista2.clear();
                }
                
                for (int y=1;y<lista.size();y++){
                    listaChida2.add(listaChida.get(y));
                }
                //retornar el array list
                return listaChida2;
            }
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Archivo no encontrado",
                    "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static void guardarMejorIndividuo(Individuo ind) throws IOException
    {
        //llamamos el metodo que permite cargar la ventana
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(file);
        //abrimos el archivo seleccionado
        File abre = file.getSelectedFile();

        String ruta = abre.getAbsolutePath();//"C:\\Users\\DE LEON\\Desktop\\" + nombre + ".txt";
        File archivo = new File(ruta + ".txt");
        BufferedWriter bw = null;
        try {
            if (archivo.exists()) {
                JOptionPane.showMessageDialog(null, "Archivo ya Existente",
                        "ERROR!!!", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                bw = new BufferedWriter(new FileWriter(archivo)); 
                int pos= ind.getGenotipo().length-1;
                for(int j=0; j<ind.getGenotipo().length;j++)
                {      
                    if(j==pos)
                    {
                    bw.append("" + ind.getGenotipo()[j]);
                    }
                    else
                    bw.append("" +ind.getGenotipo()[j] + ",");
                }         
            }
                bw.close(); 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
