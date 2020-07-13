package nl.ivonet.comics.boundary;

/**
 * @author Ivo Woltring
 */
public class Metadata {
    private String baseUri;
    private String browseUri;
    private String fileUri;
    private String downloadUri;

    public String getBaseUri() {
        return this.baseUri;
    }

    public void setBaseUri(final String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBrowseUri() {
        return this.browseUri;
    }

    public void setBrowseUri(final String browseUri) {
        this.browseUri = browseUri;
    }

    public String getFileUri() {
        return this.fileUri;
    }

    public void setFileUri(final String fileUri) {
        this.fileUri = fileUri;
    }

    public String getDownloadUri() {
        return this.downloadUri;
    }

    public void setDownloadUri(final String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
