package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.lobby.cloudnet.CloudNetGetPlayerOnline;
import org.bukkit.entity.Player;

@Command(value = "LobbySwitch", aliases = {"lobbyselector", "ls"})
public class LobbySwitchCommand {

    @MapRoute("")
    public void lobbySwitch(Player player) {
        CloudNetGetPlayerOnline cloudNetGetPlayerOnline = new CloudNetGetPlayerOnline();
        cloudNetGetPlayerOnline.openInv(player);
    }

}
