package de.utopiamc.lobby;

import com.google.inject.Inject;
import de.utopiamc.framework.api.dropin.annotations.OnDisable;
import de.utopiamc.framework.api.dropin.annotations.OnEnable;
import de.utopiamc.framework.api.event.FrameworkEvent;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.stereotype.Plugin;
import de.utopiamc.lobby.creator.EntityCreator;
import de.utopiamc.lobby.service.HoloService;
import org.bukkit.entity.EntityType;

import java.util.logging.Logger;

@Plugin
public class Lobby {

    private final Logger logger;
    private final HoloService holoService;

    @Inject
    public Lobby(Logger logger, HoloService holoService) {
        this.logger = logger;
        this.holoService = holoService;
    }

    @OnEnable
    public void onEnable() {
        logger.info("Lobby enabled!");
        spawnEntities();

        holoService.spawnHolos();
    }

    @OnDisable
    public void onDisable() {
        logger.info("Lobby disabled!");
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

        new EntityCreator("world", 77.5, 12.1, 104.5, 0, 0, EntityType.ARMOR_STAND, "§7⛃§6Ingame Shop§7⛃", "IngameShop")
                .setCollidable(false)
                .setVisible(false)
                .setGravity(false)
                .setClickArmorStand();
    }

    @Subscribe(event = FrameworkEvent.class)
    public void onFrameworkEvent(FrameworkEvent event) {

    }
}
