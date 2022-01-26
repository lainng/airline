package com.epamtc.airline.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final long serialVersionUID = 1L;
    private static final String COPYRIGHT_SYMBOL = "©";
    private static final String WHITESPACE = " ";
    private static final String BR_TAG = "<br>";

    private String year;
    private String companyName;
    private String statementOfRights;

    public void setYear(String year) {
        this.year = year;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStatementOfRights(String statementOfRights) {
        this.statementOfRights = statementOfRights;
    }

    @Override
    public int doStartTag() throws JspException {
        String copyrightLabel = makeCopyrightLabel();
        try {
            JspWriter out = pageContext.getOut();
            out.write(copyrightLabel);
        } catch (IOException e) {
            LOGGER.error("Unable to write to output stream. {}", e.getMessage());
            throw new JspException("Unable to write to output stream.", e);
        }
        return SKIP_BODY;
    }

    private String makeCopyrightLabel() {
        StringBuilder label = new StringBuilder();
        label.append(COPYRIGHT_SYMBOL).append(WHITESPACE)
                .append(year).append(WHITESPACE)
                .append(companyName)
                .append(BR_TAG).append(statementOfRights);
        return label.toString();
    }
}
