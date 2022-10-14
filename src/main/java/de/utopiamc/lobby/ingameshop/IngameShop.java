package de.utopiamc.lobby.ingameshop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class IngameShop {

    public static HashMap<Player, IngameShop> ingameshop = new HashMap<>();

    public final Player player;
    public final Location startLocation;
    public final ArmorStand as;
    public final ItemStack[] inventory;

    public IngameShop(Player player){
        this.startLocation = player.getLocation();
        this.player = player;

        ArrayList<Location> locs = new ArrayList<>();
        locs.add(new Location(Bukkit.getWorld("world"), 58.50, 28.50, 83.50, 318, 31));
        locs.add(new Location(Bukkit.getWorld("world"), 77.47, 15.50, 87.48, 0 ,16));
        locs.add(new Location(Bukkit.getWorld("world"), 63.99, 50.50, 133.99, 197, 40));
        locs.add(new Location(Bukkit.getWorld("world"), 103.50, 41.50, 181.50, 524, 18));
        locs.add(new Location(Bukkit.getWorld("world"), 86.50, 27.50, 87.50, 28, 40));

        inventory = player.getInventory().getContents();

        player.getInventory().clear();

        Random r = new Random();
        int i = r.nextInt(locs.size());

        Location loc = locs.get(i);

        ArmorStand armorStand = (ArmorStand) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ARMOR_STAND);

        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.teleport(loc.add(0, -0.5, 0));
        player.teleport(loc);

        ingameshop.put(player, this);
        this.as = armorStand;

        armorStand.setPassenger(player);

    }

    public void remove(){
        as.removePassenger(player);
        player.getInventory().setContents(inventory);
        player.teleport(startLocation);
        as.remove();
        ingameshop.remove(player);
    }

}
