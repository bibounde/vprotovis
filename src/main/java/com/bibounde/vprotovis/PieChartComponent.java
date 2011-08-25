package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.bar.TooltipFormatter;
import com.bibounde.vprotovis.chart.pie.DefaultPieLabelFormatter;
import com.bibounde.vprotovis.chart.pie.DefaultPieTooltipFormatter;
import com.bibounde.vprotovis.chart.pie.PieChart;
import com.bibounde.vprotovis.chart.pie.PieLabelFormatter;
import com.bibounde.vprotovis.chart.pie.PieTooltipFormatter;
import com.bibounde.vprotovis.chart.pie.Serie;
import com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent;
import com.bibounde.vprotovis.util.ColorUtil;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Pie charts are useful for performing relative comparisons: how do the parts
 * make up the whole?
 * 
 * @author bibounde
 * 
 */
@ClientWidget(VPieChartComponent.class)
public class PieChartComponent extends AbstractComponent {

    private PieChart chart;
    private String id = "v-protovis-piechart-" + this.hashCode();
    private PieLabelFormatter labelFormatter = new DefaultPieLabelFormatter();
    private PieTooltipFormatter tooltipFormatter = new DefaultPieTooltipFormatter();

    /**
     * Initializes a newly created PieChartComponent
     */
    public PieChartComponent() {
        chart = new PieChart();
    }

    /**
     * Sets the html element id
     * 
     * @param id
     *            html element id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Appends serie value
     * 
     * @param name
     *            name of the serie
     * @param value
     *            value
     * @return serie index
     */
    public int addSerie(String name, double value) {
        return this.chart.addSerie(name, value, false);
    }

    /**
     * Appends serie value with highlight policy. Highlighted serie will be
     * shifted
     * 
     * @param name
     *            name of the serie
     * @param value
     *            value
     * @param highlight
     * @return serie index
     */
    public int addSerie(String name, double value, boolean highlight) {
        return this.chart.addSerie(name, value, highlight);
    }

    /**
     * Clears all serie values
     */
    public void clearSeries() {
        this.chart.getSeries().clear();
    }

    /**
     * Sets chart width (unit : pixels)
     * 
     * @param width
     *            new chart width (unit : pixels)
     */
    public void setChartWidth(double width) {
        chart.setWidth(width);
    }

    /**
     * Sets chart height (unit : pixels)
     * 
     * @param height
     *            new chart height (unit : pixels)
     */
    public void setChartHeight(double height) {
        chart.setHeight(height);
    }

    /**
     * Sets left margin (unit : pixels)
     * 
     * @param marginLeft
     *            left margin (unit : pixels)
     */
    public void setMarginLeft(double marginLeft) {
        chart.setMarginLeft(marginLeft);
    }

    /**
     * Sets right margin (unit : pixels)
     * 
     * @param marginRight
     *            right margin (unit : pixels)
     */
    public void setMarginRight(double marginRight) {
        chart.setMarginRight(marginRight);
    }

    /**
     * Set top margin (unit : pixels)
     * 
     * @param marginTop
     *            top margin (unit : pixels)
     */
    public void setMarginTop(double marginTop) {
        chart.setMarginTop(marginTop);
    }

    /**
     * Sets bottom margin (unit : pixels)
     * 
     * @param marginBottom
     *            bottom margin (unit : pixels)
     */
    public void setMarginBottom(double marginBottom) {
        chart.setMarginBottom(marginBottom);
    }

    /**
     * Sets bar colors
     * 
     * @param colors
     *            new bar colors
     */
    public void setColors(String[] colors) {
        if (colors == null) {
            chart.setColors(ColorUtil.getDefaultColors());
        } else {
            chart.setColors(colors);
        }
    }
    
    /**
     * Sets highlight offset
     * @param highlightOffset new value
     */
    public void setHighlightOffset(double highlightOffset) {
        chart.setHighlightOffset(highlightOffset);
    }

    /**
     * Sets legend visibility
     * 
     * @param visible
     *            legend visibility
     */
    public void setLegendVisible(boolean visible) {
        chart.setLegendEnabled(visible);
        chart.setLegendAreaWidth(visible ? 150d : 0d);
    }

    /**
     * Sets legend area width (unit : pixels)
     * 
     * @param legendAreaWidth
     *            legend area width
     */
    public void setLegendAreaWidth(double legendAreaWidth) {
        chart.setLegendAreaWidth(legendAreaWidth);
    }
    
    /**
     * Sets label visibility
     * @param visible label visibility
     */
    public void setLabelVisible(boolean visible) {
        this.chart.setLabelVisible(true);
    }
    
    /**
     * Sets label color (default black)
     * @param color new color to set
     */
    public void setLabelColor(String color) {
        this.chart.setLabelColor(color);
    }
    
