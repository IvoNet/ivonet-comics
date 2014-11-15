package nl.ivonet.comics.service;

import nl.ivonet.cdi_properties.Property;
import nl.ivonet.comics.boundary.Comic;
import nl.ivonet.comics.boundary.Page;
import nl.ivonet.comics.boundary.Skeleton;
import nl.ivonet.comics.cbr.CbrReader;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Ivo Woltring
 */
@Path("/comic")
public class ComicService {

    private static final String COMIC = ComicService.class.getName() + ".comic";
    @Inject private CbrReader cbrReader;
    @Inject @Property private String rootFolder;

    @GET
    @Path("/{file: .+[cc][bB][rR]}")
    @Produces(APPLICATION_JSON)
    public Skeleton fetch(@Context final UriInfo uriInfo, @Context final HttpServletRequest request,
                          @PathParam("file") final String filename) {
        final HttpSession session = request.getSession(true);
        final URI build = uriInfo.getAbsolutePath();

        return new Skeleton(build, retrieveComic(session, filename));
    }

    @GET
    @Path("/{file: .+[cc][bB][rR]}/{page: .+[jJ][pP][gG]}")
    @Produces(APPLICATION_JSON)
    public Page page(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                     @PathParam("page") final String page) {
        final HttpSession session = request.getSession(true);
        final Comic comic = retrieveComic(session, filename);

        return comic.page(page);
    }


    private Comic retrieveComic(final HttpSession session, final String filename) {
        final Object item = session.getAttribute(COMIC);
        if (item != null) {
            final Comic comic = (Comic) item;
            if (("/" + filename).equals(comic.getFilename())) {
                return comic;
            }
        }
        final File file = Paths.get(this.rootFolder, filename)
                               .toFile();
        final Comic comic = this.cbrReader.read(file);
        session.setAttribute(COMIC, comic);
        return comic;
    }

}
