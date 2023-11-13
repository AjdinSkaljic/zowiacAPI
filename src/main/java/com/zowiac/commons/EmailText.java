package com.zowiac.commons;

import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderPositionEntity;

import java.util.HashSet;

public class EmailText {

    public final static String KONF_NAME = "ZOWIAC Konferenz - Wildtierforschung";

    public static String createTextRechnung(OrderEntity orderEntity) {
        return "Sehr geehrte Damen und Herren, \n\n" +
                "wir möchten uns bei Ihnen für Ihre Anmeldung zur " + KONF_NAME + " bedanken.\n" +
                "Im Anhang finden Sie Ihre Rechnung für die Teilnahme mit der Bestellnummer " + orderEntity.getId().toString() + ".\n\n" +
                "Der Studentenstatus der Teilnehmen wird beim Einlass überprüft.\n\n " + //TODO: Nur wenn es um Studenetenanmeldung handelt
                "Bitte überprüfen Sie die Rechnung auf Richtigkeit und kontaktieren Sie uns umgehend, wenn Sie Fehler oder Unstimmigkeiten feststellen.\n\n" +
                "Wir freuen uns darauf, Sie bei der Konferenz begrüßen zu dürfen.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "Ihr ZOWIAC-Team";
    }


    public static String createTeilnahme(OrderEntity orderEntity) {
        String text = "Sehr geehrte Damen und Herren,\n\n" +
                "Wir freuen uns, Ihnen mitteilen zu können, dass Sie erfolgreich für die " + KONF_NAME + " registriert wurden. \n\n" +
                "Die Konferenz findet statt am:\n" +
                "14.09.2023 09:00 - 18:00\n" +
                "15.09.2023 09:00 - 15:00\n\n" +
                "im \n" +
                "Otto-Stern-Zentrum\n" +
                "Ruth-Moufang-Straße 2\n" +
                "60438 Frankfurt am Main\n\n" +
                "Registrierte Teilnehmer/innen:";

        for (OrderPositionEntity orderPositionEntity : orderEntity.getVisitors())
            text += "\n" + orderPositionEntity.getFullname();
        text += "\n\n";

        if (orderEntity.getPosters() != null && orderEntity.getPosters().size() > 0) {
            text += "Registrierte Poster:";
            for (OrderPositionEntity orderPositionEntity : orderEntity.getPosters())
                text += "\n" + orderPositionEntity.getName();
            text += "\n\n";
        }

        if (orderEntity.getSpeeches() != null && orderEntity.getSpeeches().size() > 0) {
            text += "Registrierte Vorträge:";
            for (OrderPositionEntity orderPositionEntity : orderEntity.getSpeeches())
                text += "\n" + orderPositionEntity.getName();
            text += "\n\n";
        }


        text += "Die " + KONF_NAME + " ist die Erste gesamtdeutsche Konferenz über gebietsfremde Wirbeltiere. Wir freuen uns sehr, " +
                "dass Sie sich für die Teilnahme angemeldet habe und das wir Sie zwischen dem 14. und 15. September in Frankfurt auf der Veranstaltung begrüßen dürfen.\n\n";
        text += "Wir sind der festen Überzeugung, dass es Sie und Ihre aktive Teilnahme sind, die diese Konferenz zu einer wertvollen Plattform für den Wissensaustausch \n" +
                "und den Fortschritt der Wissenschaft machen. Sie können dazu beitragen, dieses Ziel zu erreichen, indem Sie uns helfen, ZOWIAC bekannter zu machen.";
        text += "Informieren Sie Ihr berufliches Netzwerk über die Konferenz und laden Sie es zur Teilnahme ein.\n";

        text += "Für alle Informationen und Updates besuchen Sie bitte www.ZOWIAC.eu/Konferenz\n\n";

        text += "Mit freundlichen Grüßen,\n" +
                "Ihr ZOWIAC-Team";


        return text;
    }

