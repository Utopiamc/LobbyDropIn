package de.utopiamc.lobby.interact;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.lobby.creator.EntityCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

@Controller
public class EntityInteract {

    @Subscribe(event = PlayerInteractEntityEvent.class)
    public void onEntityInteract(@Event PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            String identifier = EntityCreator.getIdentifier(event.getRightClicked());
            if (identifier != null) {
                event.setCancelled(true);
                event.getPlayer().performCommand(identifier);
            }
        }
    } // This will cancel all entity interactions and execute the command of the entity


    @Subscribe(event = EntityDamageByEntityEvent.class)
    public void onEntityDamageByEntity(@Event EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            String identifier = EntityCreator.getIdentifier(event.getEntity());
            if (identifier != null) {
                event.setCancelled(true);
                ((Player)event.getDamager()).performCommand(identifier);
            }
        }
    } // This will cancel all entity interactions and execute the command of the entity


}
