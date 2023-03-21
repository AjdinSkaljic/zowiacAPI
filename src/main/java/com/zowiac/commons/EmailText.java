package com.zowiac.commons;

import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderPositionEntity;

import java.util.HashSet;

public class EmailText {

    public final static String KONF_NAME = "ZOWIAC - Wildtierforschung";

    public static String createTextRechnung(OrderEntity orderEntity) {
        return "Guten Tag, \n\n" +
                "wir möchten uns bei Ihnen für Ihre Anmeldung zur Konferenz " + KONF_NAME + " bedanken.\n" +
                "Im Anhang finden Sie Ihre Rechnung für die Teilnahme mit der Bestellnummer " + orderEntity.getId().toString() + ". Bitte überprüfen Sie die " +
                "Rechnung auf Richtigkeit und kontaktieren Sie uns umgehend, wenn Sie Fehler oder Unstimmigkeiten feststellen.\n\n" +
                "Wir bedanken uns erneut für Ihre Bestellung und freuen uns darauf, Sie bei der Konferenz begrüßen zu dürfen.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "ZOWIAC-Team";
    }


    public static String createTeilnahme(OrderEntity orderEntity) {
        String text = "Guten Tag, \n\n" +
                "wir freuen uns, Ihnen mitteilen zu können, dass Sie erfolgreich für die Konferenz " + KONF_NAME + " registriert wurden. \n\n" +
                "Die Konferenz findet statt am :\n" +
                "14.09.2023 09:00 - 18:00\n" +
                "15.09.2023 09:00 - 15:00\n\n" +
                "im \n" +
                "Uni Campus XXX\n\n" +
                "Registrierte Teilnehmer/innen:";

        for (OrderPositionEntity orderPositionEntity : orderEntity.getVisitors())
            text += "\n" + orderPositionEntity.getName();


        text += "\n\nDer genaue Zeitplan und der Einzelheiten zu den einzelnen Veranstaltungen finden Sie auf unseren Web-Seite. " +
                "Wir empfehlen Ihnen, diese Informationen sorgfältig zu lesen und sich auf die Konferenz vorzubereiten, um das Beste aus Ihrer Teilnahme herauszuholen.\n\n" +
                "Wir sind zuversichtlich, dass die Konferenz Ihnen die Möglichkeit bieten wird, wertvolle Einblicke zu gewinnen " +
                "und sich mit anderen Fachleuten zu vernetzen. Die Teilnahme an der Konferenz wird Ihnen die Chance geben, sich weiterzubilden, " +
                "über die neuesten Trends und Entwicklungen in Ihrer Branche zu informieren und wertvolle Kontakte zu knüpfen.\n\n" +
                "Als Teilnehmer/in der Konferenz erhalten Sie Zugang zu allen Veranstaltungen und Vorträgen. \n\n" +
                "Vielen Dank für Ihre Teilnahme an der Konferenz " + KONF_NAME + ". Wir freuen uns darauf, Sie auf der Veranstaltung begrüßen zu dürfen.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "ZOWIAC-Team";


        return text;
    }

    public static String createBestellbestaetigung(OrderEntity orderEntity) {
        String text = "Guten Tag, \n\n" +
                "vielen Dank für Ihre Anmeldung zur Konferenz " + KONF_NAME + ". Wir haben Ihre Anmeldung erhalten und werden uns umgehend darum kümmern.\n\n" +
                "Anmeldenummer: " + orderEntity.getId() + " \n" +
                "Anmeldedatum: 13.03.2023\n\n" +
                "Teilnehmer/innen:\n";

        for (OrderPositionEntity orderPositionEntity : orderEntity.getVisitors())
            text += "\n * " + orderPositionEntity.getName();

        if (orderEntity.getPosters() != null && orderEntity.getPosters().size() > 0) {
            text += "\n\nPoster:\n";
            for (OrderPositionEntity orderPositionEntity : orderEntity.getPosters())
                text += "\n * " + orderPositionEntity.getName();
        }

        text += "\n\nIhre Anmeldenummer lautet " + orderEntity.getId() + ". Bitte bewahren Sie diese Nummer für Ihre Unterlagen auf, falls Sie später Fragen zu Ihrer Anmeldung haben sollten.\n\n" +
                "Nach der Bearbeitung Ihrer Anmeldung werden wir Ihnen eine separate E-Mail mit Rechnung  zusenden und eine E-Mail mit weiteren Informationen zur Konferenz.\n\n" +
                "Mit freundlichen Grüßen,\n" +
                "ZOWIAC-Team";


        return text;
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


        orderEntity.setName("Max Mustermann");
        orderEntity.setEmail("info@test.de");
        orderEntity.setStreet("Teststrasse 1");
        orderEntity.setZip("12345");
        orderEntity.setCity("Teststadt");


        System.out.println(createBestellbestaetigung(orderEntity));


        System.out.println(createTextRechnung(orderEntity));

        System.out.println(createTeilnahme(orderEntity));

    }


}
