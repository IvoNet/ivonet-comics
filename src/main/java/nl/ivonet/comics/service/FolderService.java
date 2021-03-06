package nl.ivonet.comics.service;

import nl.ivonet.comics.boundary.Data;
import nl.ivonet.comics.config.Property;
import nl.ivonet.comics.directory.Directory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Ivo Woltring
 */
@Path("/folders")
public class FolderService {
    @Context UriInfo uriInfo;
    @Inject private Directory directory;
    @Inject @Property private String rootFolder;

    private Data retrieveData(final String folder) {
        final Data data = new Data(this.directory.folder(folder));
        data.setBaseUri(this.uriInfo.getBaseUriBuilder()
                                    .path(this.getClass())
                                    .build()
                                    .toString()
                                    .replace("http:", "https:"));
        data.setBrowseUri(this.uriInfo.getBaseUriBuilder()
                                      .path(this.getClass())
                                      .path("/")
                                      .build()
                                      .toString()
                                      .replace("http:", "https:"));
        data.setFileUri(this.uriInfo.getBaseUriBuilder()
                                    .path(ComicService.class)
                                    .build()
                                    .toString()
                                    .replace("http:", "https:"));
        data.setDownloadUri(this.uriInfo.getBaseUriBuilder()
                                        .path(DownloadService.class)
                                        .build()
                                        .toString()
                                        .replace("http:", "https:"));
        return data;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Data root() {
        return retrieveData("");
    }


    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{folder: .+}")
    public Data folder(@PathParam("folder") final String folder) {
        return retrieveData(folder);
    }


}



