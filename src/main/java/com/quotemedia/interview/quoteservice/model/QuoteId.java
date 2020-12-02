package com.quotemedia.interview.quoteservice.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * The type Quote id.
 */
public class QuoteId implements Serializable {
    private String symbol;
    private Date day;

    /**
     * Instantiates a new Quote id.
     */
    public QuoteId() {
    }

    /**
     * Instantiates a new Quote id.
     *
     * @param symbol the symbol
     * @param day    the day
     */
    public QuoteId(String symbol, Date day) {
        this();
        this.symbol = symbol;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuoteId quoteId = (QuoteId) o;

        if (!symbol.equals(quoteId.symbol)) return false;
        return day.equals(quoteId.day);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + day.hashCode();
        return result;
    }
}
