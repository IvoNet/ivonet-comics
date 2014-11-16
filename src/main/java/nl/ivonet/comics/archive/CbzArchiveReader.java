package nl.ivonet.comics.archive;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ivo Woltring
 */
public class CbzArchiveReader implements ArchiveReader {

    public static final int BUFFER = 2048;

    @Override
    public List<String> pages(final File file) {
        try {
            final ZipFile zip = new ZipFile(file);
            return zip.stream()
                      .filter(p -> !p.isDirectory())
                      .filter(p -> !p.getName()
                                     .startsWith("."))
                      .filter(p -> !p.getName()
                                     .startsWith("__MACOSX"))
                      .filter(p -> p.getName()
                                    .toLowerCase()
                                    .endsWith("jpg") || p.getName()
                                                         .toLowerCase()
                                                         .endsWith(".jpeg"))
                      .map(ZipEntry::getName)
                      .collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] page(final File file, final String page) {

        try {
            final ZipFile zip = new ZipFile(file);
            final ZipEntry zipEntry = zip.stream()
                                         .filter(p -> p.getName()
                                                       .equals(page))
                                         .findAny()
                                         .orElse(null);
            if (zipEntry != null) {
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                final InputStream inputStream = zip.getInputStream(zipEntry);
                final byte[] buffer = new byte[BUFFER];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.close();
                return outputStream.toByteArray();
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
