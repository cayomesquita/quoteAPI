package com.quotemedia.interview.quoteservice.persistence;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

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

    /**
     * Find quote with hieghest ask by day.
     *
     * @param date the date
     * @return the quote
     */
    Quote findFirstByDayOrderByAskDesc(Date date);
}
