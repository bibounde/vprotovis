package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.bar.Serie;
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
        

        /*
         * bar.setValues(new double[]{1, 5.6, 4.6, 0.2}); bar.setId("protovis");
         * 
         * //Optional //bar.setBarHeight(25); //bar.setBarInset(5);
         * //bar.setBarWidth(20); //bar.setColor("#FF7F0E");
         * bar.setLabelEnabled(true); bar.setLabelColor("black");
         * 
         * bar.setVerticalRuleEnabled(true); bar.setVerticalRuleStep(2);
         * bar.setVerticalRuleColor("black");
         * bar.setVerticalRuleLabelEnabled(true);
         */

        /*
         * GroupedBarChartComponent bar = new GroupedBarChartComponent();
         * double[][] values = {{1, 5.6, 2, 6.1}, {1, 5.6, 4.6, 2}, {2.3, 1.6,
         * 4, 4.1}, {1, 0.6, 4.6, 2}}; bar.setValues(values);
         * bar.setSerieNames(new String[]{"jan.", "feb.", "mar.", "apr."});
         * bar.setId("protovis");
         * 
         * //Optional bar.setChartWidth(450); bar.setChartHeight(300);
         * bar.setHorizontalOffset(10); bar.setHorizontalRule();
         * bar.setBarLabel(); //bar.setBarInset(10); //bar.setGroupBarInset(40);
         * //bar.setBarHeight(20); //bar.setBarWidth(10);
         */

        window.addComponent(bar);
    }

}
