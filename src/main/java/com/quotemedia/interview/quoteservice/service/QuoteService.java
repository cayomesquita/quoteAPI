package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;

import java.time.LocalDate;

/**
 * The interface Quote service.
 */
public interface QuoteService {

    /**
     * Gets lastest quote by symbol name.
     *
     * @param symbolName the symbol name
     * @return the lastest by symbol name
     */
    Quote getLastestBySymbolName(String symbolName);

    /**
     * Gets symbol highest ask by day.
     *
     * @param day the day
     * @return the symbol highest quote by day
     */
    String getSymbolHighestAskByDay(LocalDate day);
}
