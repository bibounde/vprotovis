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
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VLineChartComponent.class)
public class LineChartComponent extends AbstractComponent {

    private LineChart chart;
    private String id = "v-protovis-linechart-" + this.hashCode();
    private AxisLabelFormatter horizontalAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private AxisLabelFormatter verticalAxisLabelFormatter = new DefaultAxisLabelFormatter();

    public LineChartComponent() {
        chart = new LineChart();
    }

    public void setId(String id) {
        this.id = id;
    }

    public int addSerie(String name, Point[] values) {
        return this.chart.addSerie(name, values);
    }

    /**
     * @param width
     * @see com.bibounde.vprotovis.chart.line.LineChart#setWidth(double)
     */
    public void setChartWidth(double width) {
        chart.setWidth(width);
    }

    /**
     * @param height
     * @see com.bibounde.vprotovis.chart.line.LineChart#setHeight(double)
     */
    public void setChartHeight(double height) {
        chart.setHeight(height);
    }

    /**
     * @param colors
     * @see com.bibounde.vprotovis.chart.line.LineChart#setColors(java.lang.String[])
     */
    public void setColors(String[] colors) {
        chart.setColors(colors);
    }

    public void addHorizontalAxis() {
        this.chart.setHorizontalAxisEnabled(true);
        this.chart.setHorizontalAxisLabelEnabled(false);
    }
    
    public void addHorizontalAxisWithLabel(double step) {
        this.addHorizontalAxisWithLabel(-1, -1, step, false);
    }
    
    public void addHorizontalAxisWithLabel(double step, boolean withGrid) {
        this.addHorizontalAxisWithLabel(-1, -1, step, withGrid);
    }
    
    public void addHorizontalAxisWithLabel(double start, double max, double step) {
        this.addHorizontalAxisWithLabel(start, max, step, false);
    }

    public void addHorizontalAxisWithLabel(double start, double max, double step, boolean withGrid) {
        this.chart.setHorizontalAxisEnabled(true);
        this.chart.setHorizontalAxisLabelEnabled(true);
        this.chart.setHorizontalAxisLabelStart(start);
        this.chart.setHorizontalAxisLabelStop(max);
        this.chart.setHorizontalAxisLabelStep(step);
        this.chart.setHorizontalAxisGridEnabled(withGrid);
    }

    public void removeHorizontalAxis() {
        this.chart.setHorizontalAxisEnabled(false);
        this.chart.setHorizontalAxisLabelEnabled(false);
    }

    public void addVerticalAxis() {
        this.chart.setVerticalAxisEnabled(true);
        this.chart.setVerticalAxisLabelEnabled(false);
    }
    
    public void addVerticalAxis(double step) {
        this.addVerticalAxis(-1, -1, step, false);
    }

    public void addVerticalAxis(double step, boolean withGrid) {
        this.addVerticalAxis(-1, -1, step, withGrid);
    }
    
    public void addVerticalAxis(double start, double max, double step) {
        this.addVerticalAxis(start, max, step, false);
    }
    
    public void addVerticalAxis(double start, double max, double step, boolean withGrid) {
        this.chart.setVerticalAxisEnabled(true);
        this.chart.setVerticalAxisLabelEnabled(true);
        this.chart.setVerticalAxisLabelStart(start);
        this.chart.setVerticalAxisLabelStop(max);
        this.chart.setVerticalAxisLabelStep(step);
        this.chart.setVerticalAxisGridEnabled(withGrid);
    }

    public void removeVerticalAxis() {
        this.chart.setVerticalAxisEnabled(false);
        this.chart.setVerticalAxisLabelEnabled(false);
    }

    /**
     * @param marginLeft
     * @see com.bibounde.vprotovis.chart.line.LineChart#setMarginLeft(double)
     */
    public void setMarginLeft(double marginLeft) {
        chart.setMarginLeft(marginLeft);
    }

    /**
     * @param marginRight
     * @see com.bibounde.vprotovis.chart.line.LineChart#setMarginRight(double)
     */
    public void setMarginRight(double marginRight) {
        chart.setMarginRight(marginRight);
    }

    /**
     * @param marginTop
     * @see com.bibounde.vprotovis.chart.line.LineChart#setMarginTop(double)
     */
    public void setMarginTop(double marginTop) {
        chart.setMarginTop(marginTop);
    }

    /**
     * @param marginBottom
     * @see com.bibounde.vprotovis.chart.line.LineChart#setMarginBottom(double)
     */
    public void setMarginBottom(double marginBottom) {
        chart.setMarginBottom(marginBottom);
    }
    

