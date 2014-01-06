package com.lightniinja.kperms;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.lightniinja.kperms.commands.CommandMain;

public class KPermsPlugin extends JavaPlugin {

	public void onEnable() {
		this.saveDefaultConfig();
		if(!(new File(this.getDataFolder() + "/groups/" + new ConfigManager(this).getDefaultGroup() + ".yml").exists())) {
			new Utilities(this).setupFolders();
		}
		this.getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
		this.getCommand("kperms").setExecutor(new CommandMain(this));
	}
	
	public void onDisable() {
		this.saveConfig();
	}
	
}
