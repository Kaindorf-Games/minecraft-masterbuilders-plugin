package com.gmail.stefvanschiedev.buildinggame.leaderboard;

import com.gmail.stefvanschiedev.buildinggame.Main;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class LeaderboardEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            Player p = event.getPlayer();
            p.performCommand("bg join Button");
        }, 40);

    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event)
    {
        Arena.sendLeaderboard();
    }
}
