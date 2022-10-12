package de.utopiamc.lobby.scoreboard;

import com.google.inject.Inject;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.economy.EconomyHoldingsUpdateEvent;
import de.utopiamc.framework.api.event.economy.qualifier.Holder;
import de.utopiamc.framework.api.event.economy.qualifier.TargetEconomy;
import de.utopiamc.framework.api.event.economy.qualifier.Value;
import de.utopiamc.framework.api.info.ServerName;
import de.utopiamc.framework.api.model.Economy;
import de.utopiamc.framework.api.model.PlayerEconomy;
import de.utopiamc.framework.api.service.EconomyService;
import de.utopiamc.framework.api.service.ScoreboardFactory;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.framework.api.ui.scoreboard.ScoreboardBuilder;
import de.utopiamc.framework.api.ui.scoreboard.ScoreboardLineUpdater;
import de.utopiamc.framework.api.ui.scoreboard.Subscribeables;

import java.util.UUID;

@Controller
public class Scoreboard {

    private ScoreboardLineUpdater updater;

    @Inject
    public void Sui(ScoreboardFactory factory, EconomyService economyService, @ServerName String serverName) {
        ScoreboardBuilder and = factory
                .createScoreboard()
                .addDynamicLine()
                .setTitle("Server")
                .setContent(serverName)
                .and()
                .addDynamicLine()
                .setTitle("Spieler")
                .setContent(Subscribeables.playerCount())
                .and();
        this.updater = and
                .addDynamicLine()
                .setTitle("UtopiaCoins")
                .setContent();
        updater.onBound((player) -> {
            economyService.getEconomy(UUID.fromString("d326a2e4-c92a-4a16-bdfa-1e501091b889"), player, economy -> {
                if (economy.isEmpty()) return;
                PlayerEconomy playerEconomy = economy.get();

                updater.update(playerEconomy.getValue() + playerEconomy.getEconomy().getSymbol(), player.getUuid());
            });

            return ""; // Return empty string because we will update the line later
        });
        and
                .addDynamicLine()
                .setTitle("OnlineTime")
                .setContent("0h 0m 0s")
                .build()
                .autoBind();
    }

    @Subscribe(event = EconomyHoldingsUpdateEvent.class)
    public void onEconomyUpdate(@Value double value, @Holder UUID holder, @TargetEconomy Economy economy) {
        updater.update(value + economy.getSymbol(), holder);
    }
}
