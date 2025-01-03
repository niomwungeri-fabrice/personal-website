package io.lynx.api.controllers;

import io.lynx.api.dtos.CreateContactMessageRequest;
import io.lynx.api.services.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/contacts")

public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping
    public ResponseEntity<?> saveContact(@RequestBody @Valid CreateContactMessageRequest createContactMessageRequest) {
        contactMessageService.saveContact(createContactMessageRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(contactMessageService.getAllMessages());
    }

}
