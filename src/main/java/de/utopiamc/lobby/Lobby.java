package de.utopiamc.lobby;

import com.google.inject.Inject;
import de.utopiamc.framework.api.dropin.annotations.OnDisable;
import de.utopiamc.framework.api.dropin.annotations.OnEnable;
import de.utopiamc.framework.api.stereotype.Plugin;
import de.utopiamc.lobby.creator.EntityCreator;
import de.utopiamc.lobby.creator.HoloCreator;
import org.bukkit.entity.EntityType;

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
        spawnEntities();
        spawnHolos();
    }

    @OnDisable
    public void onDisable() {
        logger.info("Lobby disabled!");
    }

    public void spawnHolos(){
        new HoloCreator("world", 67.5, 10.3, 107.5)
                .addLine("§8----------")
                .addLine("§2Internet")
                .addLine("§8----------")
                .spawn();

        new HoloCreator("world", 88.5, 10.3, 107.5)
                .addLine("§8----------")
                .addLine("§cYou§fTube")
                .addLine("§8----------")
                .spawn();

        new HoloCreator("world", 90.5, 11.3, 94.5)
                .addLine("§8----------")
                .addLine("§eIn§csta§5gra§dm")
                .addLine("§8----------")
                .spawn();

        new HoloCreator("world", 70.5, 11.3, 93.5)
                .addLine("§8----------")
                .addLine("§bTwitter")
                .addLine("§8----------")
                .spawn();

    }

    public void spawnEntities(){
        new EntityCreator("world", 61.1, 11, 108.1, -130, 0, EntityType.IRON_GOLEM, "§3§lDonpireso", "Donpireso")
                .setCollidable(false)
                .setAI(false)
                .setSilent(true)
                .setInvulnerable(true)
                .setClickArmorStand();

        new EntityCreator("world", 59.5, 13, 90.0, -90, 0, EntityType.VILLAGER, "§3§lFirion", "Firion")
                .setCollidable(false)
                .setAI(false)
                .setSilent(true)
                .setInvulnerable(true)
                .setClickArmorStand();

        new EntityCreator("world", 73.5, 13, 79.7, 0, 0, EntityType.WITCH, "§3§lAufgaben", "Aufgaben")
                .setCollidable(false)
                .setAI(false)
                .setSilent(true)
                .setInvulnerable(true)
                .setClickArmorStand();

        new EntityCreator("world", 87.5, 13.2, 99.5, -132, 0, EntityType.IRON_GOLEM, "§9§lBixby", "Bixby")
                .setCollidable(true)
                .setAI(true)
                .setSilent(false)
                .setInvulnerable(true);

        new EntityCreator("world", 73.5, 13, 89.5, -45, 0, EntityType.VILLAGER, "§6» §7Privatgames", "Privatgames")
                .setCollidable(false)
                .setAI(false)
                .setSilent(true)
                .setInvulnerable(true)
                .setClickArmorStand();
    }
}
