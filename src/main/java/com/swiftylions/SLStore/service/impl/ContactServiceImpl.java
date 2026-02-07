package com.swiftylions.SLStore.service.impl;

import com.swiftylions.SLStore.constants.ApplicationConstants;
import com.swiftylions.SLStore.dto.ContactRequestDto;
import com.swiftylions.SLStore.dto.ContactResponseDto;
import com.swiftylions.SLStore.entity.Contact;
import com.swiftylions.SLStore.exception.ResourceNotFoundException;
import com.swiftylions.SLStore.repository.ContactRepository;
import com.swiftylions.SLStore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        Contact contact = transformToEntity(contactRequestDto);
        contactRepository.save(contact);
        return true;

    }

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contacts =  contactRepository.fetchByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contacts.stream().map(this::mapToContactResponseDto).collect(Collectors.toList());

    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "ContactID", contactId.toString()));
        contact.setStatus(status);
        contactRepository.save(contact);
    }

    private ContactResponseDto mapToContactResponseDto(Contact contact) {
        ContactResponseDto responseDTO = new ContactResponseDto(
                contact.getContactId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
        return responseDTO;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact;
    }


}
