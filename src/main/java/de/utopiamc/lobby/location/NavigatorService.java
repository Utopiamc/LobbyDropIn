package de.utopiamc.lobby.location;

import com.google.inject.Inject;
import de.utopiamc.framework.api.service.FrameworkPlayerService;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.framework.api.ui.inventory.InventoryFactory;
import de.utopiamc.framework.api.ui.inventory.InventoryPresentable;
import de.utopiamc.framework.api.ui.inventory.button.ButtonInventoryBuilder;
import de.utopiamc.framework.api.ui.inventory.button.InventoryButton;
import org.bukkit.entity.Player;

@Service
public class NavigatorService {
    
    private final InventoryFactory inventoryFactory;
    private final FrameworkPlayerService playerService;
    private final SpawnService spawnService;

    private InventoryPresentable inventoryPresentable;

    @Inject
    public NavigatorService(InventoryFactory inventoryFactory, FrameworkPlayerService playerService, SpawnService spawnService) {
        this.inventoryFactory = inventoryFactory;
        this.playerService = playerService;
        this.spawnService = spawnService;
    }

    @Inject
    public void setUpNavigator(){
        ButtonInventoryBuilder navigator = inventoryFactory.createButtonInventory("Navigator");
        for (StorageLocation location : spawnService.getLocations()) {
            navigator.addButton(InventoryButton.builder()
                    .material(location.getMaterial())
                    .title(location.getName())
                    .action(player -> {
                        player.teleport(location.getLocation());
                    })
                    .build());
        }
        inventoryPresentable = navigator.build();
    }

    public void openNavigator(Player player) {
        inventoryPresentable.present(playerService.get(player.getUniqueId()));
    }

}
