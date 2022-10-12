package de.utopiamc.lobby.commands;

import com.google.inject.Inject;
import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.lobby.location.NavigatorService;
import de.utopiamc.lobby.location.SpawnService;
import org.bukkit.entity.Player;

@Command(value = "warp")
public class WarpCommand {

    private final NavigatorService navigatorService;

    @Inject
    public WarpCommand(NavigatorService navigatorService) {
        this.navigatorService = navigatorService;
    }

    @MapRoute("")
    public void warp(Player player) {
        navigatorService.openNavigator(player);
    }
}
