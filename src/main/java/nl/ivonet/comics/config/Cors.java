package nl.ivonet.comics.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Allows Access to calls from another origin.
 */
@Provider
public class Cors implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext response) {
        response.getHeaders()
                .putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders()
                .putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, HEAD");
        response.getHeaders()
                .putSingle("Access-Control-Allow-Credentials", "true");
        response.getHeaders()
                .putSingle("Access-Control-Allow-Headers",
                           "Content-Type, Accept, X-Requested-With, Origin, authorization");
        response.getHeaders()
                .putSingle("Access-Control-Max-Age", "1209600");
    }
}
