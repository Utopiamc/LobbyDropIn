package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Firion")
public class FirionCommand {

    @MapRoute("")
    public void firion(Player player) {
        player.sendMessage("§7[§6Firion§7] §eHi, I'm Firion. I'm here to give you daily rewards.");
    }

}
