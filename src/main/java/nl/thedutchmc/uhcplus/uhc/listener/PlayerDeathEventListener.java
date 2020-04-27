package nl.thedutchmc.uhcplus.uhc.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.uhcplus.UhcPlus;
import nl.thedutchmc.uhcplus.teams.TeamHandler;

public class PlayerDeathEventListener implements Listener {

	private UhcPlus plugin;
	
	public PlayerDeathEventListener(UhcPlus plugin) {
		this.plugin = plugin;
	}
 	
	TeamHandler teamHandler = new TeamHandler(plugin, null, false);
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		teamHandler.playerDied(player.getUniqueId());
		
		for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
		}
		
		if(event.getEntity().getKiller() != null) {
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " was killed by " + player.getKiller().getName());
		}
		
		
		
		
	}
}