/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bibounde.vprotovisdemo;

import com.vaadin.Application;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class VProtovisApplication extends Application {
    private Window window;    

    @Override
    public void init() {
        
        window = new Window("Protovis Wrapper Demo");
        setMainWindow(window);

        TabSheet tabSheet = new TabSheet();
        window.addComponent(tabSheet);

        BarChartPage barChartPage = new BarChartPage();
        tabSheet.addTab(barChartPage.getComponent(), "BarChartComponent", null);
        
        barChartPage.render(false);
    }
}
