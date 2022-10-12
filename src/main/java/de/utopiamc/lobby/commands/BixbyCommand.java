package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;

import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Bixby")
public class BixbyCommand {

    @MapRoute("")
    public void bixby(Player player) {
        player.sendMessage("§7[§6Bixby§7] §eHi, I'm Bixby. I'm here to help you with your questions.");
    }

}
