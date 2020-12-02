package com.quotemedia.interview.quoteservice.api.endpoint;

import com.quotemedia.interview.quoteservice.api.hateoas.QuoteRepresentationModel;
import com.quotemedia.interview.quoteservice.api.hateoas.SymbolRepresentationModel;
import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.service.QuoteService;
import com.quotemedia.interview.quoteservice.utils.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get lastest quote by symbol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lastest quote", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuoteRepresentationModel.class))})
            ,@ApiResponse(responseCode = "404", description = "Symbol not found", content = @Content)
            ,@ApiResponse(responseCode = "400", description = "Symbol invalid", content = @Content)
    })
    public ResponseEntity getLastestQuote(@Parameter(description = "Symbol name") @PathVariable("symbol") String symbolName){
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
    @Operation(summary = "Get symbol with highest ask on date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Symbol", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SymbolRepresentationModel.class))})
            ,@ApiResponse(responseCode = "404", description = "No quote found this date", content = @Content)
            ,@ApiResponse(responseCode = "400", description = "Date invalid", content = @Content)
    })
    public ResponseEntity getHighestAsk(@Parameter(description = "Date to search") @RequestParam("day") String dayStr){
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
