package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.event.player.PlayerInteractEvent;

@Controller
public class HotbarEvents {
    public static String NAVIGATOR = "§a§lNavigator §7(Rechts Klick)";
    public static String PROFILE = "§a§lProfil §7(Rechts Klick)";
    public static String PLAYER_HIDER = "§7§lSpieler: §aSichtbar §7(Rechts Klick)";
    public static String PARTICLE = "§a§lPartikel / Gadgets §7(Rechts Klick)";
    public static String LOBBY_SELECTOR = "§a§lLobby Selector §7(Rechts Klick)";

    @Subscribe(event = PlayerInteractEvent.class)
    public void onInteract(@Event PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals(HotbarEvents.PROFILE)) {
            event.getPlayer().performCommand("profil");
        }else if (event.getItem().getItemMeta().getDisplayName().equals(HotbarEvents.PLAYER_HIDER)) {
            event.getPlayer().performCommand("toggleplayerhide");
        }else if (event.getItem().getItemMeta().getDisplayName().equals(HotbarEvents.PARTICLE)) {
            event.getPlayer().performCommand("particlegadgets");
        }else if (event.getItem().getItemMeta().getDisplayName().equals(HotbarEvents.LOBBY_SELECTOR)) {
            event.getPlayer().performCommand("lobbyselector");
        }else if (event.getItem().getItemMeta().getDisplayName().equals(HotbarEvents.NAVIGATOR)) {
            event.getPlayer().performCommand("warp");
        }
    }
}
