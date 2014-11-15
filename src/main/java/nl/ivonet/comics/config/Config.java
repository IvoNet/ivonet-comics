package nl.ivonet.comics.config;

import nl.ivonet.helper.ArchiveToMemory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.enterprise.inject.Produces;

/**
 * @author Ivo Woltring
 */
public class Config {

    @Produces
    public BASE64Decoder decoder() {
        return new BASE64Decoder();
    }

    @Produces
    public BASE64Encoder encoder() {
        return new BASE64Encoder();
    }


    @Produces
    public ArchiveToMemory rar() {
        return new ArchiveToMemory();
    }


}
