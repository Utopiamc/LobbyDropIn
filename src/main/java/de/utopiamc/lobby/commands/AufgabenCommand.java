package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Aufgaben")
public class AufgabenCommand {

    @MapRoute("")
    public void bixby(Player player) {
        player.sendMessage("§7[§6Tasks§7] §eHi, I'm Tasks. I'm here to give you tasks to do!");
    }

}
