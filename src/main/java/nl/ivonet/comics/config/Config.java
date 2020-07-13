package nl.ivonet.comics.config;

import nl.ivonet.helper.ArchiveToMemory;

import javax.enterprise.inject.Produces;

/**
 * @author Ivo Woltring
 */
public class Config {

    @Produces
    public ArchiveToMemory rar() {
        return new ArchiveToMemory();
    }


}
