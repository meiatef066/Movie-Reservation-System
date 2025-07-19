package com.system.movie_reservation_system.dto.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpCustomResponse<T> {
    private int statusCode;
    private T content;
    private String message;

    public HttpCustomResponse(int statusCode, T content, String message) {
        this.statusCode = statusCode;
        this.content = content;
        this.message = message;
    }
}
