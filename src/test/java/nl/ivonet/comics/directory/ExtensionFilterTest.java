package nl.ivonet.comics.directory;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static nl.ivonet.test.Utils.injectField;
import static org.junit.Assert.assertTrue;


public class ExtensionFilterTest {

    private ExtensionFilter filter;

    @Before
    public void setUp() throws Exception {
        this.filter = new ExtensionFilter();
        injectField(this.filter, "filterExtensions", ".cbz:.cbr");
    }

    @Test
    public void testCbr() throws Exception {
        final Path entry = Paths.get("src/test/resources/comic.cbr");
        assertTrue(this.filter.accept(entry.toAbsolutePath()));
    }

    @Test
    public void testCbz() throws Exception {
        final Path entry = Paths.get("src/test/resources/comic.cbz");
        assertTrue(this.filter.accept(entry.toAbsolutePath()));
    }
}