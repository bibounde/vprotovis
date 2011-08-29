package com.bibounde.vprotovis.gwt.client;

import com.bibounde.vprotovis.gwt.client.TooltipComposite.ArrowStyle;
import com.google.gwt.user.client.ui.PopupPanel;

public class Tooltip extends PopupPanel {
	
    private TooltipComposite composite;
    
	public Tooltip() {
	    super(true);
        this.init();
    }
	
	private void init() {
	    this.composite = new TooltipComposite();
        this.add(composite);
	}
	
	public void setText(String html) {
		this.composite.setText(html);
	}
	
	public void setArrowStyle(ArrowStyle style) {
		this.composite.setArrowStyle(style);
	}
}
