package com.bibounde.vprotovis;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.InterpolationMode;
import com.bibounde.vprotovis.chart.area.AreaChart;
import com.bibounde.vprotovis.chart.area.AreaTooltipFormatter;
import com.bibounde.vprotovis.chart.area.DefaultAreaTooltipFormatter;
import com.bibounde.vprotovis.chart.area.Serie;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.DefaultAxisLabelFormatter;
import com.bibounde.vprotovis.common.Padding;
import com.bibounde.vprotovis.common.Point;
import com.bibounde.vprotovis.common.Range;
import com.bibounde.vprotovis.common.Rectangle;
import com.bibounde.vprotovis.gwt.client.area.VAreaChartComponent;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;

/**
 * Component used to display area charts. It is based on the line chart.
 * 
 * @author bibounde
 * 
 */
@ClientWidget(VAreaChartComponent.class)
public class AreaChartComponent extends AbstractChartComponent {

    private AxisLabelFormatter xAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private AxisLabelFormatter yAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private AreaTooltipFormatter tooltipFormatter = new DefaultAreaTooltipFormatter();

    /**
     * Initializes a newly created LineChartComponent
     */
    public AreaChartComponent() {
        super(new AreaChart());
        this.setId("v-protovis-areachart-" + this.hashCode());
    }
    
    public AreaChart getAreaChart() {
        return (AreaChart) this.chart;
    }

    /**
     * Appends serie values
     * @param name name of the serie
     * @param values values
     * @return serie index
     */
    public int addSerie(String name, Point[] values) {
        return this.getAreaChart().addSerie(name, values);
    }

    /**
     * Clears all serie values
     */
    public void clearSeries() {
        this.getAreaChart().getSeries().clear();
    }

