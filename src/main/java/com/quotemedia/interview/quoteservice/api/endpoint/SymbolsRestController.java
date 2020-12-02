package com.quotemedia.interview.quoteservice.api.endpoint;

import com.quotemedia.interview.quoteservice.api.hateoas.QuoteRepresentationModel;
import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Symbols API Rest controller.
 */
@RestController
@RequestMapping(value = "/api/symbols",produces = "application/json")
public class SymbolsRestController {

    @Autowired
    private QuoteService quoteService;

    /**
     * Get lastest quote by symbol.
     *
     * @return the response entity
     */
    @GetMapping("/{symbol}/quotes/lastest")
    public ResponseEntity getLastestQuote(@PathVariable("symbol")String symbolName){
        if (symbolName == null || symbolName.length() < 2 || symbolName.length() > 6){
            return ResponseEntity.badRequest().build();
        }
        Quote quote = quoteService.getLastestBySymbolName(symbolName);
        if (quote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(QuoteRepresentationModel.toModel(quote));
    }
}
