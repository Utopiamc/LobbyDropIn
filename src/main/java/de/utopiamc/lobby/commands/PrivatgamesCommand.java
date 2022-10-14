package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Privatgames")
public class PrivatgamesCommand {

    @MapRoute("")
    public void privatgames(Player player) {
        player.sendMessage("§7[§6Privatgames§7] §eHallo, ich bin Privatgames. Ich bin hier, um dir deine privaten Spiele zu geben.");
    }

}
