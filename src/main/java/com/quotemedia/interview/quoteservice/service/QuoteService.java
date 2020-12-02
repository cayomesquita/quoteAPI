package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;

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
}
