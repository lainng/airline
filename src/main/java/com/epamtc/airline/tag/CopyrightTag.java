package com.epamtc.airline.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final long serialVersionUID = 1L;
    private static final String COPYRIGHT_SYMBOL = "©";
    private static final String WHITESPACE = " ";
    private static final String BR_TAG = "<br>";
    private static final String BUNDLE_NAME = "labels";

    private String year;
    private String companyName;
    private String bundleKeyName;
    private String locale;

    public void setYear(String year) {
        this.year = year;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBundleKeyName(String bundleKeyName) {
        this.bundleKeyName = bundleKeyName;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String copyrightLabel = makeCopyrightLabel();
            JspWriter out = pageContext.getOut();
            out.write(copyrightLabel);
        } catch (IOException e) {
            LOGGER.error("Unable to write to output stream. {}", e.getMessage());
            throw new JspException("Unable to write to output stream.", e);
        }
        return SKIP_BODY;
    }

    private String makeCopyrightLabel() throws JspException {
        StringBuilder label = new StringBuilder();
        ResourceBundle labelsBundle = getResourceBundle(locale);
        String bundleValue;
        try {
            bundleValue = getProperty(labelsBundle, bundleKeyName);
        } catch (MissingResourceException e) {
            LOGGER.error("Property specified in tag attribute not found. {}", e.getMessage());
            throw new JspException("Property specified in tag attribute not found.", e);
        }
        label.append(COPYRIGHT_SYMBOL).append(WHITESPACE)
                .append(year).append(WHITESPACE)
                .append(companyName)
                .append(BR_TAG).append(bundleValue);
        return label.toString();
    }

    private ResourceBundle getResourceBundle(String locale) {
        Locale messageLocale = new Locale(locale);
        return ResourceBundle.getBundle(BUNDLE_NAME, messageLocale);
    }

    private String getProperty(ResourceBundle bundle, String key) {
        return bundle.getString(key);
    }
}
