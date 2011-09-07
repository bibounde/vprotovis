package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.bar.BarTooltipFormatter;
import com.bibounde.vprotovis.chart.line.LineTooltipFormatter;
import com.bibounde.vprotovis.chart.pie.PieLabelFormatter;
import com.bibounde.vprotovis.chart.pie.PieTooltipFormatter;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.Point;
import com.vaadin.Application;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class WidgetTestApplication extends Application {
    private Window window;

    @Override
    public void init() {
        window = new Window("Widget Test");
        setMainWindow(window);

        //this.runBar();
        //this.runLine();
        //this.runPie();
        this.runSpider();
    }
    
    private void runBar() {
        BarChartComponent bar = new BarChartComponent();
        bar.addSerie("Sales", new double[] { -100, 1170, 660, 1030 });
        bar.addSerie("Expenses", new double[] { 1757.0, -1307.0, -1825.0, 252.0 });
        bar.addSerie("VAT", new double[] { 1593.0, -1659.0, -204.0, 680.0 });
        
        bar.setGroupNames(new String[] { "2008", "2009", "2010", "2011" });
        bar.setId("protovis");
        bar.setChartWidth(550);
        bar.setChartHeight(300);
        
        //bar.setBarInset(2);
        //bar.setGroupBarInset(25);
        
        //bar.setXAxisLabelVisible(true);
        
        bar.setMarginLeft(50);
        bar.setMarginBottom(20);
        bar.setYAxisVisible(true);
        bar.setYAxisLabelVisible(true);
        bar.setYAxisLabelStep(650);
        bar.setYAxisGridVisible(true);
        bar.setYAxisLabelFormatter(new AxisLabelFormatter() {
            public String format(double labelValue) {
                return String.valueOf(labelValue) + "\u20AC";
            }
        });
        
        bar.setLegendVisible(true);
        bar.setLegendAreaWidth(150);
        bar.setLegendInsetLeft(50);
        
        BarTooltipFormatter tooltipFormatter = new BarTooltipFormatter() {
            
            public String getTooltipHTML(String serieName, double value, String groupName) {
                StringBuilder tooltipHTML = new StringBuilder();
                tooltipHTML.append("<table border=0 cellpadding=2 ><tr><td valign=top>").append("<img src=\"");

                String img = "http://www.investirama.com/img/axialis/green/Arrow2%20Up.png";
                if (value < 0) {
                    img = "http://www.investirama.com/img/axialis/red/Arrow2%20Down.png";
                }
                tooltipHTML.append(img);
                tooltipHTML.append("\"></td><td>");
                tooltipHTML.append("<b><i>").append(groupName).append("</i></b><br/>");
                tooltipHTML.append(serieName).append(": ").append(value).append(" \u20AC");
                tooltipHTML.append("</td><tr></table>");

                return tooltipHTML.toString();
            }

            public boolean isVisible(String serieName, double value, String groupName) {
                return true;
            }
        };
        bar.setTooltipFormatter(tooltipFormatter);
        
        window.addComponent(bar);
    }
    
    private void runLine() {
        
        LineChartComponent line = new LineChartComponent();
        
        line.addSerie("Sales", new Point[]{new Point(0, 1000), new Point(1, 1170), new Point(2, 660), new Point(3, 1030)});
        line.addSerie("Expenses", new Point[]{new Point(0, 400), new Point(1, 460), new Point(2, 1200), new Point(3, 540)});
        line.addSerie("Expenses- NegY", new Point[]{new Point(0, 0), new Point(1, -1360), new Point(2, 12), new Point(3, 210)});
        
        line.setId("protovis");
        line.setChartWidth(450);
        line.setChartHeight(300);
        //line.setInterpolationMode(InterpolationMode.STEP_AFTER);
        //line.setLineWidth(4);
        //line.setColors(new String[]{"#9c9ede", "#cedb9c", "#de9ed6"});
        
        line.setLegendVisible(true);
        line.setLegendAreaWidth(150d);
        line.setLegendInsetLeft(50);
        
        line.setXAxisVisible(true);
        line.setXAxisLabelVisible(true);
        line.setXAxisLabelStep(0.5);
        line.setXAxisGridVisible(true);
        line.setMarginLeft(50);
        line.setMarginBottom(50);
        //line.addVerticalAxis(-1200, 1200, 300);
       
        line.setYAxisVisible(true);
        line.setYAxisLabelVisible(true);
        line.setYAxisLabelStep(300);
        line.setYAxisGridVisible(true);
        
        window.addComponent(line);
    }
    
    private void runPie() {
        
        PieChartComponent pie = new PieChartComponent();
        
        pie.addSerie("MacOs", 123d);
        pie.addSerie("Linux", 64d);
        pie.addSerie("Windows", 13d, false);
        
        final double total = 200d;
        
        pie.setId("protovis");
        pie.setChartWidth(400);
        pie.setChartHeight(250);
        
        pie.setMarginLeft(50d);
        pie.setMarginTop(50d);
        
        //pie.setLabelVisible(true);
        //pie.setLabelColor("#FFFFFF");
        
        pie.setLabelFormatter(new PieLabelFormatter() {
            
            public boolean isVisible(double labelValue) {
                return labelValue > 10d;
            }
            
            public String format(double labelValue) {
                return Double.valueOf(labelValue / total * 100).intValue() + "%";
            }
        });
        
        pie.setTooltipFormatter(new PieTooltipFormatter() {
            
            public boolean isVisible(String serieName, double value) {
                return true;//return value > 15d;
            }
            
            public String getTooltipHTML(String serieName, double value) {
                StringBuilder ret = new StringBuilder();
                ret.append("<b>").append(serieName).append("</b><br/>");
                ret.append(Double.valueOf(value / total * 100).intValue() + "%");
                
                return ret.toString();
            }
        }, false);
        
        pie.setLegendVisible(true);
        pie.setLegendAreaWidth(150d);
        pie.setLegendInsetLeft(50);
        
        //pie.setLineWidth(2);
        
        window.addComponent(pie);
    }
    
    private void runSpider() {
        SpiderChartComponent spider = new SpiderChartComponent();
        
        spider.setChartWidth(500);
        spider.setChartHeight(500);
        
        spider.addSerie("Pierre", new double[]{1d, 2d, 3d, 4d, 5d});
        spider.addSerie("Julien", new double[]{4d, 4d, 1d, 2d, 3d});
        
        spider.setAxisNames(new String[]{"GWT", "Protovis", "Vaadin", "Maven", "Jenkins"});
        
        spider.setMarginLeft(40);
        spider.setMarginBottom(40);
        spider.setMarginTop(40);
        
        spider.setLineWidth(2);
        //spider.setAreaOpacity(0.7d);
        spider.setLegendInsetLeft(60);
        spider.setLegendVisible(true);
        
        window.addComponent(spider);
    }
}
