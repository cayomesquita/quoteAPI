package com.quotemedia.interview.quoteservice.api.hateoas;

import com.quotemedia.interview.quoteservice.api.endpoint.SymbolsRestController;
import com.quotemedia.interview.quoteservice.model.Quote;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Quote representation model.
 */
public class QuoteRepresentationModel extends RepresentationModel {

    /**
     * The Bid.
     */
    public String bid;

    /**
     * The Ask.
     */
    public String ask;

    /**
     * To model representation model.
     *
     * @param quote the quote
     * @return the representation model
     */
    public static RepresentationModel toModel(Quote quote){
        QuoteRepresentationModel model = new QuoteRepresentationModel();
        model.ask = String.format("%.2f", quote.getAsk());
        model.bid = String.format("%.2f", quote.getBid());
        return model.add(linkTo(methodOn(SymbolsRestController.class).getLastestQuote("{symbolName}"))
                .withRel("Quotes")
                .withName("Lastest quote by symbol")
                .withType("GET"));
    }
}
