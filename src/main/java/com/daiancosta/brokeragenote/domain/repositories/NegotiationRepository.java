package com.daiancosta.brokeragenote.domain.repositories;

import com.daiancosta.brokeragenote.domain.entities.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {
    @Query("SELECT m.id FROM Negotiation m WHERE m.date IN(:dates)")
    List<Long> findNegotiationByDatList (@Param("dates") final Collection<LocalDate> localDates);
}
