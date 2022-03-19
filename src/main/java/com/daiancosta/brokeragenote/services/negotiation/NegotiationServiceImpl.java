package com.daiancosta.brokeragenote.services.negotiation;

import com.daiancosta.brokeragenote.domain.entities.Negotiation;
import com.daiancosta.brokeragenote.domain.repositories.NegotiationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NegotiationServiceImpl implements NegotiationService {

    private final NegotiationRepository negotiationRepository;

    @Autowired
    public NegotiationServiceImpl(NegotiationRepository negotiationRepository) {
        this.negotiationRepository = negotiationRepository;
    }

    @Override
    @Transactional
    public boolean save(final List<Negotiation> negotiations) {
        try {
            deleteByData(negotiations);
            negotiationRepository.saveAll(negotiations);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void deleteByData(final List<Negotiation> negotiations) {
        final List<LocalDate> dates = negotiations.stream().map(Negotiation::getDate)
                .distinct()
                .collect(Collectors.toList());
        final List<Long> ids = negotiationRepository.findNegotiationByDatList(dates);
        if (ids != null) {
            negotiationRepository.deleteAllByIdInBatch(ids);
        }
    }

}
