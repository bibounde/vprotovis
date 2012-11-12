package com.bibounde.vprotovis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bibounde.vprotovis.chart.InterpolationMode;
import com.bibounde.vprotovis.chart.bar.BarTooltipFormatter;
import com.bibounde.vprotovis.chart.line.DefaultLineTooltipFormatter;
import com.bibounde.vprotovis.chart.line.LineChart;
import com.bibounde.vprotovis.chart.line.LineTooltipFormatter;
import com.bibounde.vprotovis.chart.line.Serie;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.DefaultAxisLabelFormatter;
import com.bibounde.vprotovis.common.Padding;
import com.bibounde.vprotovis.common.Point;
import com.bibounde.vprotovis.common.Range;
import com.bibounde.vprotovis.common.Rectangle;
import com.bibounde.vprotovis.gwt.client.line.VLineChartComponent;
import com.bibounde.vprotovis.gwt.client.pie.VPieChartComponent;
import com.bibounde.vprotovis.util.ColorUtil;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Component used to display line charts. Line charts are often used to
 * visualize time series data, encoding the value of a variable over time using
 * position. Typically, linear interpolation is used between samples. However,
 * in some cases, the data does not vary smoothly, but instead changes in
 * response to discrete events.
 * 
 * @author bibounde
 * 
 */
@ClientWidget(VLineChartComponent.class)
public class LineChartComponent extends AbstractChartComponent {

    private AxisLabelFormatter xAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private AxisLabelFormatter yAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private LineTooltipFormatter tooltipFormatter = new DefaultLineTooltipFormatter();

    /**
     * Initializes a newly created LineChartComponent
     */
    public LineChartComponent() {
        super(new LineChart());
        this.setId("v-protovis-linechart-" + this.hashCode());
    }
    
    public LineChart getLineChart() {
        return (LineChart) this.chart;
    }

    /**
     * Appends serie values
     * @param name name of the serie
     * @param values values
     * @return serie index
     */
    public int addSerie(String name, Point[] values) {
        return this.getLineChart().addSerie(name, values);
    }

    /**
     * Clears all serie values
     */
    public void clearSeries() {
        this.getLineChart().getSeries().clear();
    }

