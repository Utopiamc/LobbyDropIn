package de.utopiamc.lobby;

import com.google.inject.Inject;
import de.utopiamc.framework.api.dropin.annotations.OnDisable;
import de.utopiamc.framework.api.dropin.annotations.OnEnable;
import de.utopiamc.framework.api.stereotype.Plugin;
import de.utopiamc.lobby.creator.EntityCreator;
import de.utopiamc.lobby.creator.HoloCreator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
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

    private String prefix = "§a§lLobby §8» §7";

    public void spawnHolos(){
        new HoloCreator("world", 67.5, 10.3, 107.5)
                .addLine("§8----------")
                .addLine("§2Internet")
                .addLine("§8----------")
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(prefix + "§m§8---=---§9World§bWide§9Web§8§m---=---");
                    player.sendMessage(textComponentOPEN_URL("§7Forum §8(§9§nClick§8)", "https://forum.utopiamc.de/index.php"));
                    player.sendMessage(textComponentOPEN_URL("§7Bewerben §8(§9§nClick§8)", "https://enroll.utopiamc.de/"));
                    player.sendMessage(textComponentOPEN_URL("§7Utopia Wert (CityBuild) §8(§9§nClick§8)", "https://wert.utopiamc.de/index.php"));
                    player.sendMessage(prefix + "§m§8---=---§9World§bWide§9Web§8§m---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .spawn();

        new HoloCreator("world", 88.5, 10.3, 107.5)
                .addLine("§8----------")
                .addLine("§cYou§fTube")
                .addLine("§8----------")
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(prefix + "§m§8---=---=---§cYou§fTube§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL("§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://www.youtube.com/channel/UCGJ7A4T_O8fR6XpLxsJErrA"));
                    player.sendMessage(textComponentOPEN_URL("§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://www.youtube.com/channel/UCMXYILQ5PXsxzZEvakAN7lg"));
                    player.sendMessage(textComponentOPEN_URL("§4Leitung §8| §7melonenLuca44 §8(§9§nClick§8)", "https://www.youtube.com/channel/UCMXYILQ5PXsxzZEvakAN7lg"));
                    player.sendMessage(textComponentOPEN_URL("§4Manager §8| §7DevOskar §8(§9§nClick§8)", "https://youtube.com/channel/UCCKGKuCG9k36lX1e_cI_VpQ"));
                    player.sendMessage(prefix + "§m§8---=---=---§cYou§fTube§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .spawn();

        new HoloCreator("world", 90.5, 11.3, 94.5)
                .addLine("§8----------")
                .addLine("§eIn§csta§5gra§dm")
                .addLine("§8----------")
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(prefix + "§m§8---=---=---§eIn§csta§5gra§dm§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL("§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://www.instagram.com/utopiamc.de/"));
                    player.sendMessage(textComponentOPEN_URL("§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://www.instagram.com/leon_lp9/"));
                    player.sendMessage(textComponentOPEN_URL("§4Leitung §8| §7melonenLuca44 §8(§9§nClick§8)", "https://www.instagram.com/melonenluca44/"));
                    player.sendMessage(prefix + "§m§8---=---=---§eIn§csta§5gra§dm§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .spawn();

        new HoloCreator("world", 70.5, 11.3, 93.5)
                .addLine("§8----------")
                .addLine("§bTwitter")
                .addLine("§8----------")
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(prefix + "§m§8---=---=---§bTwitter§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL("§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://twitter.com/utopiamc_de"));
                    player.sendMessage(textComponentOPEN_URL("§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://twitter.com/leon_lp9"));
                    player.sendMessage(textComponentOPEN_URL("§4Leitung §8| §7melonenLuca44 §8(§9§nClick§8)", "https://twitter.com/melonenluca44"));
                    player.sendMessage(textComponentOPEN_URL("§4Manager §8| §7DevOskar §8(§9§nClick§8)", "https://twitter.com/DevOskar"));
                    player.sendMessage(prefix + "§m§8---=---=---§bTwitter§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .spawn();

    }

    public TextComponent textComponentOPEN_URL(String text, String url) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return message;
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
}
