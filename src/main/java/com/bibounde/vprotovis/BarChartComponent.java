package com.bibounde.vprotovis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bibounde.vprotovis.chart.bar.BarChart;
import com.bibounde.vprotovis.chart.bar.DefaultTooltipFormatter;
import com.bibounde.vprotovis.chart.bar.Serie;
import com.bibounde.vprotovis.chart.bar.TooltipFormatter;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.DefaultAxisLabelFormatter;
import com.bibounde.vprotovis.common.Padding;
import com.bibounde.vprotovis.common.Range;
import com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent;
import com.bibounde.vprotovis.gwt.client.line.VLineChartComponent;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VBarChartComponent.class)
public class BarChartComponent extends AbstractComponent {

    private BarChart chart;
    private String id = "v-protovis-barchart-" + this.hashCode();
    private AxisLabelFormatter verticalAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private TooltipFormatter tooltipFormatter = new DefaultTooltipFormatter();

    public BarChartComponent() {
        chart = new BarChart();
    }

    public void setId(String id) {
        this.id = id;
    }

    public int addSerie(String name, double[] values) {
        return this.chart.addSerie(name, values);
    }

    /**
     * @param width
     * @see com.bibounde.vprotovis.chart.bar.BarChart#setWidth(double)
     */
    public void setChartWidth(double width) {
        chart.setWidth(width);
    }

    /**
     * @param height
     * @see com.bibounde.vprotovis.chart.bar.BarChart#setHeight(double)
     */
    public void setChartHeight(double height) {
        chart.setHeight(height);
    }

    /**
     * @param groupNames
     * @see com.bibounde.vprotovis.chart.bar.BarChart#setGroupNames(java.lang.String[])
     */
    public void setGroupNames(String[] groupNames) {
        chart.setGroupNames(groupNames);
    }

    /**
     * @param groupBarInset
     * @see com.bibounde.vprotovis.chart.bar.BarChart#setGroupBarInset(double)
     */
    public void setGroupBarInset(double groupBarInset) {
        chart.setGroupBarInset(groupBarInset);
    }

    /**
     * @param barInset
     * @see com.bibounde.vprotovis.chart.bar.BarChart#setBarInset(double)
     */
    public void setBarInset(double barInset) {
        chart.setBarInset(barInset);
    }
    
    public void addHorizontalAxis() {
        this.chart.setHorizontalAxisEnabled(true);
        this.chart.setHorizontalAxisLabelEnabled(false);
    }
    
    public void addHorizontalAxisWithLabel() {
        this.chart.setHorizontalAxisEnabled(true);
        this.chart.setHorizontalAxisLabelEnabled(true);
    }

