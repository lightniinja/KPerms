package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KGroup;
import com.lightniinja.kperms.Utilities;

public class CommandGroupPRemove {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String group = null;
	private String permission = null;
	public CommandGroupPRemove(CommandSender s, KPermsPlugin pl, String group, String permission) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.group = group;
		this.permission = permission;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.group.permission.remove")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		KGroup g = new KGroup(this.group, this.pl);
		if(g.removePermission(this.permission)) {
			new Utilities(this.pl).refreshAllPermissions();
			String str = this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("gremoved-permission"));
			str = str.replaceAll("%perm", this.permission);
			str = str.replaceAll("%g", this.group);
			this.s.sendMessage(str);
		} else {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("unsuccessful")));
		}
	}
}
