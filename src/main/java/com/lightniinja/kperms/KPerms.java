package com.lightniinja.kperms;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KPerms extends JavaPlugin {
	
	public void onEnable() {
		this.saveDefaultConfig();
		// register command;
		getCommand("kperms").setExecutor(new Commands(this));
		// register event;
		getServer().getPluginManager().registerEvents(new Events(this), this);
	}
	
	public void reloadPermissions() {
		for(Player p: Bukkit.getOnlinePlayers()) {
			String g = getConfig().getString("player." + p.getName());
			setGroup(p, g, g);
		}
		getLogger().info("Permissions reloaded.");
	}
	
	public List<String> getGroupPermissions(String groupName) {
		return getConfig().getStringList("group." + groupName);
	}
	
	public void setGroup(Player p, String g) {
		for(String perm: getGroupPermissions(g)) {
			if(perm.startsWith("-")) {
				p.addAttachment(this, perm.replace("-", ""), false);
				
			} else {
				p.addAttachment(this, perm, true);
			}
		}
	}
	public void setGroup(Player p, String g, String og) {
		for(String perm: getGroupPermissions(og)) {
			if(perm.startsWith("-")) {
				p.addAttachment(this, perm.replace("-", ""), true);
			} else {
				p.addAttachment(this, perm, false);
			}
		}
		for(String perm: getGroupPermissions(g)) {
			if(perm.startsWith("-")) {
				p.addAttachment(this, perm.replace("-", ""), false);
			} else {
				p.addAttachment(this, perm, true);
			}
		}
	}
}
