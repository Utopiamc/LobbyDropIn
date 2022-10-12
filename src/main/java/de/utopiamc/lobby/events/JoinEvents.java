package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.event.player.PlayerJoinEvent;

@Controller
public class JoinEvents {

    @Subscribe(event = PlayerJoinEvent.class)
    public void onJoin(@Event PlayerJoinEvent event) {
        event.setJoinMessage(null);
    } // This will cancel all join messages

}
