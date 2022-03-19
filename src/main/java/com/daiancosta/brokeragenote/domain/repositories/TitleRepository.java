package com.daiancosta.brokeragenote.domain.repositories;

import com.daiancosta.brokeragenote.domain.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
    @Query("SELECT t.code FROM Title t WHERE t.name like %:name%")
    List<String> findByName(@Param("name") final String name);
}
