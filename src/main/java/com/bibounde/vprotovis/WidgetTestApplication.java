package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.InterpolationMode;
import com.bibounde.vprotovis.chart.bar.BarTooltipFormatter;
import com.bibounde.vprotovis.chart.line.LineTooltipFormatter;
import com.bibounde.vprotovis.chart.pie.PieLabelFormatter;
import com.bibounde.vprotovis.chart.pie.PieTooltipFormatter;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.Point;
import com.vaadin.Application;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class WidgetTestApplication extends Application {
    private Window window;

    public void initClem() {
        window = new Window("Widget Test");
        setMainWindow(window);

        //this.runBar();
        //this.runLine();
        this.runPie();
        //this.runSpider();
        //this.runArea();
    }
    
    public void initBug() {
        Window mainWindow = new Window("Graph Application");
        AreaChartComponent area = new AreaChartComponent();
        area.addSerie("Serie_0", new Point[] { new Point(0d, 1000d), new Point(1d, 1170d), new Point(2d, 660d), new Point(3d, 1030d) });
        area.addSerie("Serie_1", new Point[] { new Point(0d, -933d), new Point(1d, 1759d), new Point(2d, -783d), new Point(3d, -358d) });
        area.addSerie("Serie_2", new Point[] { new Point(0d, -271d), new Point(1d, 420d), new Point(2d, 869d), new Point(3d, -508d) });
        area.setChartWidth(600d);
        area.setChartHeight(300d);

        area.setMarginLeft(50d);

        area.setMarginBottom(50d);

        area.setXAxisVisible(true);
        area.setXAxisLabelVisible(true);
        area.setXAxisLabelStep(0.5d);
        area.setXAxisGridVisible(true);

        area.setYAxisVisible(true);
        area.setYAxisLabelVisible(true);
        area.setYAxisLabelStep(300d);
        area.setYAxisGridVisible(true);

        area.setLegendVisible(true);
        area.setLegendAreaWidth(150d);

        area.setTooltipEnabled(true);

        AbsoluteLayout layPreview = new AbsoluteLayout();

        //layPreview.addComponent(area, "top:0px;left:0px;");
        layPreview.setWidth("600px");
        layPreview.setHeight("300px");

        Window win = new Window();
        win.setWidth("600px");
        win.setHeight("300px");
        
        win.addComponent(area);

        mainWindow.addWindow(win);
        
        //mainWindow.addComponent(area);

        setMainWindow(mainWindow);
    }
    
    public void init() {
        Window mainWindow = new Window("Graph Application");
        setMainWindow(mainWindow);
        
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

        AbsoluteLayout layPreview = new AbsoluteLayout();

        layPreview.addComponent(bar, "top:0px;left:0px;");
        //layPreview.setWidth("600px");
        //layPreview.setHeight("300px");

        Window win = new Window();
        win.setWidth("600px");
        win.setHeight("300px");
        
        win.addComponent(bar);

        mainWindow.addWindow(win);
        
        //mainWindow.addComponent(win);
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
        
        VerticalSplitPanel splitPanel = new VerticalSplitPanel();
        splitPanel.setSplitPosition(40);
        splitPanel.setWidth("100%");
        splitPanel.setHeight("500px");
        
        splitPanel.addComponent(new Label("Empty"));
        
        Panel content = new Panel();
        content.setSizeFull();
        
        VerticalLayout mainLayout = (VerticalLayout) content.getContent();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        Panel chartContent = new Panel();
        content.addComponent(chartContent);
        
        chartContent.addComponent(line);
        
        splitPanel.addComponent(content);
        
        window.addComponent(splitPanel);
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
        
        pie.setLabelVisible(true);
        pie.setLabelColor("#FFFFFF");
        
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
        }, true);
        
        pie.setLegendVisible(true);
        pie.setLegendAreaWidth(150d);
        pie.setLegendInsetLeft(50);
        
        //pie.setLineWidth(2);
        
        window.addComponent(pie);
    }
    
    private void runSpider() {
        SpiderChartComponent spider = new SpiderChartComponent();
        
        spider.setChartWidth(200);
        spider.setChartHeight(200);
        
        spider.addSerie("Pierre", new double[]{1d, 2d, 3d, 4d, 5d});
        spider.addSerie("Julien", new double[]{4d, 4d, 1d, 4d, 3d});
        
        spider.setAxisNames(new String[]{"GWT", "Protovis", "Vaadin", "Maven", "Jenkins"});
        //spider.setAxisCaptionVisible(false);
        
        spider.setMarginLeft(40);
        spider.setMarginBottom(40);
        spider.setMarginTop(40);
        
        spider.setLineWidth(2);
        //spider.setAreaOpacity(0.7d);
        spider.setLegendInsetLeft(60);
        spider.setLegendVisible(true);
        spider.setTooltipEnabled(true);
        
        Panel panel = new Panel();
        panel.addComponent(spider);
        
        window.addComponent(panel);
    }
    
    private void runArea() {
        
        AreaChartComponent area = new AreaChartComponent();
        
        area.addSerie("Sales", new Point[]{new Point(0, 1000), new Point(1, 1170), new Point(2, 660), new Point(3, 1030)});
        area.addSerie("Expenses", new Point[]{new Point(0, 400), new Point(1, 460), new Point(2, 1200), new Point(3, 540)});
        area.addSerie("Expenses- NegY", new Point[]{new Point(0, 0), new Point(1, -1360), new Point(2, 12), new Point(3, 210)});
        
        area.setId("protovis");
        area.setChartWidth(450);
        area.setChartHeight(300);
        area.setInterpolationMode(InterpolationMode.CARDINAL);
        //line.setLineWidth(4);
        //line.setColors(new String[]{"#9c9ede", "#cedb9c", "#de9ed6"});
        
        area.setLegendVisible(true);
        area.setLegendAreaWidth(150d);
        area.setLegendInsetLeft(50);
        
        area.setXAxisVisible(true);
        area.setXAxisLabelVisible(true);
        area.setXAxisLabelStep(0.5);
        area.setXAxisGridVisible(true);
        area.setMarginLeft(50);
        area.setMarginBottom(50);
        //line.addVerticalAxis(-1200, 1200, 300);
       
        area.setYAxisVisible(true);
        area.setYAxisLabelVisible(true);
        area.setYAxisLabelStep(300);
        area.setYAxisGridVisible(true);
        
        area.setAreaOpacity(0.3d);
        
        VerticalSplitPanel splitPanel = new VerticalSplitPanel();
        splitPanel.setSplitPosition(40);
        splitPanel.setWidth("100%");
        splitPanel.setHeight("500px");
        
        splitPanel.addComponent(new Label("Empty"));
        
        Panel content = new Panel();
        content.setSizeFull();
        
        VerticalLayout mainLayout = (VerticalLayout) content.getContent();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        Panel chartContent = new Panel();
        content.addComponent(chartContent);
        
        chartContent.addComponent(area);
        
        splitPanel.addComponent(content);
        
        window.addComponent(splitPanel);
    }
}
