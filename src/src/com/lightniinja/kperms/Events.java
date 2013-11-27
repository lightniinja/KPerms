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

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		// get player group
		String group = KPerms.instance.getConfig().getString("player." + e.getPlayer().getName());
		if(group != null) {
			KPerms.setGroup(e.getPlayer(), group);
		} else {
			KPerms.setGroup(e.getPlayer(), KPerms.instance.getConfig().getString("defaultGroup"));
			KPerms.instance.getConfig().set("player." + e.getPlayer().getName(), KPerms.instance.getConfig().getString("defaultGroup"));
			try {
				KPerms.instance.getConfig().save(new File(KPerms.instance.getDataFolder() + "\\config.yml"));
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
		KPerms.setGroup(p, KPerms.instance.getConfig().getString("defaultGroup"), KPerms.instance.getConfig().getString("player." + p.getName()));
	}
	
}
