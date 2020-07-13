package nl.ivonet.comics.Archive;

import nl.ivonet.comics.archive.CbzArchiveReader;
import nl.ivonet.test.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CbzArchiveReaderTest {

    private CbzArchiveReader reader;

    @Before
    public void setUp() throws Exception {
        this.reader = new CbzArchiveReader();

    }

    @Test
    public void testPages() throws Exception {
        final File file = new File(Utils.getFileResource("comic.cbz"));
        final List<String> pages = this.reader.pages(file);
        assertNotNull(pages);
    }

    @Test
    public void testPage() throws Exception {
        final File file = new File(Utils.getFileResource("Archive.zip"));
        final List<String> pages = this.reader.pages(file);
        for (final String page : pages) {
            final byte[] page1 = this.reader.page(file, page);
            assertNotNull(page1);
        }
        final byte[] page2 = this.reader.page(file, "IDoNotExistFile.jpeg");
        assertNull(page2);
    }
}