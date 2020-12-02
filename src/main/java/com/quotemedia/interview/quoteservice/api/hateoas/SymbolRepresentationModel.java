package com.quotemedia.interview.quoteservice.api.hateoas;

import com.quotemedia.interview.quoteservice.api.endpoint.SymbolsRestController;
import com.quotemedia.interview.quoteservice.model.Quote;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Symbol representation model.
 */
public class SymbolRepresentationModel extends RepresentationModel {

    /**
     * The Symbol.
     */
    public String symbol;

    /**
     * To model representation model.
     *
     * @param symbol the symbol
     * @return the representation model
     */
    public static RepresentationModel toModel(String symbol){
        SymbolRepresentationModel model = new SymbolRepresentationModel();
        model.symbol = symbol;
        return model.add(linkTo(methodOn(SymbolsRestController.class).getLastestQuote("{symbolName}"))
                .withRel("Quotes")
                .withName("Lastest quote by symbol")
                .withType("GET")
        ,linkTo(methodOn(SymbolsRestController.class).getHighestAsk("{day}"))
                        .withRel("Symbols")
                        .withName("Symbol with Highest ask on the day")
                        .withType("GET"));
    }
}
