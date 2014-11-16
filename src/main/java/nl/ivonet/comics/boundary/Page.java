package nl.ivonet.comics.boundary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ivo Woltring
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Page {
    private final String url;
    private String caption;
    private String thumbUrl;

    public Page(final String url) {
        this.url = url;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }

    public void setThumbUrl(final String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getUrl() {
        return this.url;
    }
}
