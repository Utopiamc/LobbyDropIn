package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;

@Controller
public class BoostEvents {

    @Subscribe(event = PlayerMoveEvent.class)
    public void sui(@Event PlayerMoveEvent event) {
        if (event.getTo().getBlock().getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(2).setY(0.5));
        }
    }//Player will be pushed away from the pressureplate

}
