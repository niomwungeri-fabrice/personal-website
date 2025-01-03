package io.lynx.api.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateContactMessageRequest {
    private String name;
    private String email;
    private String phone;
    private String title;
    private String message;
}
