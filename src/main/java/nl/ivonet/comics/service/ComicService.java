package nl.ivonet.comics.service;

import nl.ivonet.cdi_properties.Property;
import nl.ivonet.comics.boundary.Page;
import nl.ivonet.comics.cbr.CbrReader;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Ivo Woltring
 */
@Path("/comic")
public class ComicService {

    @Inject private CbrReader cbrReader;
    @Inject @Property private String rootFolder;

    @GET
    @Path("/{file: .+[cc][bB][rR]}")
    @Produces(APPLICATION_JSON)
    public List<Page> fetch(@Context final UriInfo uriInfo, @Context final HttpServletRequest request,
                              @PathParam("file") final String filename) {
        final List<String> uris = new ArrayList<>();
        final List<String> pages = this.cbrReader.pages(buildFile(filename));
        for (final String page : pages) {
            final String uri = uriInfo.getAbsolutePathBuilder()
                                      .path(page)
                                      .build()
                                      .toString();
            uris.add(uri);
        }
        Collections.sort(uris);
        final List<Page> collect = uris.stream()
                                       .map(p -> new Page(p))
                                       .collect(Collectors.toList());
        return collect;
    }

    @GET
    @Path("/{file: .+[cc][bB][rR]}/{page: .+[jJ][pP][gG]}")
    @Produces("image/jpeg")
    public Response page(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                         @PathParam("page") final String page) {

        return Response.ok(this.cbrReader.page(buildFile(filename), page), "image/jpeg")
                       .build();
    }

    private File buildFile(final String filename) {
        return Paths.get(this.rootFolder, filename)
                    .toFile();
    }

//    private Comic retrieveComic(final HttpSession session, final String filename) {
//        final Object item = session.getAttribute(COMIC);
//        if (item != null) {
//            final Comic comic = (Comic) item;
//            if (("/" + filename).equals(comic.getFilename())) {
//                return comic;
//            }
//        }
//        final File file = Paths.get(this.rootFolder, filename)
//                               .toFile();
//        final Comic comic = this.cbrReader.read(file);
//        session.setAttribute(COMIC, comic);
//        return comic;
//    }

}