    public static String createBestellbestaetigung(OrderEntity orderEntity) {
        String text = "Sehr geehrte Damen und Herren, \n\n" +
                "vielen Dank für Ihre Anmeldung zur " + KONF_NAME + ". Wir haben Ihre Anmeldung erhalten und werden uns umgehend darum kümmern.\n\n" +
                "Anmeldenummer: " + orderEntity.getId() + " \n" +
                "Anmeldedatum: " + orderEntity.getOrderDateFormatted() + "\n\n" +
                "Teilnehmer/innen:";

        for (OrderPositionEntity orderPositionEntity : orderEntity.getVisitors())
            text += "\n * " + orderPositionEntity.getFullname(); //TODO: Studenten Status hinzufügen

        if (orderEntity.getPosters() != null && orderEntity.getPosters().size() > 0) {
            text += "\n\nPoster:";
            for (OrderPositionEntity orderPositionEntity : orderEntity.getPosters())
                text += "\n * " + orderPositionEntity.getName();
            text += "\n";
        }

        if (orderEntity.getSpeeches() != null && orderEntity.getSpeeches().size() > 0) {
            text += "\n\nVorträge:";
            for (OrderPositionEntity orderPositionEntity : orderEntity.getPosters())
                text += "\n * " + orderPositionEntity.getName();
            text += "\n";
        }


        text += "\n\nIhre Anmeldenummer lautet " + orderEntity.getId() + ". Bitte bewahren Sie diese Nummer für Ihre Unterlagen auf, falls Sie später Fragen zu Ihrer Anmeldung haben sollten.\n\n" +
                "Nach der Bearbeitung Ihrer Anmeldung werden wir Ihnen eine separate E-Mail mit Rechnung zusenden und eine E-Mail mit weiteren Informationen zur Konferenz.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "Ihr ZOWIAC-Team";


        return text;
    }


    public static String createTeilnahmeFollowup(OrderPositionEntity orderEntity) {
        return orderEntity.getAnredeMail() + "\n\n" +

                "Es war uns eine große Freude, Sie bei der " + KONF_NAME + " begrüßen zu dürfen. Wir sind stolz darauf, dass die Veranstaltung ein großer Erfolg war und möchten uns herzlich bei Ihnen für Ihre Teilnahme und Ihr Engagement bedanken.\n\n" +

                "Um sicherzustellen, dass Sie den größtmöglichen Nutzen aus der Konferenz ziehen, freuen wir uns, Ihnen den Zugang zu den freigegebenen Vorträgen zur Verfügung zu stellen. Sie können die Dateien über den folgenden Link herunterladen:\n\n" +
                "https://zowiac.eu/vortrage-zowiac_konferenz_2023.zip\n\n" +

                "Falls Sie Fragen zu den Vorträgen haben oder weitere Informationen benötigen, zögern Sie bitte nicht, sich an uns zu wenden. Ihr Feedback ist uns ebenfalls sehr willkommen, da es uns hilft, zukünftige Veranstaltungen noch besser zu gestalten.\n\n" +

                "Wir möchten Sie auch darüber informieren, dass während der Konferenz Fotos aufgenommen wurden. " +
                "Diese Aufnahmen würden wir gerne in Teilen auf der Zowiac Webseite veröffentlichen. Sollten Sie Einwände gegen die Verwendung Ihrer Bilder haben, teilen Sie uns dies bitte umgehend mit.\n\n" +

                "Vielen Dank für Ihre Teilnahme und Ihr Interesse. \n\n" +
                "Mit freundlichen Grüßen,\n" +
                "Ihr ZOWIAC-Team";
    }


    public static void main(String[] args) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(123L);

        orderEntity.setOrderPositions(new HashSet<>());
        OrderPositionEntity orderPositionEntity = new OrderPositionEntity();
        orderPositionEntity.setName("Max Mustermann");
        orderPositionEntity.setType(OrderPositionEntity.TYPE_VISITOR);
        orderEntity.getOrderPositions().add(orderPositionEntity);
        orderPositionEntity = new OrderPositionEntity();
        orderPositionEntity.setName("Max Mustermann2");
        orderPositionEntity.setType(OrderPositionEntity.TYPE_VISITOR);
        orderEntity.getOrderPositions().add(orderPositionEntity);


        orderEntity.setFirstname("Max Mustermann");
        orderEntity.setEmail("info@test.de");
        orderEntity.setStreet("Teststrasse 1");
        orderEntity.setZip("12345");
        orderEntity.setCity("Teststadt");


        System.out.println(createBestellbestaetigung(orderEntity));


        System.out.println(createTextRechnung(orderEntity));

        System.out.println(createTeilnahme(orderEntity));

    }


}
