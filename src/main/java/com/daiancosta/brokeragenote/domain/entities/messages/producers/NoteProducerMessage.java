package com.daiancosta.brokeragenote.domain.entities.messages.producers;

import com.daiancosta.brokeragenote.domain.entities.enums.TypeFileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteProducerMessage implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("file")
    private byte[] file;
    @JsonProperty("type")
    private TypeFileEnum type;
    @JsonProperty("password")
    private String password;
}
