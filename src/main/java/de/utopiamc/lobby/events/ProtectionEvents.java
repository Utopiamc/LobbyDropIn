package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

@Controller
public class ProtectionEvents {

    @Subscribe(event = PlayerInteractEvent.class)
    public void onBlockBreak(@Event PlayerInteractEvent event) {
        event.setCancelled(true);
    } // This will cancel all block breaks

    @Subscribe(event = PlayerArmorStandManipulateEvent.class)
    public void onArmorStandManipulate(@Event PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    } // This will cancel all armor stand manipulations

    @Subscribe(event = PlayerDropItemEvent.class)
    public void onItemDrop(@Event PlayerDropItemEvent event) {
        event.setCancelled(true);
    } // This will cancel all item drops

    @Subscribe(event = EntityPickupItemEvent.class)
    public void onItemPickup(@Event EntityPickupItemEvent event) {
        event.setCancelled(true);
    } // This will cancel all item pickups

    @Subscribe(event = EntityInteractEvent.class)
    public void onEntityInteract(@Event EntityInteractEvent event) {
        event.setCancelled(true);
    } // This will cancel all entity interactions

    @Subscribe(event = BlockExplodeEvent.class)
    public void onBlockExplode(@Event BlockExplodeEvent event) {
        event.setCancelled(true);
    } // This will cancel all block explosions

    @Subscribe(event = EntityDamageEvent.class)
    public void onEntityDamage(@Event EntityDamageEvent event) {
        event.setCancelled(true);
    } // This will cancel all entity damage

}
