package com.quotemedia.interview.quoteservice.api.endpoint;

import com.quotemedia.interview.quoteservice.api.hateoas.QuoteRepresentationModel;
import com.quotemedia.interview.quoteservice.api.hateoas.SymbolRepresentationModel;
import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import com.quotemedia.interview.quoteservice.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

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
     * @param symbolName the symbol name
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

    /**
     * Gets symbol with highest ask.
     *
     * @param dayStr the day str
     * @return the highest ask
     */
    @GetMapping("/highestAsk")
    public ResponseEntity getHighestAsk(@RequestParam("day") String dayStr){
        Optional<LocalDate> day = DateUtils.parseLocalDate(dayStr);
        if (!day.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        String symbol = quoteService.getSymbolHighestAskByDay(day.get());
        if (symbol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SymbolRepresentationModel.toModel(symbol));
    }


}
