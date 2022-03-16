package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Movement;
import com.daiancosta.brokeragenote.domain.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;

    @Autowired
    public MovementServiceImpl(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public boolean save(final List<Movement> movements) {
        try {
            deleteByData(movements);
            movementRepository.saveAll(movements);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void deleteByData(final List<Movement> movements) {
        final List<LocalDate> dates = movements.stream().map(Movement::getDate)
                .distinct()
                .collect(Collectors.toList());
        final List<Long> ids = movementRepository.findMovementByDatList(dates);
        if (ids != null) {
            movementRepository.deleteAllByIdInBatch(ids);
        }
    }

}