    /**
     * Sets visibility of x axis
     * @param visible x axis visibility
     */
    public void setXAxisVisible(boolean visible) {
        this.getAreaChart().setXAxisEnabled(visible);
        this.getAreaChart().setXAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of x axis labels
     * @param visible x axis label visibility
     */
    public void setXAxisLabelVisible(boolean visible) {
        this.getAreaChart().setXAxisEnabled(this.getAreaChart().isXAxisEnabled() || visible);
        this.getAreaChart().setXAxisLabelEnabled(visible);
    }

    /**
     * Sets x axis label step (used to display ticks on x axis)
     * @param step x axis label step
     */
    public void setXAxisLabelStep(double step) {
        this.getAreaChart().setXAxisLabelStep(step);
    }

    /**
     * Sets visibility of vertical lines
     * @param visible vertical line visibility
     */
    public void setXAxisGridVisible(boolean visible) {
        this.getAreaChart().setXAxisEnabled(this.getAreaChart().isXAxisEnabled() || visible);
        this.getAreaChart().setXAxisGridEnabled(visible);
    }

    /**
     * Sets visibility of y axis
     * @param visible y axis visibility
     */
    public void setYAxisVisible(boolean visible) {
        this.getAreaChart().setYAxisEnabled(visible);
        this.getAreaChart().setYAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of y axis labels
     * @param visible y axis label visibility
     */
    public void setYAxisLabelVisible(boolean visible) {
        this.getAreaChart().setYAxisEnabled(this.getAreaChart().isYAxisEnabled() || visible);
        this.getAreaChart().setYAxisLabelEnabled(visible);
    }

    /**
     * Sets y axis label step (used to display ticks on y axis)
     * @param step y axis label step
     */
    public void setYAxisLabelStep(double step) {
        this.getAreaChart().setYAxisLabelStep(step);
    }

    /**
     * Sets visibility of horizontal lines
     * @param visible horizontal line visibility
     */
    public void setYAxisGridVisible(boolean visible) {
        this.getAreaChart().setYAxisEnabled(this.getAreaChart().isYAxisEnabled() || visible);
        this.getAreaChart().setYAxisGridEnabled(visible);
    }
    /**
     * Sets How to interpolate between values
     * @param interpolationMode interpolation mode
     */
    public void setInterpolationMode(InterpolationMode interpolationMode) {
        this.getAreaChart().setInterpolationMode(interpolationMode);
    }

    /**
     * Sets line width
     * @param lineWidth linewidth to set
     */
    public void setLineWidth(int lineWidth) {
        this.getAreaChart().setLineWidth(lineWidth);
    }
    
    /**
     * Sets the area opacity
     * @param opacity area opacity (must be in [0, 1])
     */
    public void setAreaOpacity(double opacity) {
        if (opacity < 0 || opacity > 1) {
            throw new IllegalArgumentException("Opacity must be in [0, 1]");
        }
        this.getAreaChart().setOpacity(opacity);
    }
    
    /**
     * Sets tooltip formatter
     * @param tooltipFormatter tooltip formatter
     */
    public void setTooltipFormatter(AreaTooltipFormatter tooltipFormatter) {
        this.tooltipFormatter = tooltipFormatter;
        chart.setTooltipEnabled(tooltipFormatter != null);
    }

    
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        double xMax = 0d, xMin = 0d, yMax = 0d, yMin = 0d;
        for (Serie serie : this.getAreaChart().getSeries()) {
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

        target.addVariable(this, VAreaChartComponent.UIDL_DATA_SERIES_COUNT, this.getAreaChart().getSeries().size());
        
        String[] serieNames = new String[this.getAreaChart().getSeries().size()];

        int index = 0;
        for (Serie serie : this.getAreaChart().getSeries()) {
            // Create data
            List<String> values = new ArrayList<String>();
            
            List<String> tooltips = new ArrayList<String>();
            
            int valIndex = 0;
            for (Point value : serie.getValues()) {
                
                values.add(String.valueOf(value.getX()) + "|" + String.valueOf(value.getY()));
                
                 // Tooltips
                if (this.getAreaChart().isTooltipEnabled()) {
                    tooltips.add(this.tooltipFormatter.getTooltipHTML(serie.getName(), serie.getValues()[valIndex]));
                }
                valIndex++;
            }
            target.addVariable(this, VAreaChartComponent.UIDL_DATA_SERIE_TOOLTIP_VALUES + index, tooltips.toArray(new String[tooltips.size()]));

            target.addVariable(this, VAreaChartComponent.UIDL_DATA_SERIE_VALUES + index, values.toArray(new String[values.size()]));

            serieNames[index] = serie.getName();
            index++;
        }
        target.addVariable(this, VAreaChartComponent.UIDL_DATA_SERIES_NAMES, serieNames);
    }

    private void paintChartOptions(PaintTarget target, Rectangle dataRect, Padding padding) throws PaintException {

        double bottom = this.getAutoBottom(dataRect);
        double left = this.getAutoLeft(dataRect);
        Range rangeY = Range.getAutoRange(dataRect.getY1(), dataRect.getY2(), this.getAreaChart().getYAxisLabelStep());
        Range rangeX = Range.getAutoRange(dataRect.getX1(), dataRect.getX2(), this.getAreaChart().getXAxisLabelStep());

        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_BOTTOM, bottom);
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_LEFT, left);
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_AREA_BOTTOM, this.getAutoLineBottom(dataRect, bottom, padding));
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_AREA_LEFT, this.getAutoLineLeft(dataRect, left, padding));
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_PADDING_LEFT, padding.getLeft());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_PADDING_RIGHT, padding.getRight());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_PADDING_TOP, padding.getTop());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_PADDING_BOTTOM, padding.getBottom());

        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_ENABLED, this.getAreaChart().isXAxisEnabled());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_ENABLED, this.getAreaChart().isXAxisLabelEnabled());
        // 0 label will be displayed only if y min value is greather than 0
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_ZERO_ENABLED, dataRect.getY1() >= 0);

        // Add x axis values and their formatted text
        Double[] rangeXValues = rangeX.getRangeArray();
        String[] rangeXSValues = new String[rangeXValues.length];
        for (int i = 0; i < rangeXValues.length; i++) {
            rangeXSValues[i] = this.xAxisLabelFormatter.format(rangeXValues[i]);
        }
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_RANGE_D_VALUES, rangeX.getRangeArrayAsString());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_RANGE_S_VALUES, rangeXSValues);
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_X_AXIS_GRID_ENABLED, this.getAreaChart().isXAxisGridEnabled());

        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_ENABLED, this.getAreaChart().isYAxisEnabled());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_ENABLED, this.getAreaChart().isYAxisLabelEnabled());
        // 0 label will be displayed only if y min value is greather than 0
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_ZERO_ENABLED, dataRect.getX1() >= 0);

        // Add y axis values and their formatted text
        Double[] rangeYValues = rangeY.getRangeArray();
        String[] rangeYSValues = new String[rangeYValues.length];
        for (int i = 0; i < rangeYValues.length; i++) {
            rangeYSValues[i] = this.yAxisLabelFormatter.format(rangeYValues[i]);
        }
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_D_VALUES, rangeY.getRangeArrayAsString());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_RANGE_S_VALUES, rangeYSValues);
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_Y_AXIS_GRID_ENABLED, this.getAreaChart().isYAxisGridEnabled());

        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_INTERPOLATION_MODE, this.getAreaChart().getInterpolationMode().getJsValue());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_LINE_WIDTH, this.getAreaChart().getLineWidth());
        target.addVariable(this, VAreaChartComponent.UIDL_OPTIONS_AREA_OPACITY, this.getAreaChart().getOpacity());
    }

    protected double getAutoBottom(Rectangle dataRect) {
        if (dataRect.getY1() < 0) {
            // Axis is in the center of chart
            return (this.getAreaChart().getHeight() - this.getAreaChart().getMarginBottom() - this.getAreaChart().getMarginTop()) / 2 + this.getAreaChart().getMarginBottom();
        } else {
            return 0d + this.getAreaChart().getMarginBottom();
        }
    }

    protected double getAutoLeft(Rectangle dataRect) {
        if (dataRect.getX1() < 0) {
            // Axis is in the center of chart
            return (this.getAreaChart().getWidth() - this.getAreaChart().getLegendAreaWidth() - this.chart.getLegendInsetLeft() - this.getAreaChart().getMarginLeft() - this.getAreaChart().getMarginRight()) / 2 + this.getAreaChart().getMarginLeft();
        } else {
            return 0 + this.getAreaChart().getMarginLeft();
        }
    }

    protected double getAutoLineBottom(Rectangle dataRect, double bottom, Padding padding) {
        double availableHeight = this.getAreaChart().getHeight() - bottom - this.getAreaChart().getMarginTop() - padding.getTop() - padding.getBottom();

        double minAbs = Math.abs(dataRect.getY1());
        double max = minAbs > dataRect.getY2() ? minAbs : dataRect.getY2();

        return availableHeight / max;
    }

    protected double getAutoLineLeft(Rectangle dataRect, double left, Padding padding) {
        double availableWidth = this.getAreaChart().getWidth() - left - this.getAreaChart().getMarginRight() - padding.getRight() - padding.getLeft() - this.getAreaChart().getLegendAreaWidth() - this.chart.getLegendInsetLeft();

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
