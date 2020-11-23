package com.WEBAPP.WEBAPP.model;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfTable;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;


public class TicketPDFExporter {

    private List<Tickets> listTickets;

    private String eventName;

    public TicketPDFExporter(List<Tickets> listTickets, String eventName){
        this.listTickets=listTickets;
        this.eventName = eventName;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Imię i nazwisko", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cena biletu", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(Tickets ticket : listTickets){
            table.addCell(String.valueOf(ticket.getUser().getName()));
            table.addCell(String.valueOf(ticket.getUser().getEmail()));
            table.addCell(String.valueOf(ticket.getTicketPrice())+" PLN");
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph(eventName, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        Paragraph p1 = new Paragraph("Lista uczestników", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p1);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 3.5f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
