package nl.ivonet.comics.cbr;

import nl.ivonet.cdi_properties.Property;
import nl.ivonet.comics.boundary.Comic;
import nl.ivonet.comics.boundary.Comic.Builder;
import nl.ivonet.helper.ArchiveToMemory;
import nl.ivonet.helper.boundary.Memory;

import javax.inject.Inject;
import java.io.File;

/**
 * @author Ivo Woltring
 */
public class CbrReader {

    @Inject private ArchiveToMemory rar;

    @Inject @Property private String rootFolder;


    public Comic read(final File file) {
        final Memory rar = this.rar.extract(file);
        final Builder builder = new Builder(this.rootFolder);
        builder.filename(rar.getFileName())
               .pages(rar.getResources());
        return builder.build();
    }


}
