package com.daiancosta.brokeragenote.domain.repositories;

import com.daiancosta.brokeragenote.domain.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Integer> {
}
