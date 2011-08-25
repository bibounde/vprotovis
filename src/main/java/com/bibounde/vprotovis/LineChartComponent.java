package com.bibounde.vprotovis;

import java.util.ArrayList;
import java.util.List;

import com.bibounde.vprotovis.chart.line.InterpolationMode;
import com.bibounde.vprotovis.chart.line.LineChart;
import com.bibounde.vprotovis.chart.line.Serie;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.DefaultAxisLabelFormatter;
import com.bibounde.vprotovis.common.Padding;
import com.bibounde.vprotovis.common.Point;
import com.bibounde.vprotovis.common.Range;
import com.bibounde.vprotovis.common.Rectangle;
import com.bibounde.vprotovis.gwt.client.line.VLineChartComponent;
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
public class LineChartComponent extends AbstractComponent {

    private LineChart chart;
    private String id = "v-protovis-linechart-" + this.hashCode();
    private AxisLabelFormatter xAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private AxisLabelFormatter yAxisLabelFormatter = new DefaultAxisLabelFormatter();

    /**
     * Initializes a newly created LineChartComponent
     */
    public LineChartComponent() {
        chart = new LineChart();
    }

    /**
     * Sets the html element id
     * @param id html element id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Appends serie values
     * @param name name of the serie
     * @param values values
     * @return serie index
     */
    public int addSerie(String name, Point[] values) {
        return this.chart.addSerie(name, values);
    }

    /**
     * Clears all serie values
     */
    public void clearSeries() {
        this.chart.getSeries().clear();
    }

    /**
     * Sets chart width (unit : pixels)
     * @param width new chart width (unit : pixels)
     */
    public void setChartWidth(double width) {
        chart.setWidth(width);
    }

    /**
     * Sets chart height (unit : pixels)
     * @param height new chart height (unit : pixels)
     */
    public void setChartHeight(double height) {
        chart.setHeight(height);
    }

    /**
     * Sets line colors
     * @param colors new line colors
     */
    public void setColors(String[] colors) {
        if (colors == null) {
            chart.setColors(ColorUtil.getDefaultColors());
        } else {
            chart.setColors(colors);
        }
    }

    /**
     * Sets visibility of x axis
     * @param visible x axis visibility
     */
    public void setXAxisVisible(boolean visible) {
        this.chart.setXAxisEnabled(visible);
        this.chart.setXAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of x axis labels
     * @param visible x axis label visibility
     */
    public void setXAxisLabelVisible(boolean visible) {
        this.chart.setXAxisEnabled(this.chart.isXAxisEnabled() || visible);
        this.chart.setXAxisLabelEnabled(visible);
    }

    /**
     * Sets x axis label step (used to display ticks on x axis)
     * @param step x axis label step
     */
    public void setXAxisLabelStep(double step) {
        this.chart.setXAxisLabelStep(step);
    }

    /**
     * Sets visibility of vertical lines
     * @param visible vertical line visibility
     */
    public void setXAxisGridVisible(boolean visible) {
        this.chart.setXAxisEnabled(this.chart.isXAxisEnabled() || visible);
        this.chart.setXAxisGridEnabled(visible);
    }

    /**
     * Sets visibility of y axis
     * @param visible y axis visibility
     */
    public void setYAxisVisible(boolean visible) {
        this.chart.setYAxisEnabled(visible);
        this.chart.setYAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of y axis labels
     * @param visible y axis label visibility
     */
    public void setYAxisLabelVisible(boolean visible) {
        this.chart.setYAxisEnabled(this.chart.isYAxisEnabled() || visible);
        this.chart.setYAxisLabelEnabled(visible);
    }

    /**
     * Sets y axis label step (used to display ticks on y axis)
     * @param step y axis label step
     */
    public void setYAxisLabelStep(double step) {
        this.chart.setYAxisLabelStep(step);
    }

    /**
     * Sets visibility of horizontal lines
     * @param visible horizontal line visibility
     */
    public void setYAxisGridVisible(boolean visible) {
        this.chart.setYAxisEnabled(this.chart.isYAxisEnabled() || visible);
        this.chart.setYAxisGridEnabled(visible);
    }

    /**
     * Sets left margin (unit : pixels)
     * @param marginLeft left margin (unit : pixels)
     */
    public void setMarginLeft(double marginLeft) {
        chart.setMarginLeft(marginLeft);
    }

    /**
     * Sets right margin (unit : pixels)
     * @param marginRight right margin (unit : pixels)
     */
    public void setMarginRight(double marginRight) {
        chart.setMarginRight(marginRight);
    }

    /**
     * Set top margin (unit : pixels)
     * @param marginTop top margin (unit : pixels)
     */
    public void setMarginTop(double marginTop) {
        chart.setMarginTop(marginTop);
    }

    /**
     * Sets bottom margin (unit : pixels)
     * @param marginBottom bottom margin (unit : pixels)
     */
    public void setMarginBottom(double marginBottom) {
        chart.setMarginBottom(marginBottom);
    }

    /**
     * Sets How to interpolate between values
     * @param interpolationMode interpolation mode
     */
    public void setInterpolationMode(InterpolationMode interpolationMode) {
        chart.setInterpolationMode(interpolationMode);
    }

    /**
     * Sets line width
     * @param lineWidth linewidth to set
     */
    public void setLineWidth(int lineWidth) {
        chart.setLineWidth(lineWidth);
    }

    /**
     * Sets legend visibility
     * @param visible legend visibility
     */
    public void setLegendVisible(boolean visible) {
        chart.setLegendEnabled(visible);
        chart.setLegendAreaWidth(visible ? 150d : 0d);
    }

    /**
     * Sets legend area width (unit : pixels)
     * @param legendAreaWidth legend area width
     */
    public void setLegendAreaWidth(double legendAreaWidth) {
        chart.setLegendAreaWidth(legendAreaWidth);
    }

    
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addVariable(this, VLineChartComponent.UIDL_DIV_ID, this.id);

        double xMax = 0d, xMin = 0d, yMax = 0d, yMin = 0d;
        for (Serie serie : this.chart.getSeries()) {
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

        target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIES_COUNT, this.chart.getSeries().size());

        String[] serieNames = new String[this.chart.getSeries().size()];

        int index = 0;
        for (Serie serie : this.chart.getSeries()) {
            // Create data
            List<String> values = new ArrayList<String>();
            for (Point value : serie.getValues()) {
                values.add(String.valueOf(value.getX()) + "|" + String.valueOf(value.getY()));
            }

            target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIE_VALUES + index, values.toArray(new String[values.size()]));

            serieNames[index] = serie.getName();
            index++;
        }
        target.addVariable(this, VLineChartComponent.UIDL_DATA_SERIES_NAMES, serieNames);
    }

