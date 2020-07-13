package nl.ivonet.comics.archive;

import java.io.File;
import java.util.List;

/**
 * @author Ivo Woltring
 */
public interface ArchiveReader {
    List<String> pages(File file);

    byte[] page(File file, String page);
}
