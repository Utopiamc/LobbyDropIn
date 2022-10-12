package de.utopiamc.lobby.events;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Controller
public class JoinQuitEvents {

    @Subscribe(event = PlayerJoinEvent.class)
    public void onJoin(@Event PlayerJoinEvent event) {
        event.setJoinMessage(null);
    } // This will cancel all join messages

    @Subscribe(event = PlayerQuitEvent.class)
    public void onQuit(@Event PlayerQuitEvent event) {
        event.setQuitMessage(null);
    } // This will cancel all quit messages

}
