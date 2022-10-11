package de.utopiamc.lobby;

import com.google.inject.Inject;
import de.utopiamc.framework.api.dropin.annotations.OnDisable;
import de.utopiamc.framework.api.dropin.annotations.OnEnable;
import de.utopiamc.framework.api.stereotype.Plugin;

import java.util.logging.Logger;

@Plugin
public class Lobby {

    private final Logger logger;

    @Inject
    public Lobby(Logger logger) {
        this.logger = logger;
    }

    @OnEnable
    public void onEnable() {
        logger.info("Lobby enabled!");
    }

    @OnDisable
    public void onDisable() {
        logger.info("Lobby disabled!");
    }

}
