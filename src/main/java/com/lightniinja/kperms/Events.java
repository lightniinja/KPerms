package com.lightniinja.kperms;

import java.io.File;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
	
	private KPerms plugin;
	
	public Events(KPerms plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		// get player group
		String group = plugin.getConfig().getString("player." + e.getPlayer().getName());
		if(group != null) {
			plugin.setGroup(e.getPlayer(), group);
		} else {
			plugin.setGroup(e.getPlayer(), plugin.getConfig().getString("defaultGroup"));
			plugin.getConfig().set("player." + e.getPlayer().getName(), plugin.getConfig().getString("defaultGroup"));
			try {
				plugin.getConfig().save(new File(plugin.getDataFolder() + "\\config.yml"));
			} catch (IOException e1) {}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		onLeave(e.getPlayer());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		onLeave(e.getPlayer());
	}
	
	public void onLeave(Player p) {
		plugin.setGroup(p, plugin.getConfig().getString("defaultGroup"), plugin.getConfig().getString("player." + p.getName()));
	}
	
}
