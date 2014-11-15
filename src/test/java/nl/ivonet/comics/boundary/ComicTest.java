package nl.ivonet.comics.boundary;

import nl.ivonet.helper.boundary.Resource;
import org.junit.Before;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ComicTest {

    private Comic.Builder builder;

    @Before
    public void setUp() throws Exception {
        this.builder = new Comic.Builder("/home/foo/comics/");

    }

    @Test
    public void testGetFilename() throws Exception {
        this.builder.filename("test");
        final Comic comic = this.builder.build();
        assertTrue(comic.getPages()
                        .isEmpty());
        assertThat(comic.getFilename(), is("test"));

    }

    @Test
    public void testGetFilenameWhereRootFolderIsRemoved() throws Exception {
        this.builder.filename("/home/foo/comics/test");
        final Comic comic = this.builder.build();
        assertTrue(comic.getPages()
                        .isEmpty());
        assertThat(comic.getFilename(), is("test"));

    }

    @Test
    public void testGetPages() throws Exception {
        this.builder.filename("/home/foo/comics/test");
        final TreeMap<String, Resource> stringResourceTreeMap = new TreeMap<>();
        final Resource resource = new Resource("img000.jpg");
        resource.setData("Hello World".getBytes());
        stringResourceTreeMap.put("img000.jpg", resource);
        this.builder.pages(stringResourceTreeMap);
        final Comic comic = this.builder.build();
        assertFalse(comic.getPages()
                         .isEmpty());
        assertThat(comic.getFilename(), is("test"));
        final Page cover = comic.getPages()
                                .get("img000.jpg");
        assertFalse(cover.getData()
                         .isEmpty());
        assertThat(new String(new BASE64Decoder().decodeBuffer(cover.getData())), is("Hello World"));

    }
}