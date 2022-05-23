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

    private Document onlyDocument()
    {
        final List<Document> documents = system.contents();
        return documents.get(0);
    }

}