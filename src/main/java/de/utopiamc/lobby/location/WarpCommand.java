package de.utopiamc.lobby.location;

import com.google.inject.Inject;
import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.commands.descriptors.Variable;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "warp")
public class WarpCommand {

    private final SpawnService spawnService;

    @Inject
    public WarpCommand(SpawnService spawnService) {
        this.spawnService = spawnService;
    }

    @MapRoute("<name>")
    public void warp(Player player, @Variable("name") String name) {
        player.teleport(spawnService.loadLocation(name).getLocation());
    }
}
