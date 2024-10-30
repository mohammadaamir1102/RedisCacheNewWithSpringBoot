package com.amir.redis.service;

import com.amir.redis.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice saveInvoice(Invoice inv);
    Invoice updateInvoice(Invoice inv, Integer invId);
    void deleteInvoice(Integer invId);
    Invoice getOneInvoice(Integer invId);
    List<Invoice> getAllInvoices();
}
