package com.quotemedia.interview.quoteservice.persistence;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Quote repository.
 */
public interface QuoteRepository extends JpaRepository<Quote, QuoteId> {

    /**
     * Find lastest order by symbol.
     *
     * @param symbol the symbol
     * @return the quote
     */
    Quote findFirstBySymbolOrderByDayDesc(String symbol);
}
