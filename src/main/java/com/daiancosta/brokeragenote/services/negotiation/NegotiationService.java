package com.daiancosta.brokeragenote.services.negotiation;

import com.daiancosta.brokeragenote.domain.entities.Negotiation;

import java.util.List;

public interface NegotiationService {
    boolean save(final List<Negotiation> negotiations);
}
