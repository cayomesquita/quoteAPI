package com.quotemedia.interview.quoteservice.api.endpoint;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SymbolsRestController.class)
class SymbolsRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    void getLastestQuote() throws Exception {

        Quote quote1 = new Quote();
        quote1.setSymbol("AAA");
        quote1.setDay(Date.valueOf(LocalDate.now()));
        quote1.setAsk(1.23f);
        quote1.setBid(3.21f);

        Mockito.when(quoteService.getLastestBySymbolName(ArgumentMatchers.anyString())).thenReturn(quote1);

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/AAA/quotes/lastest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ask", Matchers.is("1.23")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bid", Matchers.is("3.21")));

        Mockito.verify(quoteService, Mockito.atLeastOnce()).getLastestBySymbolName(ArgumentMatchers.anyString());
    }

    @Test
    void getLastestQuoteMinLenght() throws Exception {

        Quote quote1 = new Quote();
        quote1.setSymbol("AAA");
        quote1.setDay(Date.valueOf(LocalDate.now()));
        quote1.setAsk(1.23f);
        quote1.setBid(3.21f);

        Mockito.when(quoteService.getLastestBySymbolName(ArgumentMatchers.anyString())).thenReturn(quote1);

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/A/quotes/lastest"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/AA/quotes/lastest"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(quoteService, Mockito.only()).getLastestBySymbolName(ArgumentMatchers.anyString());
    }

    @Test
    void getLastestQuoteMaxLenght() throws Exception {

        Quote quote1 = new Quote();
        quote1.setSymbol("AAA");
        quote1.setDay(Date.valueOf(LocalDate.now()));
        quote1.setAsk(1.23f);
        quote1.setBid(3.21f);

        Mockito.when(quoteService.getLastestBySymbolName(ArgumentMatchers.anyString())).thenReturn(quote1);

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/AAAAAAA/quotes/lastest"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/AAAAAA/quotes/lastest"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(quoteService, Mockito.only()).getLastestBySymbolName(ArgumentMatchers.anyString());
    }

    @Test
    void getHighestAsk() throws Exception {
        String symbol = "AAAA";

        Mockito.when(quoteService.getSymbolHighestAskByDay(ArgumentMatchers.any(LocalDate.class))).thenReturn(symbol);

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk?day=01-03-2020"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbol", Matchers.is(symbol)));

        Mockito.verify(quoteService, Mockito.atLeastOnce()).getSymbolHighestAskByDay(ArgumentMatchers.any(LocalDate.class));
    }

    @Test
    void getHighestAskNoResult() throws Exception {
        Mockito.when(quoteService.getSymbolHighestAskByDay(ArgumentMatchers.any(LocalDate.class))).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk?day=01-03-2020"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(quoteService, Mockito.atLeastOnce()).getSymbolHighestAskByDay(ArgumentMatchers.any(LocalDate.class));
    }

    @Test
    void getHighestAskDateBadFormat() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk?day=00-03-2020"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk?day=13-03-2020"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.get("/api/symbols/highestAsk?day="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(quoteService, Mockito.never()).getSymbolHighestAskByDay(ArgumentMatchers.any(LocalDate.class));
    }
}