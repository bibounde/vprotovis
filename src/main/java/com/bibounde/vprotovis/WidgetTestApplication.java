package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.bar.TooltipFormatter;
import com.bibounde.vprotovis.chart.line.InterpolationMode;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.Point;
import com.vaadin.Application;
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

        this.runBar();
        //this.runLine();
    }
    
    private void runBar() {
        BarChartComponent bar = new BarChartComponent();
        bar.addSerie("Sales", new double[] { -100, 1170, 660, 1030 });
        bar.addSerie("Expenses", new double[] { 400, 460, -1200, 540 });
        bar.addSerie("VAT", new double[] { 20, 46, 78, 130 });
        
        bar.setGroupNames(new String[] { "2008", "2009", "2010", "2011" });
        bar.setId("protovis");
        bar.setChartWidth(550);
        bar.setChartHeight(250);
        
        bar.setBarInset(2);
        bar.setGroupBarInset(25);
        
        bar.addHorizontalAxisWithLabel();
        
        bar.setMarginLeft(50);
        bar.setMarginBottom(20);
        bar.addVerticalAxis(650, true);
        bar.setVerticalAxisLabelFormatter(new AxisLabelFormatter() {
            public String format(double labelValue) {
                return String.valueOf(labelValue) + "\u20AC";
            }
        });
        
        bar.addLegend(150);
        
        TooltipFormatter tooltipFormatter = new TooltipFormatter() {
            
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
        };
        bar.setTooltipFormatter(tooltipFormatter);
        
        window.addComponent(bar);
    }
    
    private void runLine() {
        
        LineChartComponent line = new LineChartComponent();
        
        line.addSerie("Sales", new Point[]{new Point(0, 1000), new Point(1, 1170), new Point(2, 660), new Point(3, 1030)});
        line.addSerie("Expenses", new Point[]{new Point(0, 400), new Point(1, 460), new Point(2, 1200), new Point(3, 540)});
        line.addSerie("Expenses- NegY", new Point[]{new Point(0, 0), new Point(1, -360), new Point(2, 12), new Point(3, 210)});
        
        line.setId("protovis");
        line.setChartWidth(550);
        line.setChartHeight(250);
        line.setInterpolationMode(InterpolationMode.CARDINAL);
        //line.setLineWidth(4);
        //line.setColors(new String[]{"#9c9ede", "#cedb9c", "#de9ed6"});
        
        line.setHorizontalAxisLabelFormatter(new AxisLabelFormatter() {
            
            public String format(double labelValue) {
                return String.valueOf(labelValue) + "j.";
            }
        });
        line.addHorizontalAxisWithLabel(0.5, 3.5, 0.5, false);
        line.setMarginLeft(50);
        //line.setMarginBottom(20);
        //line.addVerticalAxis(-1200, 1200, 300);
        
        line.setVerticalAxisLabelFormatter(new AxisLabelFormatter() {
            public String format(double labelValue) {
                return String.valueOf(labelValue) + "\u20AC";
            }
        });
        line.addVerticalAxis(325, true);
        
        line.addLegend(150);
        
        window.addComponent(line);
    }
}
