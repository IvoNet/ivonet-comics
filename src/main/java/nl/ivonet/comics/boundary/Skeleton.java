package nl.ivonet.comics.boundary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ivo Woltring
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Skeleton {
    private final List<String> pages;
    private final Page cover;

    public Skeleton(final URI downloadUri, final Comic comic) {
        final Set<String> pageItems = comic.getPages()
                                           .keySet();

        final String url = endslash(downloadUri.toString());

        this.pages = pageItems.stream()
                              .map(p -> url + p)
                              .sorted()
                              .collect(Collectors.toList());


        this.cover = comic.getFirstPage();
    }

    private String endslash(final String s) {
        return s.endsWith("/") ? s : s + "/";
    }


    public List<String> getPages() {
        return this.pages;
    }

    public Page getCover() {
        return this.cover;
    }
}
