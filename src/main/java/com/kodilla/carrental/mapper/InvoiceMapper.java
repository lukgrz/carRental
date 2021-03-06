package com.kodilla.carrental.mapper;

import com.kodilla.carrental.domain.Invoice;
import com.kodilla.carrental.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceMapper {

    @Autowired
    private RentMapper rentMapper;

    public InvoiceDto mapToInvoiceDto (Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto(invoice.getId(),rentMapper.mapToRentDto(invoice.getRent()),
                invoice.getNumber(), invoice.getDateOfIssue());
        return invoiceDto;
    }

    public Invoice mapToInvoice (InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice(invoiceDto.getId(),invoiceDto.getNumber(), invoiceDto.getDateOfIssue(),
                rentMapper.mapToRent(invoiceDto.getRentDto()));
        return invoice;
    }

    public List<InvoiceDto> mapToInvoiceDtoList (List<Invoice> invoices) {
        return invoices.stream()
                .map(this::mapToInvoiceDto)
                .collect(Collectors.toList());
    }
}