    /**
     * Sets visibility of x axis
     * @param visible x axis visibility
     */
    public void setXAxisVisible(boolean visible) {
        this.getLineChart().setXAxisEnabled(visible);
        this.getLineChart().setXAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of x axis labels
     * @param visible x axis label visibility
     */
    public void setXAxisLabelVisible(boolean visible) {
        this.getLineChart().setXAxisEnabled(this.getLineChart().isXAxisEnabled() || visible);
        this.getLineChart().setXAxisLabelEnabled(visible);
    }

    /**
     * Sets x axis label step (used to display ticks on x axis)
     * @param step x axis label step
     */
    public void setXAxisLabelStep(double step) {
        this.getLineChart().setXAxisLabelStep(step);
    }

    /**
     * Sets visibility of vertical lines
     * @param visible vertical line visibility
     */
    public void setXAxisGridVisible(boolean visible) {
        this.getLineChart().setXAxisEnabled(this.getLineChart().isXAxisEnabled() || visible);
        this.getLineChart().setXAxisGridEnabled(visible);
    }

    /**
     * Sets visibility of y axis
     * @param visible y axis visibility
     */
    public void setYAxisVisible(boolean visible) {
        this.getLineChart().setYAxisEnabled(visible);
        this.getLineChart().setYAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of y axis labels
     * @param visible y axis label visibility
     */
    public void setYAxisLabelVisible(boolean visible) {
        this.getLineChart().setYAxisEnabled(this.getLineChart().isYAxisEnabled() || visible);
        this.getLineChart().setYAxisLabelEnabled(visible);
    }

    /**
     * Sets y axis label step (used to display ticks on y axis)
     * @param step y axis label step
     */
    public void setYAxisLabelStep(double step) {
        this.getLineChart().setYAxisLabelStep(step);
    }

    /**
     * Sets visibility of horizontal lines
     * @param visible horizontal line visibility
     */
    public void setYAxisGridVisible(boolean visible) {
        this.getLineChart().setYAxisEnabled(this.getLineChart().isYAxisEnabled() || visible);
        this.getLineChart().setYAxisGridEnabled(visible);
    }
    /**
     * Sets How to interpolate between values
     * @param interpolationMode interpolation mode
     */
    public void setInterpolationMode(InterpolationMode interpolationMode) {
        this.getLineChart().setInterpolationMode(interpolationMode);
    }

    /**
     * Sets tooltip formatter
     * @param tooltipFormatter tooltip formatter
     */
    public void setTooltipFormatter(LineTooltipFormatter tooltipFormatter) {
        this.tooltipFormatter = tooltipFormatter;
        chart.setTooltipEnabled(tooltipFormatter != null);
    }

    
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        double xMax = 0d, xMin = 0d, yMax = 0d, yMin = 0d;
        for (Serie serie : this.getLineChart().getSeries()) {
            for (Point value : serie.getValues()) {
                xMax = xMax < value.getX() ? value.getX() : xMax;
                xMin = xMin > value.getX() ? value.getX() : xMin;
                yMax = yMax < value.getY() ? value.getY() : yMax;
                yMin = yMin > value.getY() ? value.getY() : yMin;
            }
        }

        Padding padding = new Padding(10d, xMin < 0 ? 10d : 0d, 10d, yMin < 0 ? 10d : 0d);

        this.paintChartValues(target);
        this.paintChartOptions(target, new Rectangle(xMin, yMin, xMax, yMax), padding);
    }

    private void paintChartValues(PaintTarget target) throws PaintException {

        target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIES_COUNT, this.getLineChart().getSeries().size());
        
        String[] serieNames = new String[this.getLineChart().getSeries().size()];

        int index = 0;
        for (Serie serie : this.getLineChart().getSeries()) {
            // Create data
            List<String> values = new ArrayList<String>();
            
            List<String> tooltips = new ArrayList<String>();
            
            int valIndex = 0;
            for (Point value : serie.getValues()) {
                
                values.add(String.valueOf(value.getX()) + "|" + String.valueOf(value.getY()));
                
                 // Tooltips
                if (this.getLineChart().isTooltipEnabled()) {
                    tooltips.add(this.tooltipFormatter.getTooltipHTML(serie.getName(), serie.getValues()[valIndex]));
                }
                valIndex++;
            }
            target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIE_TOOLTIP_VALUES + index, tooltips.toArray(new String[tooltips.size()]));

            target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIE_VALUES + index, values.toArray(new String[values.size()]));

