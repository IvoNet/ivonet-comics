package nl.ivonet.comics.cbr;

import nl.ivonet.cdi_properties.Property;
import nl.ivonet.helper.ArchiveToMemory;
import nl.ivonet.helper.boundary.Resource;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

/**
 * @author Ivo Woltring
 */
public class CbrReader {

    @Inject private ArchiveToMemory rar;

    @Inject @Property private String rootFolder;


//    public Comic read(final File file) {
//        final Memory rar = this.rar.extract(file);
//        final Builder builder = new Builder(this.rootFolder);
//        builder.filename(rar.getFileName())
//               .pages(rar.getResources());
//        return builder.build();
//    }

    public List<String> pages(final File file) {
        return this.rar.files(file);
    }


    public byte[] page(final File file, final String page) {
        final Resource resource = this.rar.extract(file, page);
        return resource != null ? resource.getData() : null;
    }

}
