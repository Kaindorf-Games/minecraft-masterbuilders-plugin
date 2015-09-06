package me.stefvanschie.buildinggame.timers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;
import me.stefvanschie.buildinggame.utils.Arena;
import me.stefvanschie.buildinggame.utils.Plot;

public class VoteTimer extends BukkitRunnable {

	private int seconds;
	private int originalSeconds;
	private Arena arena;
	private Plot plot;
	
	public VoteTimer(int seconds, Arena arena) {
		this.seconds = seconds;
		originalSeconds = seconds;
		this.arena = arena;
	}

	@Override
	public void run() {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		if (seconds == originalSeconds) {
			plot = getNextPlot();
			if (plot == null) {
				arena.stop();
				this.cancel();
				return;
			}
			
			arena.setVotingPlot(plot);
			for (Plot plot : arena.getPlots()) {
				if (plot.getPlayerData() != null) {
					Player player = plot.getPlayerData().getPlayer();
					MessageManager.getInstance().send(player, messages.getString("voting.message")
							.replace("%playerplot%", player.getName()
							.replaceAll("&", "§")));
					arena.getScoreboard().show(player);
					
				}
			}
		}
		seconds--;
		if (seconds <= 0) {
			YamlConfiguration config = SettingsManager.getInstance().getConfig();
			
			seconds = config.getInt("votetimer");
			originalSeconds = seconds;
		}
	}
	public Plot getNextPlot() {
		for (Plot plot : arena.getPlots()) {
			if (!arena.getVotedPlots().contains(plot)) {
				if (plot.getPlayerData() != null) {
					return plot;
				}
			}
		}
		return null;
	}
}
