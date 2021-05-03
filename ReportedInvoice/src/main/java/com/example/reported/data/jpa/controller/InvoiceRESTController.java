package com.example.reported.data.jpa.controller;


import com.example.reported.data.jpa.dao.InvoiceRepository;
import com.example.reported.data.jpa.model.Invoice;
import com.example.reported.data.jpa.service.InvoiceService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

@RequestMapping(path = "/invoices")
@RestController
public class InvoiceRESTController {


    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceService invoiceService;

    // get All
    @GetMapping(path= "/all/{userID}")
    public ResponseEntity<Collection> getAllInvoices(@PathVariable String userID) {
        Collection<Invoice> invoices = invoiceRepository.findAllByUserId(userID);
        return ResponseEntity.ok().body(invoices);
    }

    // save
    @PostMapping("")
    ResponseEntity<Invoice> newInvoice(@RequestBody Invoice newInvoice) {
        invoiceRepository.save(newInvoice);
        System.out.println("invoice saved");
        return ResponseEntity.ok().build();
    }

    //get by ID
    @GetMapping("/{id}")
    ResponseEntity<Invoice> oneInvoice(@PathVariable String id) {
        Optional<Invoice> loadedInvoice = invoiceRepository.findById(id);
        if (loadedInvoice.get() != null) {
            return ResponseEntity.ok().body(loadedInvoice.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // delete by ID
    @GetMapping("/delete/{id}")
    ResponseEntity deleteInvoice(@PathVariable String id) {
        if (invoiceRepository.findById(id) != null) {
            invoiceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getPDF/{id}")
    ResponseEntity<byte[]> getPDFForInvoice(@PathVariable String id){
        Invoice invoice = invoiceRepository.findById(id).get();
        String filledHTML = invoiceService.parseThymeleafTemplate(invoice, invoice.getClientAddress(), invoice.getUserAddress());

        byte[] pdfContents = generetaPDF(filledHTML, invoice.getUserId());
        if (pdfContents != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            String filename = id + ".pdf";
            headers.add("content-disposition", "inline;filename=" + filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                    pdfContents, headers, HttpStatus.OK);
            return response;
        }else{
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                    HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    private byte[] generetaPDF(String forHTML, String userId){
        String outputFolder = System.getProperty("user.home") + File.separator + userId + ".pdf";
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFolder);
        } catch (FileNotFoundException e) {
            return null;
        }

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(forHTML);
        renderer.layout();
        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            return null;
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            return null;
        }

        Path path = Paths.get(outputFolder);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return null;
        }
    }
}


class InvoiceNotFoundException extends RuntimeException {
    InvoiceNotFoundException(String id) {
        super("Could not find Invoice " + id);
    }
}