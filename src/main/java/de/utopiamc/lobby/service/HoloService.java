package de.utopiamc.lobby.service;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.lobby.creator.HoloCreator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@Service
public class HoloService {

    private final HashMap<UUID, HoloCreator> holoCreators = new HashMap<>();

    public void addHoloCreator(HoloCreator holoCreator) {
        holoCreator.spawn();
        holoCreator.bind(holoCreators);
    }

    @Subscribe(event = PlayerInteractAtEntityEvent.class)
    public void onEntityInteract(PlayerInteractAtEntityEvent event) {
        HoloCreator holoCreator;
        if ((holoCreator = holoCreators.get(event.getRightClicked().getUniqueId())) != null) {
            holoCreator.getOnClick().accept(event.getPlayer());
        }
    }

    private final String PREFIX = "§c§lLobby §8» §7";

    public void spawnHolos(){

        addHoloCreator(HoloCreator.builder()
                .world("world")
                .x(67.5)
                .y(10.3)
                .z(107.5)
                .lines(List.of("§8----------",
                        "§2Internet",
                        "§8----------"))
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(PREFIX + "§m§8---=---§9World§bWide§9Web§8§m---=---");
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§7Forum §8(§9§nClick§8)", "https://forum.utopiamc.de/index.php"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§7Bewerben §8(§9§nClick§8)", "https://enroll.utopiamc.de/"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§7Utopia Wert (CityBuild) §8(§9§nClick§8)", "https://wert.utopiamc.de/index.php"));
                    player.sendMessage(PREFIX + "§m§8---=---§9World§bWide§9Web§8§m---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .build()
        );


        addHoloCreator(HoloCreator.builder()
                .world("world")
                .x(88.5)
                .y(10.3)
                .z(107.5)
                .lines(List.of("§8----------",
                        "§cYou§fTube",
                        "§8----------"))
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(PREFIX + "§m§8---=---=---§cYou§fTube§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://www.youtube.com/channel/UCGJ7A4T_O8fR6XpLxsJErrA"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://www.youtube.com/channel/UCMXYILQ5PXsxzZEvakAN7lg"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Leitung §8| §7melonenLuca44 §8(§9§nClick§8)", "https://www.youtube.com/channel/UCMXYILQ5PXsxzZEvakAN7lg"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Manager §8| §7DevOskar §8(§9§nClick§8)", "https://youtube.com/channel/UCCKGKuCG9k36lX1e_cI_VpQ"));
                    player.sendMessage(PREFIX + "§m§8---=---=---§cYou§fTube§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .build()
        );


        addHoloCreator(HoloCreator.builder()
                .world("world")
                .x(90.5)
                .y(11.3)
                .z(94.5)
                .lines(List.of("§8----------",
                        "§eIn§csta§5gra§dm",
                        "§8----------"))
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(PREFIX + "§m§8---=---=---§eIn§csta§5gra§dm§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://www.instagram.com/utopiamc.de/"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://www.instagram.com/leon_lp9/"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Leitung §8| §7melonenLuca44 §8(§9§nClick§8)", "https://www.instagram.com/melonenluca44/"));
                    player.sendMessage(PREFIX + "§m§8---=---=---§eIn§csta§5gra§dm§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .build()
        );


        addHoloCreator(HoloCreator.builder()
                .world("world")
                .x(70.5)
                .y(11.3)
                .z(93.5)
                .lines(List.of("§8----------",
                        "§bTwitter",
                        "§8----------"))
                .onClick(player -> {

                    player.sendMessage("");
                    player.sendMessage(PREFIX + "§m§8---=---=---§bTwitter§8§m---=---=---");
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Network §8| §2§lUtopiamc.de §8(§9§nClick§8)", "https://twitter.com/UtopiaNetzwerk"));
                    player.sendMessage(textComponentOPEN_URL(PREFIX + "§4Owner §8| §7Leon_lp9 §8(§9§nClick§8)", "https://twitter.com/leon_lp9"));
                    player.sendMessage(PREFIX + "§m§8---=---=---§bTwitter§8§m---=---=---");
                    player.sendMessage("");
                    player.playSound(player.getLocation(), Sound.BLOCK_SAND_BREAK,1,1);

                })
                .build()
        );

    }

    public TextComponent textComponentOPEN_URL(String text, String url) {
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return message;
    }

}
