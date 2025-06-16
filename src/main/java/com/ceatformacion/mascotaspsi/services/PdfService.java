package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Mascotas;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream exportarEmpleados(List<Mascotas> cliente) {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Listado de Pacientes Mascotas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(6); // 4 columnas
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 3,1,3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            table.addCell(new PdfPCell(new Phrase("ID", headFont)));
            table.addCell(new PdfPCell(new Phrase("Nombre", headFont)));
            table.addCell(new PdfPCell(new Phrase("Especie", headFont)));
            table.addCell(new PdfPCell(new Phrase("Raza", headFont)));
            table.addCell(new PdfPCell(new Phrase("Edad", headFont)));
            table.addCell(new PdfPCell(new Phrase("DNI Dueño", headFont)));


            for (Mascotas cli : cliente) {
                table.addCell(String.valueOf(cli.getIdMascota()));
                table.addCell(cli.getNombre());
                table.addCell(cli.getEspecie());
                table.addCell(cli.getRaza());

                table.addCell(String.valueOf(cli.getEdad()));
                table.addCell(cli.getDni_propietario());


            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}