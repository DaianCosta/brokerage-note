package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Title;

import java.util.List;

public interface TitleService {

    List<Title> findAll();

    String getByCode(final String name);
}
