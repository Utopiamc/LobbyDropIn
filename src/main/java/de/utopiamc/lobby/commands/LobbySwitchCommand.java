package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.lobby.cloudnet.CloudnetLobbyOnline;
import org.bukkit.entity.Player;

@Command(value = "LobbySwitch", aliases = {"lobbyselector", "ls"})
public class LobbySwitchCommand {

    @MapRoute("")
    public void lobbySwitch(Player player) {
        CloudnetLobbyOnline cloudNetGetPlayerOnline = new CloudnetLobbyOnline();
        cloudNetGetPlayerOnline.openInv(player);
    }

}
