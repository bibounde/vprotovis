package com.bibounde.vprotovis.gwt.server;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.terminal.gwt.server.ApplicationServlet;
import com.vaadin.terminal.gwt.server.WebBrowser;

public class IE8ApplicationServlet extends ApplicationServlet {

    @Override
    protected void writeAjaxPageHtmlHeader(BufferedWriter page, String title, String themeUri, HttpServletRequest request) throws IOException {
        page.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");

        WebBrowser browser = getApplicationContext(request.getSession())
                .getBrowser();
        if (browser.isIE()) {
            // Chrome frame in all versions of IE (only if Chrome frame is
            // installed)
            page.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />");
        }

        page.write("<style type=\"text/css\">"
                + "html, body {height:100%;margin:0;}</style>");

        // Add favicon links
        page.write("<link rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" href=\""
                + themeUri + "/favicon.ico\" />");
        page.write("<link rel=\"icon\" type=\"image/vnd.microsoft.icon\" href=\""
                + themeUri + "/favicon.ico\" />");

        page.write("<title>" + safeEscapeForHtml(title) + "</title>");
    }
}
