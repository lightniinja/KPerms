package com.lightniinja.kperms;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class KPlayer {
	private String p = null;
	private FileConfiguration c = null;
	private KPermsPlugin pl = null;
	public KPlayer(String p, KPermsPlugin pl) {
		this.p = p;
		this.pl = pl;
		this.c = YamlConfiguration.loadConfiguration(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
	}
	public KPlayer(Player p, KPermsPlugin pl) {
		this.p = p.getName();
		this.pl = pl;
		this.c = YamlConfiguration.loadConfiguration(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
	}
	public boolean make() {
		this.c.set("primaryGroup", new ConfigManager(this.pl).getDefaultGroup());
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public String getPrimaryGroup() {
		return this.c.getString("primaryGroup");
	}
 	public List<String> getGroups() {
 		return this.c.getStringList("groups");
 	}
	public boolean setPrimaryGroup(String g) {
		this.c.set("primaryGroup", g);
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean isMemberOfGroup(String g) {
		if(this.c.getStringList("groups").contains(g))
			return true;
		return false;
	}
	public void clearPermissions() {
		for(PermissionAttachmentInfo a: this.pl.getServer().getPlayerExact(this.p).getEffectivePermissions()) {
			if(a.getAttachment() == null)
				return;
			this.pl.getServer().getPlayerExact(this.p).removeAttachment(a.getAttachment());
		}
	}
	public boolean addGroup(String s) {
		List<String> g = this.c.getStringList("groups");
		g.add(s);
		this.c.set("groups", g);
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
			new Utilities(this.pl).refreshAllPermissions();
			return true;
		} catch (IOException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}
	public boolean removeGroup(String s) {
		List<String> g = this.c.getStringList("groups");
		g.remove(s);
		this.c.set("groups", g);
		try {
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
			new Utilities(this.pl).refreshAllPermissions();
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
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
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
			this.c.save(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml"));
			new Utilities(this.pl).refreshAllPermissions();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean hasPermission(String s) {
		List<String> p = this.c.getStringList("permissions");
		List<String> g = this.c.getStringList("groups");
		g.add(this.c.getString("primaryGroup"));
		if(p.contains(s)) {
			return true;
		}
		for(String str: g) {
			if(new KGroup(str, this.pl).getPermissions().contains(s)) {
				return true;
			}
		}
		return false;
	}
	public List<String> getPermissions() {
		List<String> perms = new ArrayList<String>();
		for(String s: this.c.getStringList("groups")) {
			for(String perm: new KGroup(s, this.pl).getPermissions()) {
				perms.add(perm);
			}
		}
		for(String perm: new KGroup(this.c.getString("primaryGroup"), this.pl).getPermissions()) {
			perms.add(perm);
		}
		for(String perm: this.c.getStringList("permissions")) {
			perms.add(perm);
		}
		return perms;
	}
	public void assignPermissions() {
		for(String s: this.getPermissions()) {
			if(s.startsWith("-")) {
				this.pl.getServer().getPlayerExact(this.p).addAttachment(this.pl, s.replace("-", ""), false);
			} else {
				this.pl.getServer().getPlayerExact(this.p).addAttachment(this.pl, s, true);
			}
		}
	}
	public boolean isGenerated() {
		if(new File(this.pl.getDataFolder() + "/playerdata/" + this.p + ".yml").exists())
			return true;
		return false;
	}
}
