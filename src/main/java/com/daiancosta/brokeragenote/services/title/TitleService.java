package com.daiancosta.brokeragenote.services.title;

import com.daiancosta.brokeragenote.domain.entities.Title;

import java.util.List;

public interface TitleService {

    List<Title> findAll();

    List<String> getByCode(final String name);
}
