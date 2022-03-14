package com.daiancosta.brokeragenote.domain.entities.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private Object data;
}
