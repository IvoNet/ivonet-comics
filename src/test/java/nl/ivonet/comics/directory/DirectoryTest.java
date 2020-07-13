package nl.ivonet.comics.directory;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static nl.ivonet.test.Utils.injectField;
import static org.junit.Assert.assertEquals;

public class DirectoryTest {

    private Directory directory;

    @Before
    public void setUp() throws Exception {
        this.directory = new Directory();
        injectField(this.directory, "rootFolder", Paths.get("src/test/resources/")
                                                       .toAbsolutePath()
                                                       .toString());
        injectField(this.directory, "directoryFilter", new DirectoryFilter());
        final ExtensionFilter filter = new ExtensionFilter();
        injectField(filter, "filterExtensions", ".cbz:.cbr");
        injectField(this.directory, "extensionFilter", filter);
    }

    @Test
    public void testDirectory() throws Exception {
        assertEquals(1, this.directory.folder("")
                                      .getFolders()
                                      .size());
    }

    @Test
    public void testFiles() throws Exception {
        assertEquals(2, this.directory.folder("")
                                      .getFiles()
                                      .size());
    }
}