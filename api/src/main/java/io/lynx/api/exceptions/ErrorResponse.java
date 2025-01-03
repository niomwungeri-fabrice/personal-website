package io.lynx.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
}
