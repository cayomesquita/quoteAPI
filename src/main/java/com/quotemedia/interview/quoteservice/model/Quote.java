package com.quotemedia.interview.quoteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Date;

/**
 * The type Quote.
 */
@Entity(name = "QUOTE")
@IdClass(QuoteId.class)
public class Quote implements Serializable {

    @Id
    private String symbol;

    @Id
    private Date day;

    @Column
    private Float bid;

    @Column
    private Float ask;

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets symbol.
     *
     * @param symbol the symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public Date getDay() {
        return day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(Date day) {
        this.day = day;
    }

    /**
     * Gets bid.
     *
     * @return the bid
     */
    public Float getBid() {
        return bid;
    }

    /**
     * Sets bid.
     *
     * @param bid the bid
     */
    public void setBid(Float bid) {
        this.bid = bid;
    }

    /**
     * Gets ask.
     *
     * @return the ask
     */
    public Float getAsk() {
        return ask;
    }

    /**
     * Sets ask.
     *
     * @param ask the ask
     */
    public void setAsk(Float ask) {
        this.ask = ask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (!symbol.equals(quote.symbol)) return false;
        return day.equals(quote.day);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + day.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "symbol='" + symbol + '\'' +
                ", day=" + day +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}

