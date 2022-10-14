package de.utopiamc.lobby.interact;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.lobby.creator.EntityCreator;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

@Controller
public class EntityInteract {

    @Subscribe(event = PlayerInteractAtEntityEvent.class)
    public void onEntityInteract(@Event PlayerInteractAtEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            String identifier = EntityCreator.getIdentifier(event.getRightClicked());
            if (identifier != null) {
                event.setCancelled(true);
                event.getPlayer().performCommand(identifier);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                event.getPlayer().playSound(event.getRightClicked().getLocation(), "custom." + identifier, 2F, 1);
            }
        }
    } // This will cancel all entity interactions and execute the command of the entity

    @Subscribe(event = PlayerInteractEntityEvent.class)
    public void onEntityInteract(@Event PlayerInteractEntityEvent event) {
        event.setCancelled(true);
    } // This will cancel all entity interactions


    @Subscribe(event = EntityDamageByEntityEvent.class)
    public void onEntityDamageByEntity(@Event EntityDamageByEntityEvent event) {
        event.setCancelled(true);
        if (event.getDamager() instanceof Player) {
            String identifier = EntityCreator.getIdentifier(event.getEntity());
            if (identifier != null) {
                ((Player)event.getDamager()).performCommand(identifier);
                ((Player)event.getDamager()).playSound(event.getDamager().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }

    } // This will cancel all entity interactions and execute the command of the entity

    @Subscribe(event = EntityDamageEvent.class)
    public void onEntityDamage(@Event EntityDamageEvent event) {
        event.setCancelled(true);
    } // This will cancel all entity damage

}
