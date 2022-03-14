package com.daiancosta.brokeragenote.domain.repositories;

import com.daiancosta.brokeragenote.domain.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n.id FROM Note n WHERE n.number =:number")
    Long findByDescription(@Param("number") final String number);
}
