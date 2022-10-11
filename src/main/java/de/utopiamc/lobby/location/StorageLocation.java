package de.utopiamc.lobby.location;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

@Getter
public class StorageLocation implements ConfigurationSerializable {

    private final String name;
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final Material material;

    public StorageLocation(String name, Location location, Material material) {
        this(name, location.getWorld().getName(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), material);
    }

    public StorageLocation (String name, String world, double x, double y, double z, float yaw, float pitch, Material material) {
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.material = material;
    }

    public Location getLocation(){
        return new Location(org.bukkit.Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public Map<String, Object> serialize() {
        return Map.of("name", name, "world", world, "x", x, "y", y, "z", z, "yaw", yaw, "pitch", pitch, "material", material.name());
    }

    public static StorageLocation deserialize(Map<String, Object> map) {
        return new StorageLocation((String) map.get("name"), (String) map.get("world"), (double) map.get("x"), (double) map.get("y"), (double) map.get("z"), (float) map.get("yaw"), (float) map.get("pitch"), Material.valueOf((String) map.get("material")));
    }
}
