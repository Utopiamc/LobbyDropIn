package de.utopiamc.lobby.events;

import com.google.inject.Inject;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.tasks.TaskService;
import de.utopiamc.lobby.creator.ItemBuilder;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

@Controller
public class JoinQuitEvents {

    private final TaskService taskService;

    @Inject
    public JoinQuitEvents(TaskService taskService) {
        this.taskService = taskService;
    }

    @Subscribe(event = PlayerJoinEvent.class)
    public void onJoin(@Event PlayerJoinEvent event) {
        event.setJoinMessage(null);

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

        setUpStartInv(event.getPlayer());

        event.getPlayer().sendTitle("§7Hallo §6" + event.getPlayer().getName(), "§7Willkommen auf §6Utopiamc.de", 0,40,30);
        taskService.runSync(() -> {
            event.getPlayer().sendTitle("", "§7" + Bukkit.getServerName(), 20,8,5);
        }, 70);

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

    public static void setUpStartInv(Player player){
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.setAllowFlight(false);
        player.setFlying(false);

        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setDisplayName(HotbarEvents.NAVIGATOR).build());

        ItemStack profile = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta profileSkull = (SkullMeta)profile.getItemMeta();
        profileSkull.setOwner(player.getName());
        profileSkull.setDisplayName(HotbarEvents.PROFILE);
        profile.setItemMeta(profileSkull);

        player.getInventory().setItem(1, profile);
        player.getInventory().setItem(4, new ItemBuilder(Material.INK_SACK, 1, (byte)10).setDisplayName(HotbarEvents.PLAYER_HIDER).build());
        player.getInventory().setItem(7, new ItemBuilder(Material.CLAY_BALL).setDisplayName(HotbarEvents.PARTICLE).build());
        player.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setDisplayName(HotbarEvents.LOBBY_SELECTOR).build());
    }

}