    public void removeHorizontalAxis() {
        this.chart.setHorizontalAxisEnabled(false);
        this.chart.setHorizontalAxisLabelEnabled(false);
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
     * @param verticalAxisLabelFormatter the verticalAxisLabelFormatter to set
     */
    public void setVerticalAxisLabelFormatter(AxisLabelFormatter verticalAxisLabelFormatter) {
        if (verticalAxisLabelFormatter == null) {
            this.verticalAxisLabelFormatter = new DefaultAxisLabelFormatter();
        } else {
            this.verticalAxisLabelFormatter = verticalAxisLabelFormatter;
        }
    }
    
    public void setColors(String[] colors) {
        chart.setColors(colors);
    }
    
    public void addLegend(double legendAreaWidth) {
        chart.setLegendEnabled(true);
        chart.setLegendAreaWidth(legendAreaWidth);
    }
    
    public void removeLegend() {
        chart.setLegendEnabled(false);
        chart.setLegendAreaWidth(0d);
    }

    /**
     * @param tooltipFormatter the tooltipFormatter to set
     */
    public void setTooltipFormatter(TooltipFormatter tooltipFormatter) {
        if (tooltipFormatter == null) {
            this.tooltipFormatter = new DefaultTooltipFormatter();  
        } else {
            this.tooltipFormatter = tooltipFormatter;
        }
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addVariable(this, VBarChartComponent.UIDL_DIV_ID, this.id);

        int groupCount = 0;
        double minValue = 0d, maxValue = 0d;
        for (Serie serie : this.chart.getSeries()) {
            for (int i = 0; i < serie.getValues().length; i++) {
                minValue = minValue < serie.getValues()[i] ? minValue : serie.getValues()[i];
                maxValue = maxValue < serie.getValues()[i] ? serie.getValues()[i] : maxValue;
            }
            groupCount = groupCount < serie.getValues().length ? serie.getValues().length : groupCount;
        }
        Padding padding = new Padding(10d, 10d, 10d, minValue < 0 ? 10d : 0d);

        this.paintChartValues(target, groupCount);
        this.paintChartOptions(target, groupCount, minValue, maxValue, padding);
    }

    private void paintChartValues(PaintTarget target, int groupCount) throws PaintException {
        target.addVariable(this, VBarChartComponent.UIDL_DATA_SERIES_COUNT, this.chart.getSeries().size());
        target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUPS_COUNT, groupCount);
        
        String[] groupNames = this.getGroupNames(groupCount);
        for (int i = 0; i < groupNames.length; i++) {
            target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_NAME + i, groupNames[i]);
        }
        target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUPS_NAMES, groupNames);
        
        //Convert data in protovis mode
        Map<Integer, List<String>> dataMap = new HashMap<Integer, List<String>>();
        Map<Integer, List<String>> tooltipMap = new HashMap<Integer, List<String>>();
        
        String[] serieNames = new String[this.chart.getSeries().size()];
        
        int serieIndex = 0;
        for (Serie serie : this.chart.getSeries()) {
            for (int i = 0; i < serie.getValues().length; i++) {
                //Data
                List<String> values = null;
                if (!dataMap.containsKey(i)) {
                    values = new ArrayList<String>();
                    dataMap.put(i, values);
                } else {
                    values = dataMap.get(i);
                }
                values.add(String.valueOf(serie.getValues()[i]));
                
                //Tooltips
                List<String> tooltips = null;
                if (!tooltipMap.containsKey(i)) {
                    tooltips = new ArrayList<String>();
                    tooltipMap.put(i, tooltips);
                } else {
                    tooltips = tooltipMap.get(i);
                }
                tooltips.add(this.tooltipFormatter.getTooltipHTML(serie.getName(), serie.getValues()[i], groupNames[i]));
            }
            
            target.addVariable(this, VBarChartComponent.UIDL_DATA_SERIE_NAME + serieIndex, serie.getName());
            serieNames[serieIndex] = serie.getName();
            serieIndex++;
        }
        
        //Serie names
        target.addVariable(this, VBarChartComponent.UIDL_DATA_SERIES_NAMES, serieNames);
        
        //Data
        int index = 0;
        for (List<String> values : dataMap.values()) {
            target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_VALUES + index, values.toArray(new String[values.size()]));
            index++;
        }
        
        //Tooltips
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_TOOLTIP_ENABLED, this.chart.isTooltipEnabled());
        if (this.chart.isTooltipEnabled()) {
            index = 0;
            for (List<String> values : tooltipMap.values()) {
                target.addVariable(this, VBarChartComponent.UIDL_DATA_GROUP_TOOLTIP_VALUES + index, values.toArray(new String[values.size()]));
                index++;
            }
        }
    }

    private void paintChartOptions(PaintTarget target, int groupCount, double minValue, double maxValue, Padding padding) throws PaintException {
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_WIDTH, this.chart.getWidth());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_HEIGHT, this.chart.getHeight());
        
        double bottom = this.getAutoBottom(minValue);
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BOTTOM, bottom);
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_LEFT, this.chart.getMarginLeft());
        
        double groupWidth = this.getAutoGroupWidth(groupCount, padding);
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_WIDTH, groupWidth);
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_GROUP_INSET, this.chart.getGroupBarInset());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_HEIGHT, this.getAutoBarHeight(minValue, maxValue, bottom, padding));
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_WIDTH, this.getAutoBarWidth(groupWidth));
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_BAR_INSET, this.chart.getBarInset());
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_MARGIN_LEFT, this.chart.getMarginLeft());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_MARGIN_BOTTOM, this.chart.getMarginBottom());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_MARGIN_RIGHT, this.chart.getMarginRight());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_MARGIN_TOP, this.chart.getMarginTop());
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_PADDING_LEFT, padding.getLeft());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_PADDING_BOTTOM, padding.getBottom());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_PADDING_RIGHT, padding.getRight());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_PADDING_TOP, padding.getTop());
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_ENABLED, this.chart.isHorizontalAxisEnabled());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_HORIZONTAL_AXIS_LABEL_ENABLED, this.chart.isHorizontalAxisLabelEnabled());
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_ENABLED, this.chart.isVerticalAxisEnabled());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_ENABLED, this.chart.isVerticalAxisLabelEnabled());
        
        //Add y axis values and their formatted text
        Range rangeY = this.chart.getVerticalAxisLabelStart() != -1 ? new Range(this.chart.getVerticalAxisLabelStart(), this.chart.getVerticalAxisLabelStop(), this.chart.getVerticalAxisLabelStep()) : Range.getAutoRange(minValue, maxValue, this.chart.getVerticalAxisLabelStep());
        Double[] rangeYValues = rangeY.getRangeArray();
        String[] rangeYSValues = new String[rangeYValues.length];
        for (int i = 0; i < rangeYValues.length; i++) {
            rangeYSValues[i] = this.verticalAxisLabelFormatter.format(rangeYValues[i]);
        }
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_D_VALUES, rangeY.getRangeArrayAsString());
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_LABEL_RANGE_S_VALUES, rangeYSValues);
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_VERTICAL_AXIS_GRID_ENABLED, this.chart.isVerticalAxisGridEnabled());
        
        target.addVariable(this, VBarChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
        target.addVariable(this, VLineChartComponent.UIDL_OPTIONS_LEGEND_AREA_WIDTH, this.chart.getLegendAreaWidth());
    }

    protected String[] getGroupNames(int groupCount) {

        String[] ret = new String[groupCount];

        if (this.chart.getGroupNames() == null) {
            for (int i = 0; i < groupCount; i++) {
                ret[i] = "";
            }
        } else {
            int namesCount = this.chart.getGroupNames().length;
            for (int i = 0; i < groupCount; i++) {
                if (i < namesCount) {
                    ret[i] = this.chart.getGroupNames()[i];
                } else {
                    ret[i] = "";
                }
            }
        }

        return ret;
    }
    
    protected double getAutoBottom(double minValue) {
        if (minValue < 0) {
            //Axis is in the center of chart
            return (this.chart.getHeight() / 2);
        } else {
            return 0d + this.chart.getMarginBottom();
        }
    }
    
    protected double getAutoGroupWidth(int groupCount, Padding padding) {
        double availableWidth = this.chart.getWidth() - this.chart.getMarginLeft() - this.chart.getMarginRight() - padding.getRight() - padding.getLeft() - this.chart.getLegendAreaWidth();
        
        return (availableWidth - ((groupCount - 1) * this.chart.getGroupBarInset())) / groupCount;
    }
    
    protected double getAutoBarHeight(double minValue, double maxValue, double bottom, Padding padding) {
        double availableHeight = this.chart.getHeight() - bottom - this.chart.getMarginTop() - padding.getTop() - padding.getBottom();

        double minAbs = Math.abs(minValue);
        double max = minAbs > maxValue ? minAbs : maxValue;

        return availableHeight / max;
    }
    
    protected double getAutoBarWidth(double groupWidth) {
        return (groupWidth - ((this.chart.getSeries().size() - 1) * this.chart.getBarInset())) / this.chart.getSeries().size();
    }
}
