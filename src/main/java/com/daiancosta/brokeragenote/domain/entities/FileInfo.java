package com.daiancosta.brokeragenote.domain.entities;

import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FileInfo implements Serializable {
    private String name;
    private TypeFileEnum type;
    private String url;
    private String password;
}
