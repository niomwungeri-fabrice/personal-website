package io.lynx.api.services;

import io.lynx.api.dtos.CreateContactMessageRequest;
import io.lynx.api.exceptions.BadRequestException;
import io.lynx.api.exceptions.InternalServerErrorException;
import io.lynx.api.models.ContactMessage;
import io.lynx.api.repositories.ContactMessageRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public void saveContact(CreateContactMessageRequest contact) {
        try {
            ContactMessage contactMessage = ContactMessage.builder()
                    .name(contact.getName())
                    .email(contact.getEmail())
                    .phone(contact.getPhone())
                    .message(contact.getMessage())
                    .title(contact.getTitle())
                    .build();
            contactMessageRepository.save(contactMessage);
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException(e.getConstraintViolations());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}
