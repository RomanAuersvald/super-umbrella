package com.example.reported.data.jpa.dao;

import com.example.reported.data.jpa.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    Collection<Invoice> findAllByUserId(String id);
    Collection<Invoice> findAllByUserIdOrderByIdAsc(String id);
    Collection<Invoice> findAllByProjectId(String id);
    Collection<Invoice> findAllByProjectIdOrderByCreatedDesc(String id);
}
