package nl.ivonet.comics.boundary;

/**
 * @author Ivo Woltring
 */
public class Page {
    private final String page;
    private final String data;

    public Page(final String page, final String data) {
        this.page = page;
        this.data = data;
    }

    public String getPage() {
        return this.page;
    }

    public String getData() {
        return this.data;
    }
}
