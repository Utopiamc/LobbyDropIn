package de.utopiamc.lobby.cloudnet;

import com.google.inject.Inject;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceConnectNetworkEvent;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.event.BridgeUpdateCloudOfflinePlayerEvent;
import de.dytanic.cloudnet.ext.bridge.event.BridgeUpdateCloudPlayerEvent;
import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.lobby.creator.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class CloudNetGetPlayerOnline {

    public int countServiceInfoSnapshotPlayerCountOfTask(String taskName) {
        int counter = 0;

        for (ServiceInfoSnapshot serviceInfoSnapshot : CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices(taskName)) {
            counter += serviceInfoSnapshot.getProperty(BridgeServiceProperty.ONLINE_COUNT).orElse(0);
        }

        return counter;
    }

    public Inventory inv = Bukkit.createInventory(null, 9, "§8» §a§lLobby Selector §8«");

    @Inject
    public void startUp(){
        setUpInv("Lobby", inv);
    }

    public void setUpInv(String taskName, Inventory inv){
        List<ItemStack> itemStacks = new ArrayList<>();

        for (ServiceInfoSnapshot serviceInfoSnapshot : CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices(taskName)) {
            itemStacks.add(new ItemBuilder((serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_ONLINE).orElse(false) ? Material.EMERALD_BLOCK : (serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_STARTING).orElse(false) ? Material.IRON_BLOCK : (serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_FULL).orElse(false) ? Material.DIAMOND_BLOCK : Material.COAL_BLOCK))))
                    .setDisplayName("§a" + serviceInfoSnapshot.getServiceId().getName())
                    .addLineLore("§7Spieler: §e" + serviceInfoSnapshot.getProperty(BridgeServiceProperty.ONLINE_COUNT).orElse(0) + "§8/§e" + serviceInfoSnapshot.getProperty(BridgeServiceProperty.MAX_PLAYERS).orElse(0))
                    .addLineLore("§7Status: §e" + (serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_ONLINE).orElse(false) ? "§aOnline" : (serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_STARTING).orElse(false) ? "§eStarting" : (serviceInfoSnapshot.getProperty(BridgeServiceProperty.IS_FULL).orElse(false) ? "§4Voll" : "§cOffline"))))
                    .build());
        }

        itemStacks.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getItemMeta().getDisplayName().split("-")[1])));
        for (int i = 0; i < itemStacks.size(); i++) {
            inv.setItem(i, itemStacks.get(i));
        }

    }

    public void sendPlayerToService(Player player, String serverName){
        player.sendMessage("§8» §7Du wirst nun zum Server §e" + serverName + " §7verbunden...");

        IPlayerManager iPlayerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);
        PlayerExecutor playerExecutor = iPlayerManager.getPlayerExecutor(player.getUniqueId());
        playerExecutor.connect(serverName);
    }

    public void openInv(Player player){
        setUpInv("Lobby", inv);
        player.openInventory(inv);
    }

    //Handles an online player update only
    @Subscribe(event = BridgeUpdateCloudPlayerEvent.class)
    public void handle1(@Event BridgeUpdateCloudPlayerEvent event) {
        ICloudPlayer cloudPlayer = event.getCloudPlayer();

        setUpInv("Lobby", inv);
    }

    //Handles an offline player update only
    @Subscribe(event = BridgeUpdateCloudOfflinePlayerEvent.class)
    public void handle2(@Event BridgeUpdateCloudOfflinePlayerEvent event) {
        ICloudOfflinePlayer cloudPlayer = event.getCloudOfflinePlayer();

        this.setUpInv("Lobby", this.inv);
    }

    //Handles a service connect event only
    @Subscribe(event = CloudServiceConnectNetworkEvent.class)
    public void handle3(@Event CloudServiceConnectNetworkEvent event) {

        this.setUpInv("Lobby", this.inv);
    }

    @Subscribe(event = InventoryClickEvent.class)
    public void handle4(@Event InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getTitle().equals(inv.getTitle())){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getType() == Material.AIR) return;
            if(event.getCurrentItem().getItemMeta().getDisplayName().contains("§aLobby-")){
                event.getWhoClicked().closeInventory();
                sendPlayerToService((Player) event.getWhoClicked(), event.getCurrentItem().getItemMeta().getDisplayName().split("§a")[1]);
            }
        }
    }

}
