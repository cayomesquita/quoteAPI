package com.quotemedia.interview.quoteservice.service.impl;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.persistence.QuoteRepository;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

/**
 * The Quote service implementation.
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    @Cacheable(value = "symbols")
    public Quote getLastestBySymbolName(String symbolName) {
        return quoteRepository.findFirstBySymbolOrderByDayDesc(symbolName);
    }

    @Override
    @Cacheable(value = "symbols")
    public String getSymbolHighestAskByDay(LocalDate day) {
        Date date = Date.valueOf(day);
        Quote quote = quoteRepository.findFirstByDayOrderByAskDesc(date);
        return (quote == null) ? null : quote.getSymbol();
    }

}
