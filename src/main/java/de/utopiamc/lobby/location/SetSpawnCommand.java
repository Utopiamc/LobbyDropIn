package de.utopiamc.lobby.location;

import com.google.inject.Inject;
import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.commands.descriptors.Permission;
import de.utopiamc.framework.api.commands.descriptors.Variable;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Command(value = "setspawn")
public class SetSpawnCommand {

    private final SpawnService spawnService;

    @Inject
    public SetSpawnCommand(SpawnService spawnService) {
        this.spawnService = spawnService;
    }


    @Permission("lobby.setspawn")
    @MapRoute("<Name> <Material> <Round>")
    public String setSpawn(Player player, @Variable("Name") String name, @Variable("Material") Material material, @Variable("Round") Boolean round) {
        Location location = player.getLocation();
        if (round) {
            round(location);
        }
        spawnService.saveLocation(location, name, material);
        return "Position $s%s $rgespeichert!".formatted(name);
    }

    private void round(Location location) {
        location.setX(location.getBlockX() + 0.5);
        location.setZ(location.getBlockZ() + 0.5);
        location.setY(location.getBlockY());

        location.setYaw(Math.round(location.getYaw() / 45) * 45);
        location.setPitch(Math.round(location.getPitch() / 45) * 45);
    }
}
