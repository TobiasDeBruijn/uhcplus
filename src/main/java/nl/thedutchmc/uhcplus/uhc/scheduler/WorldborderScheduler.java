package nl.thedutchmc.uhcplus.uhc.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

import nl.thedutchmc.uhcplus.UhcPlus;
import nl.thedutchmc.uhcplus.presets.PresetHandler;

public class WorldborderScheduler {

	public void scheduleWorldborder() {

		double worldBorderStartingSize = Double.valueOf(PresetHandler.worldBorderSize);
		double worldBorderShrinkTo = Double.valueOf(PresetHandler.worldBorderShrinkTo);

		long worldBorderStartShrinkAfter = Long.valueOf(PresetHandler.worldBorderShrinkAfter);
		long gameTimeLeft = Long.valueOf(PresetHandler.gameTime - worldBorderStartShrinkAfter);

		long worldBorderStartShrinkAfterTick = worldBorderStartShrinkAfter * 60 * 20;
		long gameTimeLeftSecond = gameTimeLeft * 60;

		World overworld = Bukkit.getServer().getWorld("uhcworld");

		WorldBorder worldBorder = overworld.getWorldBorder();
		worldBorder.setSize(worldBorderStartingSize);
		worldBorder.setCenter(0, 0);

		String minuteSinglePlural = (worldBorderStartShrinkAfter != 1) ? " minutes" : " minute";
		Bukkit.broadcastMessage(ChatColor.GRAY + "The world border will start shrinking in " + ChatColor.RED
				+ worldBorderStartShrinkAfter + ChatColor.GRAY + minuteSinglePlural + "!");

		new BukkitRunnable() {

			@Override
			public void run() {
				worldBorder.setSize(worldBorderShrinkTo, gameTimeLeftSecond);

			}
		}.runTaskLaterAsynchronously(UhcPlus.INSTANCE, worldBorderStartShrinkAfterTick);

	}
}