    /**
     * @param interpolationMode
     * @see com.bibounde.vprotovis.chart.line.LineChart#setInterpolationMode(com.bibounde.vprotovis.chart.line.InterpolationMode)
     */
    public void setInterpolationMode(InterpolationMode interpolationMode) {
        chart.setInterpolationMode(interpolationMode);
    }

    /**
     * @param lineWidth
     * @see com.bibounde.vprotovis.chart.line.LineChart#setLineWidth(int)
     */
    public void setLineWidth(int lineWidth) {
        chart.setLineWidth(lineWidth);
    }

    public void addLegend(double legendAreaWidth) {
        chart.setLegendEnabled(true);
        chart.setLegendAreaWidth(legendAreaWidth);
    }
    
    public void removeLegend() {
        chart.setLegendEnabled(false);
        chart.setLegendAreaWidth(0d);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.ui.AbstractComponent#paintContent(com.vaadin.terminal.PaintTarget
     * )
     */
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
        Range rangeY = this.chart.getVerticalAxisLabelStart() != -1 ? new Range(this.chart.getVerticalAxisLabelStart(), this.chart.getVerticalAxisLabelStop(), this.chart.getVerticalAxisLabelStep()) : Range.getAutoRange(dataRect.getY1(), dataRect.getY2(), this.chart.getVerticalAxisLabelStep());
        Range rangeX = this.chart.getHorizontalAxisLabelStart() != -1 ? new Range(this.chart.getHorizontalAxisLabelStart(), this.chart.getHorizontalAxisLabelStop(), this.chart.getHorizontalAxisLabelStep()) : Range.getAutoRange(dataRect.getX1(), dataRect.getX2(), this.chart.getHorizontalAxisLabelStep());

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

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_ENABLED, this.chart.isHorizontalAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_ENABLED, this.chart.isHorizontalAxisLabelEnabled());
        
        //Add x axis values and their formatted text
        Double[] rangeXValues = rangeX.getRangeArray();
        String[] rangeXSValues = new String[rangeXValues.length];
        for (int i = 0; i < rangeXValues.length; i++) {
            rangeXSValues[i] = this.horizontalAxisLabelFormatter.format(rangeXValues[i]);
        }
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_D_VALUES, rangeX.getRangeArrayAsString());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_RANGE_S_VALUES, rangeXSValues);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_GRID_ENABLED, this.chart.isHorizontalAxisGridEnabled());

        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_ENABLED, this.chart.isVerticalAxisEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_ENABLED, this.chart.isVerticalAxisLabelEnabled());
        
        //Add y axis values and their formatted text
        Double[] rangeYValues = rangeY.getRangeArray();
        String[] rangeYSValues = new String[rangeYValues.length];
        for (int i = 0; i < rangeYValues.length; i++) {
            rangeYSValues[i] = this.verticalAxisLabelFormatter.format(rangeYValues[i]);
        }
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_D_VALUES, rangeY.getRangeArrayAsString());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_S_VALUES, rangeYSValues);
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_GRID_ENABLED, this.chart.isVerticalAxisGridEnabled());
        
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_INTERPOLATION_MODE, this.chart.getInterpolationMode().getJsValue());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LINE_WIDTH, this.chart.getLineWidth());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());
        
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_AREA_WIDTH, this.chart.getLegendAreaWidth());
    }

    protected double getAutoBottom(Rectangle dataRect) {
        if (dataRect.getY1() < 0) {
            //Axis is in the center of chart
            return (this.chart.getHeight() / 2);
        } else {
            return 0d + this.chart.getMarginBottom();
        }
    }

    protected double getAutoLeft(Rectangle dataRect) {
        if (dataRect.getX1() < 0) {
          //Axis is in the center of chart
            return (this.chart.getWidth() - this.chart.getLegendAreaWidth()) / 2;
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
     * @param horizontalAxisLabelFormatter the horizontalAxisLabelFormatter to set
     */
    public void setHorizontalAxisLabelFormatter(AxisLabelFormatter horizontalAxisLabelFormatter) {
        if (horizontalAxisLabelFormatter == null) {
            this.horizontalAxisLabelFormatter = new DefaultAxisLabelFormatter();
        } else {
            this.horizontalAxisLabelFormatter = horizontalAxisLabelFormatter;
        }
    }



    /**
     * @param verticalAxisLabelFormatter the verticalAxisLabelFormatter to set
     */
    public void setVerticalAxisLabelFormatter(AxisLabelFormatter verticalAxisLabelFormatter) {
        if (verticalAxisLabelFormatter == null) {
            this.verticalAxisLabelFormatter = new DefaultAxisLabelFormatter();
        } else {
            this.verticalAxisLabelFormatter = verticalAxisLabelFormatter;
        }
    }
}
