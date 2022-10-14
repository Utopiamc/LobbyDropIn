package de.utopiamc.lobby.commands;

import com.google.inject.Inject;
import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.service.MessageService;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.tasks.TaskService;
import de.utopiamc.lobby.ingameshop.IngameShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

@Controller
@Command(value = "IngameShop")
public class IngameShopCommand {

    private final TaskService taskService;

    @Inject
    public IngameShopCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @MapRoute("")
    public void ingameshop(Player player, MessageService messageService) {
        if (player.isSneaking()) messageService.sendMessage("Du kannst den IngameShop nur offnen, wenn du nicht Sneakst!");
        else new IngameShop(player);
    }

    @Subscribe(event = PlayerInteractEvent.class)
    public void onInteract(@Event PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
            event.getPlayer().sendMessage("§7[§6IngameShop§7] §eHallo, ich bin der IngameShop. Ich bin hier, um dir Ränge und Kisten zu verkaufen!");
            event.getPlayer().performCommand("IngameShop");
        }
    }

    @Subscribe(event = PlayerQuitEvent.class)
    public void onQuit(@Event PlayerQuitEvent event){
        if(IngameShop.ingameshop.containsKey(event.getPlayer())){
            IngameShop.ingameshop.get(event.getPlayer()).as.remove();
            IngameShop.ingameshop.remove(event.getPlayer());
        }
    }

    @Subscribe(event = EntityDismountEvent.class)
    public void onSneak(@Event EntityDismountEvent event){
        event.getEntity().sendMessage("Debug1");
        if (event.getEntity() instanceof Player) {
            event.getEntity().sendMessage("Debug");
            if (IngameShop.ingameshop.containsKey((Player) event.getEntity())) {
                IngameShop.ingameshop.get((Player) event.getEntity()).remove();
            }
        }
    }

}
