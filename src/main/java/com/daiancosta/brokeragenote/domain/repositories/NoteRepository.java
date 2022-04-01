package com.daiancosta.brokeragenote.domain.repositories;

import com.daiancosta.brokeragenote.domain.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n.id FROM Note n WHERE n.number =:number")
    Long findByNumber(@Param("number") final String number);

    @Query("SELECT n.id FROM Note n WHERE n.number IN(:numbers)")
    List<Long> findByNumbers(@Param("numbers") final List<String> numbers);
}
