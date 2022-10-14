package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

@Controller
@Command(value = "IngameShop")
public class IngameShopCommand {

    @MapRoute("")
    public void ingameshop() {

    }

    @Subscribe(event = PlayerInteractEvent.class)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
            event.getPlayer().sendMessage("§7[§6IngameShop§7] §eHallo, ich bin der IngameShop. Ich bin hier, um dir Ränge und Kisten zu verkaufen!");
            event.getPlayer().performCommand("IngameShop");
        }
    }

}
