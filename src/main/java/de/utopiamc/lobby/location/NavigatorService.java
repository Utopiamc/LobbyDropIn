package de.utopiamc.lobby.location;

import com.google.inject.Inject;
import de.utopiamc.framework.api.service.FrameworkPlayerService;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.framework.api.tasks.TaskService;
import de.utopiamc.framework.api.ui.inventory.InventoryFactory;
import de.utopiamc.framework.api.ui.inventory.InventoryPresentable;
import de.utopiamc.framework.api.ui.inventory.button.ButtonInventoryBuilder;
import de.utopiamc.framework.api.ui.inventory.button.InventoryButton;
import de.utopiamc.framework.api.ui.inventory.design.InventoryDesigns;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

@Service
public class NavigatorService {
    
    private final InventoryFactory inventoryFactory;
    private final FrameworkPlayerService playerService;
    private final SpawnService spawnService;
    private final TaskService taskService;

    private InventoryPresentable inventoryPresentable;

    @Inject
    public NavigatorService(InventoryFactory inventoryFactory, FrameworkPlayerService playerService, SpawnService spawnService, TaskService taskService) {
        this.inventoryFactory = inventoryFactory;
        this.playerService = playerService;
        this.spawnService = spawnService;
        this.taskService = taskService;
    }

    @Inject
    public void setUpNavigator(){
        ButtonInventoryBuilder navigator = inventoryFactory.createButtonInventory("Navigator").applyDesign(InventoryDesigns.cornerInventoryDesign());
        for (StorageLocation location : spawnService.getLocations()) {
            navigator.addButton(InventoryButton.builder()
                    .material(location.getMaterial())
                    .title(location.getName())
                    .action(player -> {
                        player.setVelocity(new Vector(0, 1.5,0));

                        player.sendTitle("§2Teleportiere...", "§a§l" + location.getName(), 20, 30, 10);
                        taskService.runSync(() -> {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                            taskService.runSync(() -> {
                                player.teleport(new Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY() + 10, location.getZ(), location.getYaw(), location.getPitch()));
                            },15);
                        },5);
                    })
                    .build());
        }
        inventoryPresentable = navigator.build();
    }

    public void openNavigator(Player player) {
        inventoryPresentable.present(playerService.get(player.getUniqueId()));
    }

}
