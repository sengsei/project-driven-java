package chapter04;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static chapter04.Attributes.*;
import static org.junit.jupiter.api.Assertions.*;

class DocumentManagementSystemTest {
    private static final String RESOURCES =
            "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String ALICE = "Alice";
    private static final String LETTER = RESOURCES + "patient.letter";

    private final DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportImageAttributes() throws Exception
    {
        system.importFile(XRAY);

        final Document document = onlyDocument();

        assertEquals(document.getAttributes(WIDTH),"320");
        assertEquals(document.getAttributes(HEIGHT), "179");
        assertEquals(document.getAttributes(TYPE), "IMAGE");
    }

    @Test
    public void shouldImportInvoiceAttributes() throws Exception{
        system.importFile(INVOICE);

        final Document document = onlyDocument();

        assertEquals(document.getAttributes(PATIENT), ALICE);
        assertEquals(document.getAttributes(AMOUNT), "$100");
        assertEquals(document.getAttributes(TYPE), "INVOICE");

    }

    @Test
    public void shouldImportLetterAttributes() throws Exception{
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertEquals(document.getAttributes(PATIENT), ALICE);
        assertEquals(document.getAttributes(ADDRESS),
                """
                        123 Fake Street
                        Westminster
                        London
                        United Kingdom""");
        assertEquals(document.getAttributes(BODY),
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertEquals(document.getAttributes(TYPE), "LETTER");

    }

    private Document onlyDocument()
    {
        final List<Document> documents = system.contents();
        return documents.get(0);
    }

}