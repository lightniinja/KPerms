package com.lightniinja.kperms;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class KGroup {
	private String groupName = null;
	private KPermsPlugin pl = null;
	private FileConfiguration c = null;
	public KGroup(String groupName, KPermsPlugin pl) {
		this.groupName = groupName;
		this.pl = pl;
		this.c = YamlConfiguration.loadConfiguration(new File(this.pl.getDataFolder() + "/groups/" + this.groupName +".yml"));
	}
	
	public List<String> getPermissions() {
		return this.c.getStringList("permissions");
	}
	public boolean make() {
		this.c.set("permissions", "");
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean addPermission(String s) {
		List<String> g = this.c.getStringList("permissions");
		g.add(s);
		this.c.set("permissions", g);
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml"));
			new Utilities(this.pl).refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean removePermission(String s) {
		List<String> g = this.c.getStringList("permissions");
		g.remove(s);
		this.c.set("permissions", g);
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml"));
			new Utilities(this.pl).refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean hasPermission(String s) {
		if(this.c.getStringList("permissions").contains(s))
			return true;
		return false;
	}
	public boolean isGenerated() {
		return new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml").exists();
	}

	public boolean remove() {
		if(!new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml").exists())
			return false;
		File f = new File(this.pl.getDataFolder() + "/groups/" + this.groupName + ".yml");
		return f.delete();
	}
}
