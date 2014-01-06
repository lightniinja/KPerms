package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.Utilities;
import com.lightniinja.kperms.KPlayer;

public class CommandUserInfo {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String player = null;
	public CommandUserInfo(CommandSender s, KPermsPlugin pl, String player) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.player = player;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.user.info")) {
			this.s.sendMessage(new Utilities(this.pl).format(new ConfigManager(this.pl).getMessage("prefix") + " " + new ConfigManager(this.pl).getMessage("no-permission")));
			return;
		}
		this.s.sendMessage(this.u.format("        &e*" + this.m.getMessage("prefix") + "&e*  &bUser Info: &6" + this.player));
		KPlayer p = new KPlayer(this.player, this.pl);
		this.s.sendMessage(this.u.format("&c   Permissions:"));
		for(String str: p.getPermissions()) {
			this.s.sendMessage(this.u.format("     &e" + str));
		}
		this.s.sendMessage(this.u.format("&c   Groups:"));
		for(String str: p.getGroups()) {
			if(str.equalsIgnoreCase(p.getPrimaryGroup()))
				continue;
			this.s.sendMessage(this.u.format("     &e" + str));
		}
		this.s.sendMessage(this.u.format("  &bPrimary group: &2" + p.getPrimaryGroup()));
	}
}
