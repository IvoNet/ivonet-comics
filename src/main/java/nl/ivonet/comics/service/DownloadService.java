package nl.ivonet.comics.service;

import nl.ivonet.comics.config.Property;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;

/**
 * @author Ivo Woltring
 */
@Path(DownloadService.DOWNLOAD)
public class DownloadService {

    public static final String APPLICATION_X_CBR = "application/x-cbr";
    public static final String DOWNLOAD = "/download";

    @Inject
    @Property
    private String rootFolder;


    @GET
    @Path("/{file: .+}")
    @Produces(APPLICATION_X_CBR)
    public Response download(@PathParam("file") final String filename) {
        return Response.ok(Paths.get(this.rootFolder, filename)
                                .toFile(), APPLICATION_X_CBR)
                       .build();
    }
}
