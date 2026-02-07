package com.swiftylions.SLStore.repository;

import com.swiftylions.SLStore.entity.Contact;
import com.swiftylions.SLStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByStatus(String status); //defined inside Contact entity class

    @Query(name = "Contact.findByStatus") //in order of changing method name
    List<Contact> fetchByStatus(String status);

    List<Contact> findByStatusWithNativeQuery(String status); //defined inside Contact entity class
  }