package com.gdteam.vprotovisdemo;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.codelabel.CodeLabel;

import com.gdteam.vprotovis.BarChartComponent;
import com.gdteam.vprotovis.chart.bar.Serie;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BarChartPage implements Serializable {

    private static final String YES_OPTION = "Yes";
    private static final String YES_WITH_LABEL_OPTION = "Yes with labels";
    private static final String NO_OPTION = "No";
    private static final String CUSTOM_OPTION = "Custom";
    private static final String DEFAULT_OPTION = "Default";
    private static final String ERROR_REQUIRED_MESSAGE = "Value must be set";
    
    private static final DecimalFormat FORMAT_PERCENT = new DecimalFormat("# %");

    private VerticalLayout main;

    private NativeSelect tooltipTypeSelect, tooltipEnabledSelect, legendEnabledSelect, groupAxisEnabledSelect, valueAxisEnabledSelect;
    private TextField chartWidthText, chartHeightText, barInsetText, groupBarInsetText, groupAxisSizeText, valueAxisSizeText, valueAxisMaxText, valueAxisStepText, valueAxisPatternText,
            valueAxisUnitText, legendSizeText;
    private Label chartWidthLabel, chartHeightLabel, barInsetLabel, groupBarInsetLabel, valueAxisUnitLabel, tooltipEnabledLabel, tooltipTypeLabel, legendEnabledLabel, legendSizeLabel,
            groupAxisSizeLabel, groupAxisEnabledLabel, valueAxisEnabledLabel, valueAxisSizeLabel, valueAxisMaxLabel, valueAxisStepLabel, valueAxisPatternLabel;

    private Button renderButton;
    private BarChartComponent chart;
    private CodeLabel codeLabel;

    public BarChartPage() {
        
        this.main = new VerticalLayout();
        this.main.setMargin(true);
        this.main.setSpacing(true);
        this.main.addComponent(this.createOptionComponent());

        Accordion rendering = new Accordion();
        rendering.setSizeFull();

        VerticalLayout chartContent = new VerticalLayout();
        rendering.addTab(chartContent, "Chart", null);
        
        VerticalLayout sourceCodeContent = new VerticalLayout();
        rendering.addTab(sourceCodeContent, "Source code", null);

        rendering.setSelectedTab(chartContent);

        this.main.addComponent(new Label("<hr />", Label.CONTENT_XHTML));
        this.main.addComponent(rendering);

        this.initListeners();
        this.initStaticValidators();

        this.chartWidthText.setValue("600");
        this.chartHeightText.setValue("300");
        this.barInsetText.setValue("2");
        this.groupBarInsetText.setValue("25");
        this.tooltipEnabledSelect.setValue(YES_OPTION);
        this.tooltipTypeSelect.setValue(DEFAULT_OPTION);
        this.legendEnabledSelect.setValue(YES_OPTION);
        this.legendSizeText.setValue("250");
        this.groupAxisEnabledSelect.setValue(YES_WITH_LABEL_OPTION);
        this.valueAxisEnabledSelect.setValue(YES_WITH_LABEL_OPTION);
        this.valueAxisMaxText.setValue("1500");
        this.valueAxisPatternText.setValue("#.#");
        this.valueAxisSizeText.setValue("70");
        this.valueAxisStepText.setValue("300");
        this.valueAxisUnitText.setValue("\u20AC");

        this.chart = new BarChartComponent() {

            @Override
            public String getTooltipHTML(int serieIndex, int valueIndex, String groupName) {

                if (DEFAULT_OPTION.equals(tooltipTypeSelect.getValue())) {
                    return super.getTooltipHTML(serieIndex, valueIndex, groupName);
                } else {
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
            }

        };
        
        chartContent.addComponent(this.chart);
        
        chartContent.setComponentAlignment(this.chart, Alignment.MIDDLE_CENTER);
        
        chart.addSerie("Sales", new double[] { 1000, 1170, 660, 1030 });
        chart.addSerie("Expenses", new double[] { 400, 460, 1200, 540 });
        chart.addSerie("VAT", new double[] { 20, 46, 78, 13 });
        chart.setGroupNames(new String[] { "2008", "2009", "2010", "2011" });
        
        this.codeLabel = new CodeLabel();
        sourceCodeContent.addComponent(this.codeLabel);
    }

    public Component getComponent() {
        return this.main;
    }

    private Component createOptionComponent() {
        GridLayout grid = new GridLayout(8, 6);
        grid.setSpacing(true);

        this.chartWidthLabel = new Label("Width : ");
        grid.addComponent(chartWidthLabel, 0, 0);
        grid.setComponentAlignment(chartWidthLabel, Alignment.MIDDLE_LEFT);

        this.chartWidthText = new TextField();
        this.chartWidthText.setWidth("110px");
        grid.addComponent(this.chartWidthText, 1, 0);
        grid.setComponentAlignment(this.chartWidthText, Alignment.MIDDLE_LEFT);

        this.chartHeightLabel = new Label("Height : ");
        grid.addComponent(chartHeightLabel, 2, 0);
        grid.setComponentAlignment(chartHeightLabel, Alignment.MIDDLE_LEFT);

        this.chartHeightText = new TextField();
        this.chartHeightText.setWidth("110px");
        grid.addComponent(this.chartHeightText, 3, 0);
        grid.setComponentAlignment(this.chartHeightText, Alignment.MIDDLE_LEFT);

        this.barInsetLabel = new Label("Bar inset : ");
        grid.addComponent(barInsetLabel, 4, 0);
        grid.setComponentAlignment(barInsetLabel, Alignment.MIDDLE_LEFT);

        this.barInsetText = new TextField();
        this.barInsetText.setWidth("110px");
        grid.addComponent(this.barInsetText, 5, 0);
        grid.setComponentAlignment(this.barInsetText, Alignment.MIDDLE_LEFT);

        this.groupBarInsetLabel = new Label("Group inset : ");
        grid.addComponent(groupBarInsetLabel, 6, 0);
        grid.setComponentAlignment(groupBarInsetLabel, Alignment.MIDDLE_LEFT);

        this.groupBarInsetText = new TextField();
        this.groupBarInsetText.setWidth("110px");
        grid.addComponent(this.groupBarInsetText, 7, 0);
        grid.setComponentAlignment(this.groupBarInsetText, Alignment.MIDDLE_LEFT);

        this.tooltipEnabledLabel = new Label("Tooltip : ");
        grid.addComponent(tooltipEnabledLabel, 0, 1);
        grid.setComponentAlignment(tooltipEnabledLabel, Alignment.MIDDLE_LEFT);

        this.tooltipEnabledSelect = new NativeSelect();
        this.tooltipEnabledSelect.setNullSelectionAllowed(false);
        this.tooltipEnabledSelect.setImmediate(true);
        this.tooltipEnabledSelect.setWidth("110px");
        grid.addComponent(this.tooltipEnabledSelect, 1, 1);
        this.tooltipEnabledSelect.addItem(YES_OPTION);
        this.tooltipEnabledSelect.addItem(NO_OPTION);
        grid.setComponentAlignment(this.tooltipEnabledSelect, Alignment.MIDDLE_LEFT);

        this.tooltipTypeLabel = new Label("Type : ");
        grid.addComponent(tooltipTypeLabel, 2, 1);
        grid.setComponentAlignment(tooltipTypeLabel, Alignment.MIDDLE_LEFT);

        this.tooltipTypeSelect = new NativeSelect();
        this.tooltipTypeSelect.setNullSelectionAllowed(false);
        this.tooltipTypeSelect.setImmediate(true);
        this.tooltipTypeSelect.setWidth("110px");
        this.tooltipTypeSelect.addItem(DEFAULT_OPTION);
        this.tooltipTypeSelect.addItem(CUSTOM_OPTION);
        grid.addComponent(this.tooltipTypeSelect, 3, 1);
        grid.setComponentAlignment(this.tooltipTypeSelect, Alignment.MIDDLE_LEFT);

        this.legendEnabledLabel = new Label("Legend : ");
        grid.addComponent(legendEnabledLabel, 4, 1);
        grid.setComponentAlignment(legendEnabledLabel, Alignment.MIDDLE_LEFT);

        this.legendEnabledSelect = new NativeSelect();
        this.legendEnabledSelect.setNullSelectionAllowed(false);
        this.legendEnabledSelect.setImmediate(true);
        this.legendEnabledSelect.setWidth("110px");
        this.legendEnabledSelect.addItem(YES_OPTION);
        this.legendEnabledSelect.addItem(NO_OPTION);
        grid.addComponent(this.legendEnabledSelect, 5, 1);
        grid.setComponentAlignment(this.legendEnabledSelect, Alignment.MIDDLE_LEFT);

        this.legendSizeLabel = new Label("Width : ");
        grid.addComponent(legendSizeLabel, 6, 1);
        grid.setComponentAlignment(legendSizeLabel, Alignment.MIDDLE_LEFT);

        this.legendSizeText = new TextField();
        this.legendSizeText.setWidth("110px");
        grid.addComponent(this.legendSizeText, 7, 1);
        grid.setComponentAlignment(this.legendSizeText, Alignment.MIDDLE_LEFT);

        this.groupAxisEnabledLabel = new Label("Group axis : ");
        grid.addComponent(groupAxisEnabledLabel, 0, 2);
        grid.setComponentAlignment(groupAxisEnabledLabel, Alignment.MIDDLE_LEFT);

        this.groupAxisEnabledSelect = new NativeSelect();
        this.groupAxisEnabledSelect.setNullSelectionAllowed(false);
        this.groupAxisEnabledSelect.setImmediate(true);
        this.groupAxisEnabledSelect.setWidth("110px");
        this.groupAxisEnabledSelect.addItem(YES_OPTION);
        this.groupAxisEnabledSelect.addItem(YES_WITH_LABEL_OPTION);
        this.groupAxisEnabledSelect.addItem(NO_OPTION);
        grid.addComponent(this.groupAxisEnabledSelect, 1, 2);
        grid.setComponentAlignment(this.groupAxisEnabledSelect, Alignment.MIDDLE_LEFT);

        this.groupAxisSizeLabel = new Label("Height : ");
        grid.addComponent(groupAxisSizeLabel, 2, 2);
        grid.setComponentAlignment(groupAxisSizeLabel, Alignment.MIDDLE_LEFT);

        this.groupAxisSizeText = new TextField();
        this.groupAxisSizeText.setWidth("110px");
        grid.addComponent(this.groupAxisSizeText, 3, 2);
        grid.setComponentAlignment(this.groupAxisSizeText, Alignment.MIDDLE_LEFT);

        this.valueAxisEnabledLabel = new Label("Value axis : ");
        grid.addComponent(valueAxisEnabledLabel, 0, 3);
        grid.setComponentAlignment(valueAxisEnabledLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisEnabledSelect = new NativeSelect();
        this.valueAxisEnabledSelect.setNullSelectionAllowed(false);
        this.valueAxisEnabledSelect.setImmediate(true);
        this.valueAxisEnabledSelect.setWidth("110px");
        this.valueAxisEnabledSelect.addItem(YES_OPTION);
        this.valueAxisEnabledSelect.addItem(YES_WITH_LABEL_OPTION);
        this.valueAxisEnabledSelect.addItem(NO_OPTION);
        grid.addComponent(this.valueAxisEnabledSelect, 1, 3);
        grid.setComponentAlignment(this.valueAxisEnabledSelect, Alignment.MIDDLE_LEFT);

        this.valueAxisSizeLabel = new Label("Width : ");
        grid.addComponent(valueAxisSizeLabel, 2, 3);
        grid.setComponentAlignment(valueAxisSizeLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisSizeText = new TextField();
        this.valueAxisSizeText.setWidth("110px");
        grid.addComponent(this.valueAxisSizeText, 3, 3);
        grid.setComponentAlignment(this.valueAxisSizeText, Alignment.MIDDLE_LEFT);

        this.valueAxisMaxLabel = new Label("Max : ");
        grid.addComponent(valueAxisMaxLabel, 4, 3);
        grid.setComponentAlignment(valueAxisMaxLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisMaxText = new TextField();
        this.valueAxisMaxText.setWidth("110px");
        grid.addComponent(this.valueAxisMaxText, 5, 3);
        grid.setComponentAlignment(this.valueAxisMaxText, Alignment.MIDDLE_LEFT);

        this.valueAxisStepLabel = new Label("Step : ");
        grid.addComponent(valueAxisStepLabel, 6, 3);
        grid.setComponentAlignment(valueAxisStepLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisStepText = new TextField();
        this.valueAxisStepText.setWidth("110px");
        grid.addComponent(this.valueAxisStepText, 7, 3);
        grid.setComponentAlignment(this.valueAxisStepText, Alignment.MIDDLE_LEFT);

        this.valueAxisPatternLabel = new Label("Pattern : ");
        grid.addComponent(valueAxisPatternLabel, 4, 4);
        grid.setComponentAlignment(valueAxisPatternLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisPatternText = new TextField();
        this.valueAxisPatternText.setWidth("110px");
        grid.addComponent(this.valueAxisPatternText, 5, 4);
        grid.setComponentAlignment(this.valueAxisPatternText, Alignment.MIDDLE_LEFT);

        this.valueAxisUnitLabel = new Label("Unit : ");
        grid.addComponent(valueAxisUnitLabel, 6, 4);
        grid.setComponentAlignment(valueAxisUnitLabel, Alignment.MIDDLE_LEFT);

        this.valueAxisUnitText = new TextField();
        this.valueAxisUnitText.setWidth("110px");
        grid.addComponent(this.valueAxisUnitText, 7, 4);
        grid.setComponentAlignment(this.valueAxisUnitText, Alignment.MIDDLE_LEFT);

        this.renderButton = new Button("Render !");
        grid.addComponent(this.renderButton, 0, 5);
        grid.setComponentAlignment(this.renderButton, Alignment.MIDDLE_LEFT);

        return grid;
    }

    private void initListeners() {
        this.tooltipEnabledSelect.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                boolean enabled = YES_OPTION.equals(event.getProperty().getValue());
                tooltipTypeLabel.setEnabled(enabled);
                tooltipTypeSelect.setEnabled(enabled);
            }

        });

        this.legendEnabledSelect.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                boolean enabled = YES_OPTION.equals(event.getProperty().getValue());
                legendSizeLabel.setEnabled(enabled);
                legendSizeText.setEnabled(enabled);
                legendSizeText.setRequired(enabled);
            }

        });

        this.groupAxisEnabledSelect.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                boolean enabled = YES_WITH_LABEL_OPTION.equals(event.getProperty().getValue());
                groupAxisSizeLabel.setEnabled(enabled);
                groupAxisSizeText.setEnabled(enabled);
            }
        });

        this.valueAxisEnabledSelect.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                boolean enabled = YES_WITH_LABEL_OPTION.equals(event.getProperty().getValue());
                valueAxisMaxLabel.setEnabled(enabled);
                valueAxisMaxText.setEnabled(enabled);
                valueAxisMaxText.setRequired(enabled);

                valueAxisPatternLabel.setEnabled(enabled);
                valueAxisPatternText.setEnabled(enabled);
                valueAxisPatternText.setRequired(enabled);

                valueAxisSizeLabel.setEnabled(enabled);
                valueAxisSizeText.setEnabled(enabled);
                valueAxisSizeText.setRequired(enabled);

                valueAxisStepLabel.setEnabled(enabled);
                valueAxisStepText.setEnabled(enabled);
                valueAxisStepText.setRequired(enabled);

                valueAxisUnitLabel.setEnabled(enabled);
                valueAxisUnitText.setEnabled(enabled);
            }
        });

        this.renderButton.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                render(true);
            }
        });
    }

    private void initStaticValidators() {

        DoubleValidator doubleValidator = new DoubleValidator("Value must be a number");

        chartWidthText.setRequired(true);
        chartWidthText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        chartWidthText.addValidator(doubleValidator);
        chartWidthText.setImmediate(true);

        chartHeightText.setRequired(true);
        chartHeightText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        chartHeightText.addValidator(doubleValidator);
        chartWidthText.setImmediate(true);

        barInsetText.addValidator(doubleValidator);
        barInsetText.setImmediate(true);

        groupBarInsetText.addValidator(doubleValidator);
        groupBarInsetText.setImmediate(true);

        legendSizeText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        legendSizeText.addValidator(doubleValidator);
        legendSizeText.setImmediate(true);

        groupAxisSizeText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        groupAxisSizeText.addValidator(doubleValidator);
        groupAxisSizeText.setImmediate(true);

        valueAxisMaxText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        valueAxisMaxText.addValidator(doubleValidator);
        valueAxisMaxText.setImmediate(true);

        valueAxisPatternText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        valueAxisPatternText.addValidator(new AbstractValidator(
                "Pattern is invalid. See <a href=\"http://google-web-toolkit.googlecode.com/svn/javadoc/2.2/com/google/gwt/i18n/client/NumberFormat.html\">javadoc</a> for valid pattern (ex: #.#)") {

            public boolean isValid(Object value) {
                try {
                    DecimalFormat f = new DecimalFormat((String) value);
                    f.format(12.0d);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });
        valueAxisPatternText.setImmediate(true);

        valueAxisSizeText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        valueAxisSizeText.addValidator(doubleValidator);
        valueAxisSizeText.setImmediate(true);

        valueAxisStepText.setRequiredError(ERROR_REQUIRED_MESSAGE);
        valueAxisStepText.addValidator(doubleValidator);
        valueAxisStepText.setImmediate(true);
    }

    private boolean checkValues() {
        try {
            chartWidthText.validate();
            chartHeightText.validate();
            barInsetText.validate();
            groupBarInsetText.validate();

            if (YES_OPTION.equals(legendEnabledSelect.getValue())) {
                legendSizeText.validate();
            }
            if (YES_WITH_LABEL_OPTION.equals(groupAxisEnabledSelect.getValue()) || YES_OPTION.equals(groupAxisEnabledSelect.getValue())) {
                groupAxisSizeText.validate();
            }
            if (YES_WITH_LABEL_OPTION.equals(valueAxisEnabledSelect.getValue())) {
                valueAxisMaxText.validate();
                valueAxisPatternText.validate();
                valueAxisSizeText.validate();
                valueAxisStepText.validate();
            }
            return true;
        } catch (Exception e) {
            // Do nothing. All is done in ui
            return false;
        }
    }

    public void render(boolean checkValues) {
        if (!checkValues || this.checkValues()) {

            double width = Double.valueOf((String) this.chartWidthText.getValue());
            double height = Double.valueOf((String) this.chartHeightText.getValue());
            double barInset = this.barInsetText.getValue().equals("") ? 0 : Double.valueOf((String) this.barInsetText.getValue());
            double groupBarInset = this.groupBarInsetText.getValue().equals("") ? 0 : Double.valueOf((String) this.groupBarInsetText.getValue());

            Map<String, Object> modelSourceCodeMap = new HashMap<String, Object>();
            modelSourceCodeMap.put("chartWidth", String.valueOf(width));
            modelSourceCodeMap.put("chartHeight", String.valueOf(height));
            modelSourceCodeMap.put("barInset", String.valueOf(barInset));
            modelSourceCodeMap.put("groupBarInset", String.valueOf(groupBarInset));
            
            chart.setChartWidth(width);
            chart.setChartHeight(height);

            chart.setBarInset(barInset);
            chart.setGroupBarInset(groupBarInset);

            if (YES_OPTION.equals(this.tooltipEnabledSelect.getValue())) {
                chart.setTooltipEnabled(true);
                modelSourceCodeMap.put("tooltipEnabled", 1);
                modelSourceCodeMap.put("tooltipType", this.tooltipTypeSelect.getValue());
            } else {
                chart.setTooltipEnabled(false);
                modelSourceCodeMap.put("tooltipEnabled", 0);
            }

            if (YES_OPTION.equals(this.legendEnabledSelect.getValue())) {
                double legendSize = Double.valueOf((String) this.legendSizeText.getValue());
                chart.addLegend(legendSize);
                modelSourceCodeMap.put("legendEnabled", 1);
                modelSourceCodeMap.put("legendSize", String.valueOf(legendSize));
            } else {
                this.chart.removeLegend();
                modelSourceCodeMap.put("legendEnabled", 0);
            }

            if (YES_WITH_LABEL_OPTION.equals(this.groupAxisEnabledSelect.getValue())) {
                if ("".equals(this.groupAxisSizeText.getValue())) {
                    this.chart.addGroupAxisWithLabel();
                    modelSourceCodeMap.put("groupAxisEnabled", 1);
                    modelSourceCodeMap.put("groupAxisLabelEnabled", 1);
                    modelSourceCodeMap.put("groupAxisLabelSize", "");
                } else {
                    double groupAxisSize = Double.valueOf((String) this.groupAxisSizeText.getValue());
                    this.chart.addGroupAxisWithLabel(groupAxisSize);
                    modelSourceCodeMap.put("groupAxisEnabled", 1);
                    modelSourceCodeMap.put("groupAxisLabelEnabled", 1);
                    modelSourceCodeMap.put("groupAxisLabelSize", String.valueOf(groupAxisSize));
                }
            } else if (YES_OPTION.equals(this.groupAxisEnabledSelect.getValue())) {
                chart.addGroupAxis();
                modelSourceCodeMap.put("groupAxisEnabled", 1);
                modelSourceCodeMap.put("groupAxisLabelEnabled", 0);
            } else {
                this.chart.removeGroupAxis();
                modelSourceCodeMap.put("groupAxisEnabled", 0);
                modelSourceCodeMap.put("groupAxisLabelEnabled", 0);
            }

            if (YES_WITH_LABEL_OPTION.equals(this.valueAxisEnabledSelect.getValue())) {
                double size = Double.valueOf((String) this.valueAxisSizeText.getValue());
                double max = Double.valueOf((String) this.valueAxisMaxText.getValue());
                double step = Double.valueOf((String) this.valueAxisStepText.getValue());
                String pattern = (String) this.valueAxisPatternText.getValue();
                String unit = (String) this.valueAxisUnitText.getValue();
                chart.addValueAxisWithLabel(size, max, step, pattern, unit);
                
                modelSourceCodeMap.put("valueAxisEnabled", 1);
                modelSourceCodeMap.put("valueAxisLabelEnabled", 1);
                modelSourceCodeMap.put("valueAxisLabelSize", String.valueOf(size));
                modelSourceCodeMap.put("valueAxisLabelMax", String.valueOf(max));
                modelSourceCodeMap.put("valueAxisLabelStep", String.valueOf(step));
                modelSourceCodeMap.put("valueAxisLabelPattern", pattern);
                modelSourceCodeMap.put("valueAxisLabelUnit", unit);
            } else if (YES_OPTION.equals(this.valueAxisEnabledSelect.getValue())) {
                chart.addValueAxis();
                modelSourceCodeMap.put("valueAxisEnabled", 1);
                modelSourceCodeMap.put("valueAxisLabelEnabled", 0);
            } else {
                this.chart.removeValueAxis();
                modelSourceCodeMap.put("valueAxisEnabled", 0);
            }

            chart.requestRepaint();

            try {
                Configuration configuration = new Configuration();
                configuration.setClassForTemplateLoading(getClass(), "/templates/");
                Template tpl = configuration.getTemplate("BarChartComponentCode.ftl");
                StringWriter sWriter = new StringWriter();
                
                tpl.process(modelSourceCodeMap, sWriter);
                this.codeLabel.setValue(sWriter.toString());
                
            } catch (IOException e) {
                this.main.getWindow().showNotification("Configuration error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
            } catch (TemplateException e) {
                this.main.getWindow().showNotification("Template error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
            }
            
        } else {
            this.main.getWindow().showNotification("Form error", "Please set fields with correct values", Notification.TYPE_ERROR_MESSAGE);
        }
    }
}
