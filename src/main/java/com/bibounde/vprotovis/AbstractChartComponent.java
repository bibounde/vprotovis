package com.bibounde.vprotovis;

import com.bibounde.vprotovis.chart.Chart;
import com.bibounde.vprotovis.gwt.client.VAbstractChartComponent;
import com.bibounde.vprotovis.util.ColorUtil;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;

public abstract class AbstractChartComponent extends AbstractComponent {

    protected Chart chart;
    protected String id;

    public AbstractChartComponent(Chart chart) {
        this.chart = chart;
    }
    /**
     * Sets html element id
     * @param id html element id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @see com.vaadin.ui.AbstractComponent#paintContent(com.vaadin.terminal.PaintTarget)
     */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        
        target.addVariable(this, VAbstractChartComponent.UIDL_DIV_ID, this.id);
        
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_WIDTH, this.chart.getWidth());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_HEIGHT, this.chart.getHeight());
        
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_MARGIN_LEFT, this.chart.getMarginLeft());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_MARGIN_BOTTOM, this.chart.getMarginBottom());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_MARGIN_RIGHT, this.chart.getMarginRight());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_MARGIN_TOP, this.chart.getMarginTop());
        
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_TOOLTIP_ENABLED, this.chart.isTooltipEnabled());
        
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_COLORS, this.chart.getColors());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_LEGEND_ENABLED, this.chart.isLegendEnabled());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_LEGEND_AREA_WIDTH, this.chart.getLegendAreaWidth());
        target.addVariable(this, VAbstractChartComponent.UIDL_OPTIONS_LEGEND_INSET_LEFT, this.chart.getLegendInsetLeft());
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
     * Sets bar colors
     * @param colors new bar colors
     */
    public void setColors(String[] colors) {
        if (colors == null) {
            chart.setColors(ColorUtil.getDefaultColors());
        } else {
            chart.setColors(colors);
        }
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
    
    /**
     * Enables (or disables) the tooltip
     * @param enabled  true to enable the tooltip, otherwise false
     */
    public void setTooltipEnabled(boolean enabled) {
        this.chart.setTooltipEnabled(enabled);
    }
    /**
     * Sets legend's left inset value
     * @param inset legend's left inset value
     */
    public void setLegendInsetLeft(double inset) {
        this.chart.setLegendInsetLeft(inset);
    }
}
