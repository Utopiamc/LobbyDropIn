package de.utopiamc.lobby.events;

import de.dytanic.cloudnet.ext.bridge.player.executor.ServerSelectorType;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.lobby.cloudnet.CloudNetSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

@Controller
public class PortalEnter {

    @Subscribe(event = PlayerMoveEvent.class)
    public void onPortalEnter(@Event PlayerMoveEvent event) {
        Player p = event.getPlayer();

        Location locCBpos1 = new Location(Bukkit.getWorld("world"), 82,12,121);
        Location locBWpos1 = new Location(Bukkit.getWorld("world"), 88,12,113);
        Location locSKYpos1 = new Location(Bukkit.getWorld("world"), 72,12,121);
        Location locCHApos1 = new Location(Bukkit.getWorld("world"), 66,12,113);

        if (!event.getFrom().getBlock().getType().equals(Material.NETHER_PORTAL) && event.getTo().getBlock().getType().equals(Material.NETHER_PORTAL)) {

            int dis = 9999;
            String Server = null;
            if (p.getLocation().distance(locBWpos1) < dis) {
                dis = (int) p.getLocation().distance(locBWpos1);
                //CloudNetDriver.getInstance().getCloudServiceProvider().getServicesCountByGroup("BedWars");
                Server = "BedWars";
            }
            if (p.getLocation().distance(locCBpos1) < dis) {
                dis = (int) p.getLocation().distance(locCBpos1);
                Server = "CityBuild";
            }
            if (p.getLocation().distance(locCHApos1) < dis) {
                dis = (int) p.getLocation().distance(locCHApos1);
                Server = "Challenges-HUB";
            }
            if (p.getLocation().distance(locSKYpos1) < dis) {
                dis = (int) p.getLocation().distance(locSKYpos1);
                Server = "SkyBlockHub";
            }

            if (Server != null){
                if (Server.equals("SkyBlockHub")){
                    CloudNetSender.sendUserNextRound(p, Server, ServerSelectorType.LOWEST_PLAYERS);
                }else if (Server.equals("BedWars")){
                    CloudNetSender.sendUserNextRound(p, Server, ServerSelectorType.HIGHEST_PLAYERS);
                }
                else {
                    CloudNetSender.sendUserNextRound(p, Server, ServerSelectorType.HIGHEST_PLAYERS);
                }
            }else {
                p.sendMessage("§cError: Server not found");
            }

        }
    }
}

