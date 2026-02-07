package com.swiftylions.SLStore.service;

import com.swiftylions.SLStore.dto.ContactRequestDto;
import com.swiftylions.SLStore.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);

    List<ContactResponseDto> getAllOpenMessages();

    void updateMessageStatus(Long contactId, String status);

}
