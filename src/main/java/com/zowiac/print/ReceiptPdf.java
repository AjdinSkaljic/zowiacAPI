package com.zowiac.print;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderPositionEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

        document.setFontSize(10);
        document.setLeftMargin(60);

        document.add(createAbsender());

        document.add(createRechnungsinfo());

        document.add(createLeistungen());

        document.add(createZahlungsbedingungen());

        document.add(createZahlungsaufforderung());

        document.add(createUnterschrift());

        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());

        addLogo(document);

        document.close();
        return byteArrayOutputStream.toByteArray();
    }


    private IBlockElement createAbsender() {
        Paragraph paragraph = new Paragraph();
        paragraph.setFixedLeading(12);
        paragraph.add(new Text("\n\n\n\n\n\n"));
        paragraph.add(new Text("Goethe-Universität Frankfurt am Main, xxxx, 60323 Frankfurt am Main\n").setUnderline().setFontSize(8));
        paragraph.add(new Text("\n").setFontSize(6));
        paragraph.add(new Text(order.getName() + "\n").setFontSize(12));
        paragraph.add(new Text(order.getStreet() + "\n").setFontSize(12));
        paragraph.add(new Text("\n").setFontSize(4));
        paragraph.add(new Text(order.getZip() + " " + order.getCity() + "\n").setFontSize(12));


        return paragraph;
    }


    private IBlockElement createLeistungen() {
        UnitValue[] widths = {UnitValue.createPercentValue(55), UnitValue.createPercentValue(15), UnitValue.createPercentValue(15), UnitValue.createPercentValue(15)};
        Table table = new Table(widths);
        table.setMarginTop(50);
        table.setWidth(UnitValue.createPercentValue(100));


        table.setFontSize(10);
        table.setBorder(Border.NO_BORDER);


        Cell cell = new Cell().setPadding(3).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).add(new Paragraph("Leistung"));
        table.addCell(cell);
        cell = new Cell().setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).add(new Paragraph("Menge"));
        table.addCell(cell);
        cell = new Cell().setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).setHorizontalAlignment(HorizontalAlignment.RIGHT).add(new Paragraph("Preis"));
        table.addCell(cell);
        cell = new Cell().setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).setHorizontalAlignment(HorizontalAlignment.RIGHT).add(new Paragraph("Gesamtpreis"));
        table.addCell(cell);


        for (OrderPositionEntity visitor : order.getVisitors()) {
            cell = new Cell().setPadding(3).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).add(new Paragraph("Teilnahmegebühr " + visitor.getName() + "\ninkl. Verpflegung"));
            table.addCell(cell);
            cell = new Cell().setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).add(new Paragraph("1"));
            table.addCell(cell);
            cell = new Cell().setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).setHorizontalAlignment(HorizontalAlignment.RIGHT).add(new Paragraph("20 €"));
            table.addCell(cell);
            cell = new Cell().setHorizontalAlignment(HorizontalAlignment.RIGHT).setBorder(Border.NO_BORDER).setBorderBottom(new SolidBorder(0.5f)).add(new Paragraph("20 €").setHorizontalAlignment(HorizontalAlignment.RIGHT));
            table.addCell(cell);
        }

        cell = new Cell(1, 3).setPadding(3).setBorder(Border.NO_BORDER).add(new Paragraph(new Text("Gesamtbetrag").setBold()));
        table.addCell(cell);
        cell = new Cell().setPadding(2).setBorder(Border.NO_BORDER).add(new Paragraph(new Text("20 €").setBold()));
        table.addCell(cell);

        return table;


    }


    private IBlockElement createZahlungsbedingungen() {
        Paragraph paragraph = new Paragraph();
        paragraph.setMarginTop(30);
        paragraph.setFixedLeading(12);
        paragraph.add(new Text("Die sonstige Leistungen sind steuerfrei nach § 4 Nr 22 a) UStG.\n\n"));
        paragraph.add(new Text("Zahlbar innerhalb von 14 Tagen nach Rechnungsdatum.\n"));

        return paragraph;
    }

    private IBlockElement createUnterschrift() {
        Paragraph paragraph = new Paragraph();
        paragraph.setMarginTop(20);
        paragraph.setFixedLeading(12);
        paragraph.add(new Text("Diese Rechnung wurde elektronisch erstellt und ist ohne Unterschrift gültig.\n"));

        return paragraph;
    }


    private IBlockElement createZahlungsaufforderung() {
        UnitValue[] widths = {UnitValue.createPercentValue(10), UnitValue.createPercentValue(90)};

        Table table = new Table(widths).setMarginTop(20).setFontSize(10).setWidth(UnitValue.createPercentValue(100));
        table.setBackgroundColor(new DeviceRgb(232, 232, 232));
        Cell cell = new Cell(1, 2).add(new Paragraph("Der aktuell zu zahlende Betrag beträgt 20 €")).setBorder(Border.NO_BORDER);
        table.addCell(cell);

        table.addCell(getCell("Kontoinhaber:", true, false));
        table.addCell(getCell("Goethe-Universität Frankfurt am Main", true, false));
        table.addCell(getCell("Bank:", true, false));
        table.addCell(getCell("Helaba Landesbank Hessen-Thüringen", true, false));
        table.addCell(getCell("IBAN:", true, false));
        table.addCell(getCell("DE 95 5005 0000 0001 0064 10", true, false));
        table.addCell(getCell("BIC:", true, false));
        table.addCell(getCell("HELADEFFXXX", true, false));
        table.addCell(getCellBold("Verwendungszweck:", true, false, 1));
        table.addCell(getCellBold(order.getReceiptId().toString(), true, false, 1));

        return table;
    }


    private IBlockElement createRechnungsinfo() {
        Table tableContainer = new Table(1);
        tableContainer.setMarginTop(20);
        tableContainer.setHorizontalAlignment(HorizontalAlignment.RIGHT);

        Table table = new Table(2);
        table.setMargin(5);
        table.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        table.addCell(getCellBold("Rechnung", true, false, 2));
        table.addCell(getCell("Rechnungsnummer: ", true, false));
        table.addCell(getCell(order.getReceiptId().toString(), true, false));
        table.addCell(getCell("Datum: ", true, false));
        table.addCell(getCell(order.getReceiptDateFormatted(), true, false));
        table.addCell(getCell("Bestellnummer: ", true, false));
        table.addCell(getCell(order.getId().toString(), true, false));
        table.addCell(getCell("E-Mail: ", true, false));
        table.addCell(getCell("info@zowiac.eu", true, false));
        table.addCell(getCell("Tel.: ", true, false));
        table.addCell(getCell("+49 69 498 420 69", true, false));

        tableContainer.addCell(table);
        return tableContainer;


    }


    public Cell getCell(String text, boolean border, boolean padding) {
        Cell cell = new Cell().add(new Paragraph(text));
        if (border) {
            cell.setBorder(Border.NO_BORDER);
        }
        if (!padding) {
            cell.setPadding(0);
        }
        return cell;
    }

    public Cell getCellBold(String text, boolean border, boolean padding, int colspan) {
        Cell cell = new Cell(1, colspan).add(new Paragraph(new Text(text).setBold()));
        if (border) {
            cell.setBorder(Border.NO_BORDER);
        }
        if (padding) {
            cell.setPadding(0);
        }
        return cell;
    }


    private void addLogo(Document document) {
        try {
            String imageFile = "C:\\Temp/zowiac\\logo.png";
            ImageData data = ImageDataFactory.create(imageFile);
            Image img = new Image(data);
            img.scaleToFit(100, 100);
            img.setFixedPosition(450, 760);
            document.add(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String imageFile = "C:\\Temp/zowiac\\logo.png";
            ImageData data = ImageDataFactory.create(imageFile);
            Image img = new Image(data);
            img.scaleToFit(100, 100);
            img.setFixedPosition(450, 700);
            document.add(img);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public OrderEntity getOrder() {
        return order;
    }

    //create main  method
    public static void main(String[] args) throws IOException {
        OrderEntity order = new OrderEntity();
        order.setName("Max Mustermann");
        order.setStreet("Musterstraße 1");
        order.setZip("12345");
        order.setCity("Musterstadt");

        order.setId(new Long(123456));
        order.setReceiptDate(new Date());
        order.setReceiptId(new Long(123456));

        OrderPositionEntity positionEntity = new OrderPositionEntity();
        positionEntity.setName("Mustermann");
        positionEntity.setDescription("Musterbeschreibung");
        positionEntity.setEmail("muster@test.de");
        positionEntity.setType(OrderPositionEntity.TYPE_VISITOR);
        positionEntity.setOrder(order);

        Set<OrderPositionEntity> positions = new HashSet<>();


        positions.add(positionEntity);

        positionEntity = new OrderPositionEntity();
        positionEntity.setName("Musterfrau");
        positionEntity.setDescription("Musterbeschreibung");
        positionEntity.setEmail("muster@test.de");
        positionEntity.setType(OrderPositionEntity.TYPE_VISITOR);
        positionEntity.setOrder(order);

        positions.add(positionEntity);
        order.setOrderPositions(positions);


        ReceiptPdf receiptPdf = new ReceiptPdf(order);
        byte[] data = receiptPdf.createPdf();
        File file = new File("C:\\Temp\\zowiac\\test.pdf");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();


    }

    private static class FooterEventHandler implements IEventHandler {


        public FooterEventHandler() {
        }

        @Override
        public void handleEvent(Event currentEvent) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            new Canvas(canvas, new Rectangle(60, 0, page.getPageSize().getWidth() - 60, 60))
                    .add(createFooterTable())
                    .close();
        }

        private IBlockElement createFooterTable() {
            UnitValue[] widths = {UnitValue.createPercentValue(60), UnitValue.createPercentValue(40)};
            Table table = new Table(widths);
            table.setWidth(UnitValue.createPercentValue(100));
            table.setFontSize(8);


            table.addCell(getCell("Goethe-Universität Frankfurt am Main", HorizontalAlignment.LEFT));
            table.addCell(getCell("Bank: Helaba Landesbank Hessen-Thüringen", HorizontalAlignment.RIGHT));

            table.addCell(getCell("Steuernummer: 045 226 58 002", HorizontalAlignment.LEFT));
            table.addCell(getCell("IBAN: DE 95 5005 0000 0001 0064 10", HorizontalAlignment.RIGHT));

            table.addCell(getCell("USt-ID Nr: DE 114 110 511", HorizontalAlignment.LEFT));
            table.addCell(getCell("BIC: HELADEFFXXX", HorizontalAlignment.RIGHT));


            table.addCell(getCell("EORI Nr.: DE3930017", HorizontalAlignment.LEFT));
            table.addCell(getCell(" ", HorizontalAlignment.RIGHT));

            return table;

        }

        // get Cell horizontal alignment
        public Cell getCell(String text, HorizontalAlignment horizontalAlignment) {
            Cell cell = new Cell().add(new Paragraph(text));
            cell.setBorder(Border.NO_BORDER);
            cell.setPadding(0);
            cell.setMargin(0);

            cell.setHorizontalAlignment(horizontalAlignment);
            return cell;
        }
    }
}