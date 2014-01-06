package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KGroup;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.Utilities;

public class CommandGroupAdd {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String group = null;
	public CommandGroupAdd(CommandSender s, KPermsPlugin pl, String group) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.group = group;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.group.add")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		KGroup g = new KGroup(this.group, this.pl);
		if(g.isGenerated()) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("already-created")));
		} else {
			if(g.make()) {
				this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("group-created")));
			} else {
				this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("unsuccessful")));
			}
		}
	}
}
