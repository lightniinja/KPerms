package com.lightniinja.kperms;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KPerms extends JavaPlugin {
	
	public static KPerms instance;
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		
		// register command;
		this.getCommand("kperms").setExecutor(new Commands());
		// register event;
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	public static void reloadPermissions() {
		for(Player p: Bukkit.getOnlinePlayers()) {
			String g = KPerms.instance.getConfig().getString("player." + p.getName());
			setGroup(p, g, g);
		}
		KPerms.instance.getLogger().info("Permissions reloaded.");
	}
	
	public static List<String> getGroupPermissions(String groupName) {
		return KPerms.instance.getConfig().getStringList("group." + groupName);
	}
	
	public static void setGroup(Player p, String g) {
		for(String perm: getGroupPermissions(g)) {
			if(perm.startsWith("-")) {
				p.addAttachment(KPerms.instance, perm.replace("-", ""), false);
				
			} else {
				p.addAttachment(KPerms.instance, perm, true);
			}
		}
	}
	public static void setGroup(Player p, String g, String og) {
		for(String perm: getGroupPermissions(og)) {
			if(perm.startsWith("-")) {
				p.addAttachment(KPerms.instance, perm.replace("-", ""), true);
			} else {
				p.addAttachment(KPerms.instance, perm, false);
			}
		}
		for(String perm: getGroupPermissions(g)) {
			if(perm.startsWith("-")) {
				p.addAttachment(KPerms.instance, perm.replace("-", ""), false);
			} else {
				p.addAttachment(KPerms.instance, perm, true);
			}
		}
	}
}
