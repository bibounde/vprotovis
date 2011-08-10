package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.bar.Serie;
import com.bibounde.vprotovis.chart.line.InterpolationMode;
import com.bibounde.vprotovis.gwt.common.AxisLabelFormatter;
import com.bibounde.vprotovis.gwt.common.Point;
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

        //this.runBar();
        this.runLine();
    }
    
    private void runBar() {
        BarChartComponent bar = new BarChartComponent() {

            @Override
            public String getTooltipHTML(int serieIndex, int valueIndex, String groupName) {

                Serie serie = this.getChart().getSeries().get(serieIndex);

                StringBuilder tooltipHTML = new StringBuilder();
                tooltipHTML.append("<table border=0 cellpadding=2 ><tr><td valign=top>").append("<img src=\"");

                double ref = serie.getValues()[valueIndex];
                double previous = valueIndex == 0 ? 0 : serie.getValues()[valueIndex - 1];

                String img = "http://www.investirama.com/img/axialis/green/Arrow2%20Up.png";
                if (previous > ref) {
                    img = "http://www.investirama.com/img/axialis/red/Arrow2%20Down.png";
                }
                tooltipHTML.append(img);
                tooltipHTML.append("\"></td><td>");
                tooltipHTML.append("<b><i>").append(groupName).append("</i></b><br/>");
                tooltipHTML.append(serie.getName()).append(": ").append(ref).append(" \u20AC");
                tooltipHTML.append("</td><tr></table>");

                return tooltipHTML.toString();
            }

        };
        bar.addSerie("Sales", new double[] { 1000, 1170, 660, 1030 });
        bar.addSerie("Expenses", new double[] { 400, 460, 1200, 540 });
        bar.addSerie("VAT", new double[] { 20, 46, 78, 13 });

        // Optional
        bar.setGroupNames(new String[] { "2008", "2009", "2010", "2011" });
        bar.setId("protovis");
        bar.setChartWidth(600);
        bar.setChartHeight(250);

        bar.setBarInset(2);
        bar.setGroupBarInset(25);

        bar.addGroupAxisWithLabel();
        bar.addValueAxisWithLabel(70, 1201, 300, "#.#", " \u20AC");

        //bar.setColors(new String[]{"red","blue"});
        bar.addLegend(250);
        // bar.setTooltipEnabled(false);

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
        line.addVerticalAxis(300, true);
        
        line.addLegend(150);
        
        window.addComponent(line);
    }
}