    /**
     * Sets the label formatter
     * @param pieLabelFormatter label formatter
     */
    public void setLabelFormatter(PieLabelFormatter pieLabelFormatter) {
        if (pieLabelFormatter == null) {
            this.labelFormatter = new DefaultPieLabelFormatter();
        } else {
            this.labelFormatter = pieLabelFormatter;
        }
    }
    
    /**
     * Sets the tooltip formatter
     * @param pieTooltipFormatter tooltip formatter
     */
    public void setTooltipFormatter(PieTooltipFormatter pieTooltipFormatter) {
        this.setTooltipFormatter(pieTooltipFormatter, false);
    }
    
    /**
     * Sets the tooltip formatter 
     * @param pieTooltipFormatter tooltip formatter
     * @param permanent if true, tooltip is always displayed. Otherwise, tooltip appears on mouseover
     */
    public void setTooltipFormatter(PieTooltipFormatter pieTooltipFormatter, boolean permanent) {
        this.tooltipFormatter = pieTooltipFormatter;
        this.chart.setTooltipEnabled(pieTooltipFormatter != null);
        this.chart.setTooltipPermanent(permanent);
            
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addVariable(this, VPieChartComponent.UIDL_DIV_ID, this.id);

        this.paintChartValues(target);
        this.paintChartOptions(target);
    }

    private void paintChartValues(PaintTarget target) throws PaintException {

        target.addVariable(this, VPieChartComponent.UIDL_DATA_SERIES_COUNT, this.chart.getSeries().size());

        double sum = 0d;
        int index = 0;
        String[] highlighted = new String[this.chart.getSeries().size()];
        String[] labelValues = new String[this.chart.getSeries().size()];
        String[] tooltips = new String[this.chart.getSeries().size()];
        
        for (Serie serie : this.chart.getSeries()) {
            target.addVariable(this, VPieChartComponent.UIDL_DATA_SERIE_VALUE + index, String.valueOf(serie.getValue()));

            highlighted[index] = String.valueOf(serie.isHighlight());
            labelValues[index] = this.labelFormatter.isVisible(serie.getValue()) ? this.labelFormatter.format(serie.getValue()) : "";
            
            tooltips[index] = this.tooltipFormatter.getTooltipHTML(serie.getName(), serie.getValue());
            target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_TOOLTIP_ENABLED + index, this.tooltipFormatter.isVisible(serie.getName(), serie.getValue()));
            
            sum += serie.getValue();
            index++;
        }

        target.addVariable(this, VPieChartComponent.UIDL_DATA_SERIES_HIGHLIGHTED, highlighted);
        target.addVariable(this, VPieChartComponent.UIDL_DATA_LABEL_VALUES, labelValues);
        if (this.chart.isTooltipEnabled()) {
            target.addVariable(this, VPieChartComponent.UIDL_DATA_TOOLTIP_VALUES, tooltips);
        }
        target.addVariable(this, VPieChartComponent.UIDL_DATA_SERIES_SUM, sum);

    }

    private void paintChartOptions(PaintTarget target) throws PaintException {
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_WIDTH, this.chart.getWidth());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_HEIGHT, this.chart.getHeight());

        double radius = this.getAutoRadius();

        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_RADIUS, radius);
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_LEFT, this.getAutoLeft(radius));
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_BOTTOM, this.getAutoBottom(radius));
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_HIGHLIGHT_OFFSET, this.chart.getHighlightOffset());

        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_MARGIN_LEFT, this.chart.getMarginLeft());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_MARGIN_BOTTOM, this.chart.getMarginBottom());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_MARGIN_RIGHT, this.chart.getMarginRight());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_MARGIN_TOP, this.chart.getMarginTop());

        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_LEGEND_AREA_WIDTH, this.chart.getLegendAreaWidth());
        
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_TOOLTIPS_ENABLED, this.chart.isTooltipEnabled());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_TOOLTIPS_PERMANENT, this.chart.isTooltipPermanent());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_LABEL_ENABLED, this.chart.isLabelVisible());
        target.addVariable(this, VPieChartComponent.UIDL_OPTIONS_LABEL_COLOR, this.chart.getLabelColor());
    }

    private double getAutoRadius() {
        double availableWidth = this.chart.getWidth() - this.chart.getMarginLeft() - this.chart.getMarginRight() - this.chart.getLegendAreaWidth();
        double availableHeight = this.chart.getHeight() - this.chart.getMarginTop() - this.chart.getMarginBottom();

        return (Math.min(availableWidth, availableHeight) - (this.chart.getHighlightOffset() * 2)) / 2;
    }

    private double getAutoLeft(double radius) {
        return radius + this.chart.getMarginLeft() + this.chart.getHighlightOffset();
    }

    private double getAutoBottom(double radius) {
        return radius + this.chart.getMarginBottom() + this.chart.getHighlightOffset();
    }
}
