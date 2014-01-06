package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KGroup;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.Utilities;

public class CommandGroupInfo {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String group = null;
	public CommandGroupInfo(CommandSender s, KPermsPlugin pl, String group) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.group = group;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.group.info")) {
			this.s.sendMessage(new Utilities(this.pl).format(new ConfigManager(this.pl).getMessage("prefix") + " " + new ConfigManager(this.pl).getMessage("no-permission")));
			return;
		}
		this.s.sendMessage(this.u.format("        &e*" + this.m.getMessage("prefix") + "&e*  &bGroup Info: &6" + this.group));
		KGroup g = new KGroup(this.group, this.pl);
		this.s.sendMessage(this.u.format("&c   Permissions:"));
		for(String str: g.getPermissions()) {
			this.s.sendMessage(this.u.format("     &e" + str));
		}
	}
}
