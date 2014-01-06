package com.lightniinja.kperms;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utilities {
	private KPermsPlugin pl = null;
	public Utilities(KPermsPlugin pl) {
		this.pl = pl;
	}
	public String format(String s) {
		return s.replace("&".toCharArray()[0], ChatColor.COLOR_CHAR);
	}
	public boolean setupFolders() {
		File f = new File(this.pl.getDataFolder() + "/playerdata/");
		if(!f.isDirectory()) {
			if(!f.mkdir())
				return false;
		}
		f = new File(this.pl.getDataFolder() + "/groups/");
		if(!f.isDirectory()) {
			if(!f.mkdir())
				return false;
		}
		if(new KGroup(new ConfigManager(this.pl).getDefaultGroup(), this.pl).make()) {
			return false;
		}
		return true;
	}
	public void refreshAllPermissions() {
		for(Player p: this.pl.getServer().getOnlinePlayers()) {
			KPlayer play = new KPlayer(p.getName(), this.pl);
			play.clearPermissions();
			play.assignPermissions();
		}
	}
	public String[] getGroups() {
		File folder = new File(this.pl.getDataFolder() + "/groups");
		File[] listOfFiles = folder.listFiles();
		String[] groups = new String[listOfFiles.length];
		for(int i = 0; i < listOfFiles.length; i++) {
			groups[i] = listOfFiles[i].getName().split(".")[0];
		}
		return groups;
	}
}
