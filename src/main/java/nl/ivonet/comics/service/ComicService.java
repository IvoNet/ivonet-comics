package nl.ivonet.comics.service;

import nl.ivonet.comics.archive.ArchiveReader;
import nl.ivonet.comics.archive.CbrArchiveReader;
import nl.ivonet.comics.archive.CbzArchiveReader;
import nl.ivonet.comics.boundary.Page;
import nl.ivonet.comics.config.Property;

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
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Ivo Woltring
 */
@Path("/comic")
public class ComicService {

    @Inject
    private CbrArchiveReader cbrReader;
    @Inject
    private CbzArchiveReader cbzReader;
    @Inject
    @Property
    private String rootFolder;

    @GET
    @Path("/{file: .+[cc][bB][rR]}")
    @Produces(APPLICATION_JSON)
    public List<Page> fetchCbr(@Context final UriInfo uriInfo, @Context final HttpServletRequest request,
                               @PathParam("file") final String filename) {
        return getPages(this.cbrReader, uriInfo, filename);
    }

    @GET
    @Path("/{file: .+[cc][bB][zZ]}")
    @Produces(APPLICATION_JSON)
    public List<Page> fetchCbz(@Context final UriInfo uriInfo, @Context final HttpServletRequest request,
                               @PathParam("file") final String filename) {
        return getPages(this.cbzReader, uriInfo, filename);
    }

    @GET
    @Path("/{file: .+[cc][bB][rR]}/{page: .+[jJ][pP][gG]}") //TODO [eE]?
    @Produces("image/jpeg")
    public Response cbrPage(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                            @PathParam("page") final String page) {
//TODO What about the error siuations?
        return Response.ok(this.cbrReader.page(buildFile(filename), page), "image/jpeg")
                       .build();
    }

    @GET
    @Path("/{file: .+[cc][bB][rR]}/{page: .+[pP][nN][gG]}")
    @Produces("image/png")
    public Response cbrPagePng(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                               @PathParam("page") final String page) {
//TODO What about the error siuations?
        return Response.ok(this.cbrReader.page(buildFile(filename), page), "image/png")
                       .build();
    }

    @GET
    @Path("/{file: .+[cc][bB][zZ]}/{page: .+[jJ][pP][gG]}") //TODO [eE]?
    @Produces("image/jpeg")
    public Response cbzPage(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                            @PathParam("page") final String page) {
//TODO What about the error siuations?
        return Response.ok(this.cbzReader.page(buildFile(filename), page), "image/jpeg")
                       .build();
    }

    @GET
    @Path("/{file: .+[cc][bB][zZ]}/{page: .+[pP][nN][gG]}")
    @Produces("image/png")
    public Response cbzPagePng(@Context final HttpServletRequest request, @PathParam("file") final String filename,
                               @PathParam("page") final String page) {
//TODO What about the error siuations?
        return Response.ok(this.cbzReader.page(buildFile(filename), page), "image/png")
                       .build();
    }

    private File buildFile(final String filename) {
        return Paths.get(this.rootFolder, filename)
                    .toFile();
    }

    private List<Page> getPages(final ArchiveReader reader, final UriInfo uriInfo, final String filename) {
        final List<String> pages = reader.pages(buildFile(filename));
        return pages.stream()
                    .map(p -> uriInfo.getAbsolutePathBuilder()
                                     .path(p)
                                     .build()
                                     .toString())
                    .map(Page::new)
                    .collect(Collectors.toList());
    }

}
