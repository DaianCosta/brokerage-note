package com.daiancosta.brokeragenote.domain.entities.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseMessage implements Serializable {
    private String message;
    private Object data;
}
