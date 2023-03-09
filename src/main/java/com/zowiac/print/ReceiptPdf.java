package com.zowiac.print;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.zowiac.model.OrderEntity;
import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ReceiptPdf {

    private final OrderEntity order;

    public ReceiptPdf(OrderEntity order) {
        this.order = order;
    }

    public byte[] createPdf() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);


        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        document.add(new Paragraph("Rechnung:").setFont(font));
        // Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        // Add ListItem objects
        list.add(new ListItem("Positionen"))
                .add(new ListItem("Poster"))
                .add(new ListItem("Logo"))
                .add(new ListItem("Preis"))
                .add(new ListItem("Bankverbindung"));
        // Add the list
        document.add(list);

        //Close document
        document.close();
       return byteArrayOutputStream.toByteArray();
    }

    public OrderEntity getOrder() {
        return order;
    }

    //create main  method
    public static void main(String[] args) throws IOException {
        OrderEntity order = new OrderEntity();
        ReceiptPdf receiptPdf = new ReceiptPdf(order);
        byte[] data = receiptPdf.createPdf();
    }
}
