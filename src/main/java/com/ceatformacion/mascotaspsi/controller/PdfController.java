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

// Indica que esta clase es un controlador REST (responde con JSON, PDF, etc., no vistas HTML)
@RestController
public class PdfController {

    // Inyección automática del repositorio de mascotas para acceder a los datos desde la base de datos
    @Autowired
    private MascotaRepository mascotaRepository;

    // Inyección automática del servicio que genera el PDF
    @Autowired
    private PdfService pdfService;

    // Mapeo del endpoint GET "/pdf". Este método se ejecuta cuando se accede a esa URL
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPDF() {

        // Se obtienen todas las mascotas de la base de datos
        List<Mascotas> mascotas = mascotaRepository.findAll();

        // Se genera el PDF con los datos de las mascotas (el PDF se devuelve como flujo de bytes)
        ByteArrayInputStream pdfStream = pdfService.exportarEmpleados(mascotas);

        // Se crean cabeceras HTTP para indicar que el archivo será mostrado como PDF en el navegador
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=clientes.pdf"); // se puede cambiar "clientes" por "mascotas"

        // Se construye y devuelve la respuesta HTTP con:
        // - código 200 OK
        // - tipo de contenido "application/pdf"
        // - las cabeceras configuradas
        // - el contenido del PDF leído como array de bytes
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes()); // convierte el flujo a un array de bytes
    }
}

