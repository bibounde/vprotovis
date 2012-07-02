package com.bibounde.vprotovis;

import com.bibounde.pcharts.shared.bar.BarChartHelper;
import com.bibounde.pcharts.shared.bar.BarChartModel;
import com.bibounde.pcharts.shared.bar.BarJSModel;
import com.bibounde.pcharts.shared.bar.BarJSModelImpl;
import com.bibounde.vprotovis.chart.bar.BarTooltipFormatter;
import com.bibounde.vprotovis.chart.bar.DefaultBarTooltipFormatter;
import com.bibounde.vprotovis.common.AxisLabelFormatter;
import com.bibounde.vprotovis.common.DefaultAxisLabelFormatter;
import com.bibounde.vprotovis.gwt.client.bar.VBarChartComponent;
import com.bibounde.vprotovis.util.ColorUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Component used to display multi-series bar chart. A grouped chart allows
 * accurate comparison of individual values thanks to an aligned baseline: a
 * position, rather than length, judgment is used
 * 
 * @author bibounde
 * 
 */
@ClientWidget(VBarChartComponent.class)
public class BarChartComponent extends AbstractComponent {

    private AxisLabelFormatter yAxisLabelFormatter = new DefaultAxisLabelFormatter();
    private BarTooltipFormatter tooltipFormatter = new DefaultBarTooltipFormatter();

    private BarChartModel model;
    private BarChartHelper helper;
    private Gson gson;

    /**
     * Initializes a newly created BarChartComponent
     */
    public BarChartComponent() {
        this.model = new BarChartModel();
        this.model.setId("v-protovis-barchart-" + this.hashCode());
        this.helper = new BarChartHelper(this.model);
        this.gson = new GsonBuilder().disableHtmlEscaping().create();
    }
    
    /**
     * Appends serie values
     * @param name name of the serie
     * @param values values
     * @return serie index
     */
    public int addSerie(String name, double[] values) {
        return this.model.addSerie(name, values);
    }

    /**
     * Clears all serie values
     */
    public void clearSeries() {
        this.model.getSeries().clear();
    }

    /**
     * Sets the names of group
     * @param groupNames new names of group
     */
    public void setGroupNames(String[] groupNames) {
        this.model.setGroupNames(groupNames);
    }

    /**
     * Sets space (unit : pixels) between groups
     * @param groupInset space (unit : pixels) between groups
     */
    public void setGroupInset(double groupInset) {
        this.model.setGroupInset(groupInset);
    }

    /**
     * Sets space (unit : pixels) between bar in each group
     * @param barInset space (unit : pixels) between bar in each group
     */
    public void setBarInset(double barInset) {
        this.model.setBarInset(barInset);
    }

    /**
     * Sets visibility of x axis
     * @param visible x axis visibility
     */
    public void setXAxisVisible(boolean visible) {
        this.model.setXAxisEnabled(visible);
        this.model.setXAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of x axis labels
     * @param visible x axis label visibility
     */
    public void setXAxisLabelVisible(boolean visible) {
        this.model.setXAxisEnabled(this.model.isXAxisEnabled() || visible);
        this.model.setXAxisLabelEnabled(visible);
    }

    /**
     * Sets visibility of y axis
     * @param visible y axis visibility
     */
    public void setYAxisVisible(boolean visible) {
        this.model.setYAxisEnabled(visible);
        this.model.setYAxisLabelEnabled(false);
    }

    /**
     * Sets visibility of y axis labels
     * @param visible y axis label visibility
     */
    public void setYAxisLabelVisible(boolean visible) {
        this.model.setYAxisEnabled(this.model.isYAxisEnabled() || visible);
        this.model.setYAxisLabelEnabled(visible);
    }

    /**
     * Sets y axis label step (used to display ticks on y axis)
     * @param step y axis label step
     */
    public void setYAxisLabelStep(double step) {
        this.model.setYAxisLabelStep(step);
    }

    /**
     * Sets visibility of horizontal lines
     * @param visible horizontal line visibility
     */
    public void setYAxisGridVisible(boolean visible) {
        this.model.setYAxisEnabled(this.model.isYAxisEnabled() || visible);
        this.model.setYAxisGridEnabled(visible);
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

    /**
     * Sets tooltip formatter
     * @param tooltipFormatter tooltip formatter
     */
    public void setTooltipFormatter(BarTooltipFormatter tooltipFormatter) {
        this.tooltipFormatter = tooltipFormatter;
        this.model.setTooltipEnabled(tooltipFormatter != null);
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        
        BarJSModel jsModel = new BarJSModelImpl();
        this.helper.updateJSModel(jsModel, tooltipFormatter, yAxisLabelFormatter);

        target.addVariable(this, VBarChartComponent.UIDL_BAR_JSMODEL, this.gson.toJson(jsModel));
    }
    
    
    //TODO: Add in abstract
    //----------------------------------------------------------------------------------------------------
    /**
     * Sets html element id
     * @param id html element id
     */
    public void setId(String id) {
        this.model.setId(id);
    }
    
    /**
     * Sets chart width (unit : pixels)
     * @param width new chart width (unit : pixels)
     */
    public void setChartWidth(double width) {
        this.model.setWidth(width);
    }

    /**
     * Sets chart height (unit : pixels)
     * @param height new chart height (unit : pixels)
     */
    public void setChartHeight(double height) {
        this.model.setHeight(height);
    }
    
    /**
     * Sets left margin (unit : pixels)
     * @param marginLeft left margin (unit : pixels)
     */
    public void setMarginLeft(double marginLeft) {
        this.model.setMarginLeft(marginLeft);
    }

    /**
     * Sets right margin (unit : pixels)
     * @param marginRight right margin (unit : pixels)
     */
    public void setMarginRight(double marginRight) {
        this.model.setMarginRight(marginRight);
    }

    /**
     * Set top margin (unit : pixels)
     * @param marginTop top margin (unit : pixels)
     */
    public void setMarginTop(double marginTop) {
        this.model.setMarginTop(marginTop);
    }

    /**
     * Sets bottom margin (unit : pixels)
     * @param marginBottom bottom margin (unit : pixels)
     */
    public void setMarginBottom(double marginBottom) {
        this.model.setMarginBottom(marginBottom);
    }

    /**
     * Sets bar colors
     * @param colors new bar colors
     */
    public void setColors(String[] colors) {
        if (colors == null) {
            this.model.setColors(ColorUtil.getDefaultColors());
        } else {
            this.model.setColors(colors);
        }
    }

    /**
     * Sets legend visibility
     * @param visible legend visibility
     */
    public void setLegendVisible(boolean visible) {
        this.model.setLegendEnabled(visible);
        this.model.setLegendAreaWidth(visible ? 150d : 0d);
    }

    /**
     * Sets legend area width (unit : pixels)
     * @param legendAreaWidth legend area width
     */
    public void setLegendAreaWidth(double legendAreaWidth) {
        this.model.setLegendAreaWidth(legendAreaWidth);
    }
    
    /**
     * Enables (or disables) the tooltip
     * @param enabled  true to enable the tooltip, otherwise false
     */
    public void setTooltipEnabled(boolean enabled) {
        this.model.setTooltipEnabled(enabled);
    }
    /**
     * Sets legend's left inset value
     * @param inset legend's left inset value
     */
    public void setLegendInsetLeft(double inset) {
        this.model.setLegendInsetLeft(inset);
    }
}
