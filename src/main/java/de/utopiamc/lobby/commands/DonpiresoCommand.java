package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import org.bukkit.entity.Player;

@Command(value = "Donpireso")
public class DonpiresoCommand {

    @MapRoute("")
    public void donpireso(Player player) {
        player.sendMessage("§7[§6Donpireso§7] §eHallo, ich bin Donpireso. Ich bin hier, um Sie über unsere Ränge zu informieren.");
    }

}
