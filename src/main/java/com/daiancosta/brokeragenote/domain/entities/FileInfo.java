package com.daiancosta.brokeragenote.domain.entities;

import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileInfo {
    private String name;
    private TypeFileEnum type;
    private String url;
    private String password;
}