            serieNames[index] = serie.getName();
            index++;
        }
        target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIES_NAMES, serieNames);
    }

    private void paintChartOptions(PaintTarget target, Rectangle dataRect, Padding padding) throws PaintException {

        double bottom = this.getAutoBottom(dataRect);
        double left = this.getAutoLeft(dataRect);
        Range rangeY = Range.getAutoRange(dataRect.getY1(), dataRect.getY2(), this.getLineChart().getYAxisLabelStep());
        Range rangeX = Range.getAutoRange(dataRect.getX1(), dataRect.getX2(), this.getLineChart().getXAxisLabelStep());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_BOTTOM, bottom);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEFT, left);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_BOTTOM, this.getAutoLineBottom(dataRect, bottom, padding));
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_LEFT, this.getAutoLineLeft(dataRect, left, padding));
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_LEFT, padding.getLeft());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_RIGHT, padding.getRight());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_TOP, padding.getTop());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_BOTTOM, padding.getBottom());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_ENABLED, this.getLineChart().isXAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_ENABLED, this.getLineChart().isXAxisLabelEnabled());
        // 0 label will be displayed only if y min value is greather than 0
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_ZERO_ENABLED, dataRect.getY1() >= 0);

        // Add x axis values and their formatted text
        Double[] rangeXValues = rangeX.getRangeArray();
        String[] rangeXSValues = new String[rangeXValues.length];
        for (int i = 0; i < rangeXValues.length; i++) {
            rangeXSValues[i] = this.xAxisLabelFormatter.format(rangeXValues[i]);
        }
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_RANGE_D_VALUES, rangeX.getRangeArrayAsString());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_RANGE_S_VALUES, rangeXSValues);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_GRID_ENABLED, this.getLineChart().isXAxisGridEnabled());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_ENABLED, this.getLineChart().isYAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_ENABLED, this.getLineChart().isYAxisLabelEnabled());
        // 0 label will be displayed only if y min value is greather than 0
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_ZERO_ENABLED, dataRect.getX1() >= 0);

        // Add y axis values and their formatted text
        Double[] rangeYValues = rangeY.getRangeArray();
        String[] rangeYSValues = new String[rangeYValues.length];
        for (int i = 0; i < rangeYValues.length; i++) {
            rangeYSValues[i] = this.yAxisLabelFormatter.format(rangeYValues[i]);
        }
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_D_VALUES, rangeY.getRangeArrayAsString());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_S_VALUES, rangeYSValues);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_GRID_ENABLED, this.getLineChart().isYAxisGridEnabled());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_INTERPOLATION_MODE, this.getLineChart().getInterpolationMode().getJsValue());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_WIDTH, this.getLineChart().getLineWidth());
    }

    protected double getAutoBottom(Rectangle dataRect) {
        if (dataRect.getY1() < 0) {
            // Axis is in the center of chart
            return (this.getLineChart().getHeight() - this.getLineChart().getMarginBottom() - this.getLineChart().getMarginTop()) / 2 + this.getLineChart().getMarginBottom();
        } else {
            return 0d + this.getLineChart().getMarginBottom();
        }
    }

    protected double getAutoLeft(Rectangle dataRect) {
        if (dataRect.getX1() < 0) {
            // Axis is in the center of chart
            return (this.getLineChart().getWidth() - this.getLineChart().getLegendAreaWidth() - this.chart.getLegendInsetLeft() - this.getLineChart().getMarginLeft() - this.getLineChart().getMarginRight()) / 2 + this.getLineChart().getMarginLeft();
        } else {
            return 0 + this.getLineChart().getMarginLeft();
        }
    }

    protected double getAutoLineBottom(Rectangle dataRect, double bottom, Padding padding) {
        double availableHeight = this.getLineChart().getHeight() - bottom - this.getLineChart().getMarginTop() - padding.getTop() - padding.getBottom();

        double minAbs = Math.abs(dataRect.getY1());
        double max = minAbs > dataRect.getY2() ? minAbs : dataRect.getY2();

        return availableHeight / max;
    }

    protected double getAutoLineLeft(Rectangle dataRect, double left, Padding padding) {
        double availableWidth = this.getLineChart().getWidth() - left - this.getLineChart().getMarginRight() - padding.getRight() - padding.getLeft() - this.getLineChart().getLegendAreaWidth() - this.chart.getLegendInsetLeft();

        double minAbs = Math.abs(dataRect.getX1());
        double max = minAbs > dataRect.getX2() ? minAbs : dataRect.getX2();

        return availableWidth / max;
    }

    /**
     * Sets x axis label formatter
     * @param xAxisLabelFormatter x axis label formatter
     */
    public void setXAxisLabelFormatter(AxisLabelFormatter xAxisLabelFormatter) {
        if (xAxisLabelFormatter == null) {
            this.xAxisLabelFormatter = new DefaultAxisLabelFormatter();
        } else {
            this.xAxisLabelFormatter = xAxisLabelFormatter;
        }
    }

    /**
     * Sets y axis label formatter
     * @param yAxisLabelFormatter y axis label formatter
     */
    public void setYAxisLabelFormatter(AxisLabelFormatter yAxisLabelFormatter) {
        if (yAxisLabelFormatter == null) {
            this.yAxisLabelFormatter = new DefaultAxisLabelFormatter();
        } else {
            this.yAxisLabelFormatter = yAxisLabelFormatter;
        }
    }
}
