package nl.ivonet.comics.boundary;

import nl.ivonet.comics.io.ImageBase64;
import nl.ivonet.helper.boundary.Resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ivo Woltring
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Comic implements Serializable {
    private final String filename;

    @XmlTransient private final Map<String, Page> pages;

    private Page firstPage;

    /**
     * Private constructor for the builder.
     *
     * @param builder the {@link Builder} creating this object.
     */
    private Comic(final Builder builder) {
        this.filename = builder.filename;
        this.pages = Collections.unmodifiableMap(builder.pages);
        final Iterator<String> iterator = this.pages.keySet()
                                                    .iterator();

        if (iterator.hasNext()) {
            this.firstPage = this.pages.get(iterator.next());
        }
    }

    public String getFilename() {
        return this.filename;
    }

    public Map<String, Page> getPages() {
        return this.pages;
    }

    public Page page(final String page) {
        return this.pages.get(page);
    }

    public Page getFirstPage() {
        return this.firstPage;
    }

    /**
     * The Builder for {@link Comic}.
     */
    public static class Builder {
        private final String rootFolder;
        private final ImageBase64 base64;
        private final Map<String, Page> pages;
        private String filename;

        /**
         * Constructor of the {@link nl.ivonet.comics.boundary.Comic} class.
         */
        public Builder(final String rootFolder) {
            this.rootFolder = rootFolder;
            this.base64 = new ImageBase64();
            this.pages = new TreeMap<>();
        }


        public final Builder filename(final String filename) {
            if (filename != null) {
                this.filename = filename.startsWith(this.rootFolder) ? filename.substring(this.rootFolder.length()) :
                                filename;
            }
            return this;
        }

        public final Builder pages(final Map<String, Resource> pages) {
            for (final String page : pages.keySet()) {
                final Resource resource = pages.get(page);
                final byte[] data = resource.getData();
                final String value = this.base64.encodeToString(data);
                this.pages.put(page, new Page(page, value));
            }
            return this;
        }


        /**
         * Builds the {@link nl.ivonet.comics.boundary.Comic} class. Should be called when finished building and the
         * final product may be created. After calling this method the resulting object will be immutable.
         *
         * @return {@link nl.ivonet.comics.boundary.Comic} instance.
         */
        public final Comic build() {
            return new Comic(this);
        }
    }
}