    private void paintChartOptions(PaintTarget target, Rectangle dataRect, Padding padding) throws PaintException {

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_WIDTH, this.chart.getWidth());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HEIGHT, this.chart.getHeight());

        double bottom = this.getAutoBottom(dataRect);
        double left = this.getAutoLeft(dataRect);
        Range rangeY = Range.getAutoRange(dataRect.getY1(), dataRect.getY2(), this.chart.getYAxisLabelStep());
        Range rangeX = Range.getAutoRange(dataRect.getX1(), dataRect.getX2(), this.chart.getXAxisLabelStep());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_BOTTOM, bottom);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEFT, left);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_BOTTOM, this.getAutoLineBottom(dataRect, bottom, padding));
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_LEFT, this.getAutoLineLeft(dataRect, left, padding));
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_MARGIN_LEFT, this.chart.getMarginLeft());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_MARGIN_RIGHT, this.chart.getMarginRight());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_MARGIN_TOP, this.chart.getMarginTop());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_MARGIN_BOTTOM, this.chart.getMarginBottom());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_LEFT, padding.getLeft());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_RIGHT, padding.getRight());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_TOP, padding.getTop());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_PADDING_BOTTOM, padding.getBottom());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_ENABLED, this.chart.isXAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_LABEL_ENABLED, this.chart.isXAxisLabelEnabled());
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
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_X_AXIS_GRID_ENABLED, this.chart.isXAxisGridEnabled());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_ENABLED, this.chart.isYAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_LABEL_ENABLED, this.chart.isYAxisLabelEnabled());
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
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_Y_AXIS_GRID_ENABLED, this.chart.isYAxisGridEnabled());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_INTERPOLATION_MODE, this.chart.getInterpolationMode().getJsValue());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_WIDTH, this.chart.getLineWidth());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_AREA_WIDTH, this.chart.getLegendAreaWidth());
    }

    protected double getAutoBottom(Rectangle dataRect) {
        if (dataRect.getY1() < 0) {
            // Axis is in the center of chart
            return (this.chart.getHeight() - this.chart.getMarginBottom() - this.chart.getMarginTop()) / 2 + this.chart.getMarginBottom();
        } else {
            return 0d + this.chart.getMarginBottom();
        }
    }

    protected double getAutoLeft(Rectangle dataRect) {
        if (dataRect.getX1() < 0) {
            // Axis is in the center of chart
            return (this.chart.getWidth() - this.chart.getLegendAreaWidth() - this.chart.getMarginLeft() - this.chart.getMarginRight()) / 2 + this.chart.getMarginLeft();
        } else {
            return 0 + this.chart.getMarginLeft();
        }
    }

    protected double getAutoLineBottom(Rectangle dataRect, double bottom, Padding padding) {
        double availableHeight = this.chart.getHeight() - bottom - this.chart.getMarginTop() - padding.getTop() - padding.getBottom();

        double minAbs = Math.abs(dataRect.getY1());
        double max = minAbs > dataRect.getY2() ? minAbs : dataRect.getY2();

        return availableHeight / max;
    }

    protected double getAutoLineLeft(Rectangle dataRect, double left, Padding padding) {
        double availableWidth = this.chart.getWidth() - left - this.chart.getMarginRight() - padding.getRight() - padding.getLeft() - this.chart.getLegendAreaWidth();

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
