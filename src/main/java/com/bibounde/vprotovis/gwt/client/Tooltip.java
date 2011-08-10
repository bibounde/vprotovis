package com.bibounde.vprotovis.gwt.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;

public class Tooltip extends PopupPanel {

	private HTML leftArrow, bottomArrow, rightArrrow, topArrow, message;
	
	public Tooltip() {
		super(true, false);
		
		//setWidth("130px");
		
		FlexTable grid = new FlexTable();
		grid.setCellPadding(0);
		grid.setCellSpacing(0);
		
		topArrow = new HTML();
		topArrow.setVisible(true);
		topArrow.setStyleName("v-vprotovis-tooltip-arrow-top");
		grid.setWidget(0, 1, topArrow);
		
		leftArrow = new HTML();
		leftArrow.setVisible(true);
		leftArrow.setStyleName("v-vprotovis-tooltip-arrow-left");
		grid.setWidget(1, 0, leftArrow);
		grid.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		rightArrrow = new HTML();
		rightArrrow.setVisible(true);
		grid.setWidget(1, 2, rightArrrow);
		
		bottomArrow = new HTML();
		bottomArrow.setVisible(true);
		bottomArrow.setStyleName("v-vprotovis-tooltip-arrow-bottom");
		grid.setWidget(2, 1, bottomArrow);
		grid.getFlexCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		this.message = new HTML();
		this.message.setStyleName("v-vprotovis-tooltip-content");
		grid.setWidget(1, 1, this.message);
		//grid.getFlexCellFormatter().setWidth(1, 1, "100%");
		
		this.add(grid);
	}
	
	public void setText(String html) {
		this.message.setHTML(html);
	}
	
	/**
	 * Must be called before setArrowStyle
	 */
	public void initArrows() {
		this.topArrow.setVisible(true);
		this.leftArrow.setVisible(true);
		this.rightArrrow.setVisible(true);
		this.bottomArrow.setVisible(true);
	}
	
	public void setArrowStyle(ArrowStyle style) {
		switch (style) {
		case TOP:
			this.topArrow.setVisible(true);
			this.leftArrow.setVisible(false);
			this.rightArrrow.setVisible(false);
			this.bottomArrow.setVisible(false);
			break;
		case LEFT:
			this.topArrow.setVisible(false);
			this.leftArrow.setVisible(true);
			this.rightArrrow.setVisible(false);
			this.bottomArrow.setVisible(false);
			break;
		default:
			this.topArrow.setVisible(false);
			this.leftArrow.setVisible(false);
			this.rightArrrow.setVisible(false);
			this.bottomArrow.setVisible(true);
			break;
		}
	}
	
	public enum ArrowStyle {
		LEFT, TOP, BOTTOM, RIGHT;
	}
}
