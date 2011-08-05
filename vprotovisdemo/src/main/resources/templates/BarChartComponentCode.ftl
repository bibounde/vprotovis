import com.gdteam.vprotovis.BarChartComponent;
import com.gdteam.vprotovis.chart.bar.Serie;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class WidgetTestApplication extends Application {
    <#if tooltipEnabled == 1 && tooltipType = "Custom">
    
    private static final DecimalFormat FORMAT_PERCENT = new DecimalFormat("# %");
    </#if>
    
    private Window window;

    @Override
    public void init() {
        window = new Window("Widget Test");
        setMainWindow(window);

        <#if tooltipEnabled == 0 || tooltipType = "Default">
        BarChartComponent bar = new BarChartComponent();
        <#else>
        BarChartComponent bar = new BarChartComponent() {
            @Override
            public String getTooltipHTML(int serieIndex, int valueIndex, String groupName) {

                Serie serie = this.getChart().getSeries().get(serieIndex);

                double ref = serie.getValues()[valueIndex];
                double previous = valueIndex == 0 ? 0 : serie.getValues()[valueIndex - 1];
                    
                StringBuilder tooltipHTML = new StringBuilder();
                tooltipHTML.append("<table border=0 cellpadding=2 ><tr><td valign=top>");
                tooltipHTML.append("<img src=\"VAADIN/themes/reindeer/lightbulb.png\"></td><td>");
                tooltipHTML.append("<b><i>").append(groupName).append("</i></b><br/>");
                tooltipHTML.append(serie.getName());
                if (previous > 0) {
                    double val = (ref - previous)/previous;
                    String color = "red";
                    String sval = FORMAT_PERCENT.format(val);
                    if (val >= 0) {
                        color = "green";
                        sval = "+" + sval;
                    }
                    tooltipHTML.append(" (<font color=\"").append(color).append("\">").append(sval).append("</font>)");    
                }
                tooltipHTML.append(" : ").append(ref).append(" \u20AC");
                tooltipHTML.append("</td><tr></table>");

                return tooltipHTML.toString();
            }
        };
        </#if> 
        bar.addSerie("Sales", new double[] { 1000, 1170, 660, 1030 });
        bar.addSerie("Expenses", new double[] { 400, 460, 1200, 540 });
        bar.addSerie("VAT", new double[] { 20, 46, 78, 13 });
        bar.setGroupNames(new String[] { "2008", "2009", "2010", "2011" });

        bar.setChartWidth(${chartWidth});
        bar.setChartHeight(${chartHeight});
 
        <#if tooltipEnabled == 0>
        bar.setTooltipEnabled(false);
        </#if>
        bar.setBarInset(${barInset});
        bar.setGroupBarInset(${groupBarInset});

        <#if groupAxisEnabled == 1 && groupAxisLabelEnabled == 1>
        bar.addGroupAxisWithLabel(${groupAxisLabelSize});
        <#elseif groupAxisEnabled == 1 && groupAxisLabelEnabled == 0>
        bar.addGroupAxis();
        </#if>
        <#if valueAxisEnabled == 1 && valueAxisLabelEnabled == 1>
        bar.addValueAxisWithLabel(${valueAxisLabelSize}, ${valueAxisLabelMax}, ${valueAxisLabelStep}, "${valueAxisLabelPattern}", "${valueAxisLabelUnit}");
        <#elseif valueAxisEnabled == 1 && valueAxisLabelEnabled == 0>
        bar.addValueAxis();
        </#if>
        
        
        <#if legendEnabled == 1>
        bar.addLegend(${legendSize});
        </#if>
        
        window.addComponent(bar);
    }

}
