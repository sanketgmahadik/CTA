/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author UNICOM-SOFTWARE
 */
public class GraphCT {
    
    public static  ChartPanel createChartPanel() 
    {
        String chartTitle = "CT FLOW CHART";
        String xAxisLabel = "Address";
        String yAxisLabel = "Amps";

        XYDataset dataset = createDataset();
        

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, false,false,false);
        
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        
        NumberAxis x_Axis = (NumberAxis) plot.getDomainAxis();
        //x_Axis.setAutoRange(true);
          // 60 seconds
        x_Axis.setTickUnit(new NumberTickUnit(200));
        //x_Axis.set
        x_Axis.setRange(0, 3000); 
        
        NumberAxis y_Axis = (NumberAxis) plot.getRangeAxis();
        y_Axis.setAutoRangeIncludesZero(true);
        //x_Axis = plot.getRangeAxis();
        y_Axis.setRange(-100, 3100);
        y_Axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        return new ChartPanel(chart);
    }
    
    public static XYSeries series1 = new XYSeries("Object 1");
    public static XYDataset createDataset() 
    {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }
    
    
}
