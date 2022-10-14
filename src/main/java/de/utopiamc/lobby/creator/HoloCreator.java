package de.utopiamc.lobby.creator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class HoloCreator {

    public static HashMap<UUID, HoloCreator> holos = new HashMap<>();

    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private Consumer<Player> action;

    private final ArrayList<String> lines = new ArrayList<>();

    public HoloCreator(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public HoloCreator addLine(String line) {
        lines.add(line);
        return this;
    }

    public HoloCreator onClick(final Consumer<Player> action) {
        this.action = action;
        return this;
    }

    public Consumer<Player> getAction() {
        return action;
    }

    public void spawn() {
        for (int i = 0; i < lines.size(); i++) {
            ArmorStand as = (ArmorStand) Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), x, (y - (i * 0.3)) + (lines.size() * 0.3), z), EntityType.ARMOR_STAND);
            as.setCustomName(lines.get(i));
            as.setCustomNameVisible(true);
            as.setInvulnerable(true);
            as.setVisible(false);
            as.setGravity(false);

            holos.put(as.getUniqueId(), this);
        }
    }
}
