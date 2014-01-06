package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.Utilities;

public class CommandReload {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	public CommandReload(CommandSender s, KPermsPlugin pl) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.reload")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		this.pl.reloadConfig();
		this.pl.getServer().getPluginManager().disablePlugin(this.pl);
		this.pl.getServer().getPluginManager().enablePlugin(this.pl);
		this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("reload-complete")));
	}
}
