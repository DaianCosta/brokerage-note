package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.domain.entities.Title;
import com.daiancosta.brokeragenote.domain.repositories.TitleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
class TitleServiceImpl implements TitleService {

    private final List<Title> titles;
    private final TitleRepository titleRepository;

    TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
        this.titles = findAll();
    }

    @Cacheable
    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public String getByCode(final String name, final String nameOptional) {
        final String resultCode = titleRepository.findByName(name);
        if (resultCode != null) {
            return resultCode;
        } else {
            final String resultCodeOptional = titleRepository.findByName(name);
            if (resultCodeOptional != null) {
                return resultCodeOptional;
            } else {
                return nameOptional;
            }
        }
    }
}