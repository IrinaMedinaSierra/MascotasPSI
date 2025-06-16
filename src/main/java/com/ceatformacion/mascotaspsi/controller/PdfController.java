package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class PdfController {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPDF() {
       List<Mascotas> mascotas = mascotaRepository.findAll();
        ByteArrayInputStream pdfStream = pdfService.exportarEmpleados(mascotas);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=clientes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
}
