package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;

import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Bixby")
public class BixbyCommand {

    @MapRoute("")
    public void bixby(Player player) {
        player.sendMessage("§7[§6Bixby§7] §eHallo, ich bin Bixby. Ich bin hier, um Ihnen bei Ihren Fragen zu helfen.");
    }

}
