package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Movement;

import java.util.List;

public interface MovementService {
    boolean save(final List<Movement> movements);
}
