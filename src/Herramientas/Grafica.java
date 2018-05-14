/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author ruben
 */
public class Grafica {
    private JFreeChart grafica;
    private XYSeriesCollection series;
    private String ejeX,ejeY,titulo;
    
    public Grafica(String  ejeX, String ejeY, String titulo){
        this.grafica = null;
        this.series = new XYSeriesCollection();
        this. ejeX = ejeX;
        this.ejeY = ejeY;
        this.titulo = titulo;
    }
    public void agregarSeries(String nombre){
        XYSeries serie = new XYSeries(nombre);
        this.series.addSeries(serie);
    }
    public void agregarDatoSerie(String nombre, XYDataItem dato){
        this.series.getSeries(nombre).add(dato);
    }
    public void agregarSerie(String nombre, ArrayList<Integer> datos){
        XYSeries serie = new XYSeries(nombre);
        for(int i=0; i<datos.size();i++){
            serie.add(i, datos.get(i));
        }
        this.series.addSeries(serie);
        
    }
    public void crearYmostrarGrafica(){
        this.grafica = ChartFactory.createXYLineChart(titulo, ejeX, ejeY, series);
        ChartFrame frame = new ChartFrame("Grafica Fitness",grafica);
        frame.setVisible(true);
        frame.setSize(600,400);
    }

    
    
}
