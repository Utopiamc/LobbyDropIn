package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Controller
public class JoinQuitEvents {

    @Subscribe(event = PlayerJoinEvent.class)
    public void onJoin(@Event PlayerJoinEvent event) {
        event.setJoinMessage(null);

        event.getPlayer().setGameMode(GameMode.ADVENTURE);

        //Oskar Teleport

        event.getPlayer().sendMessage("§2§m-----------------------------------------------");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage(centerText("§a§lUtopia§f§lMC"));
        event.getPlayer().sendMessage(centerText("§7§oWähle einen Server mit dem §a§lNavigator§7§o!"));
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage(centerText("§2§lBixby §7§o- §fHilfe bei Fragen"));
        event.getPlayer().sendMessage(centerText("§a§lFirion §7§o- §fTägliche Belohnungen"));
        event.getPlayer().sendMessage(centerText("§7§lDiscord §7§o- §fdiscord.gg/WAymjhhX8Q"));
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("§2§m-----------------------------------------------");

    } // Join handler

    @Subscribe(event = PlayerQuitEvent.class)
    public void onQuit(@Event PlayerQuitEvent event) {
        event.setQuitMessage(null);
    } // This will cancel all quit messages

    private String centerText(String text) {
        int maxWidth = 68,
                spaces = (int) Math.round((maxWidth-1.4* ChatColor.stripColor(text).length())/2);
        return StringUtils.repeat(" ", spaces)+text;
    }

}
