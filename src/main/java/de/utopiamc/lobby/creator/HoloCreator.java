package de.utopiamc.lobby.creator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class HoloCreator {

    private final String world;
    private final double x;
    private final double y;
    private final double z;

    private ArrayList<String> lines = new ArrayList<>();

    public HoloCreator(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public HoloCreator addLine(String line) {
        this.lines.add(line);
        return this;
    }

    public void spawn() {
        for (int i = 0; i < lines.size(); i++) {
            ArmorStand as = (ArmorStand) Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), x, y + lines.size() * 0.3, z), EntityType.ARMOR_STAND);
            as.setCustomName(lines.get(0));
            as.setCustomNameVisible(true);
            as.setInvulnerable(true);
            as.setVisible(false);
            as.setGravity(false);

            lines.remove(0);
        }
    }
}
