package com.quotemedia.interview.quoteservice.service.impl;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.persistence.QuoteRepository;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Quote service implementation.
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote getLastestBySymbolName(String symbolName) {
        return quoteRepository.findFirstBySymbolOrderByDayDesc(symbolName);
    }

}
