package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KPlayer;
import com.lightniinja.kperms.Utilities;

public class CommandAddGroup {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String player = null;
	private String group = null;
	public CommandAddGroup(CommandSender s, KPermsPlugin pl, String player, String group) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.player = player;
		this.group = group;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.user.group.add")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		KPlayer p = new KPlayer(this.player, this.pl);
		if(p.addGroup(this.group)) {
			p.clearPermissions();
			p.assignPermissions();
			String str = this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("added-group"));
			str = str.replaceAll("%g", this.group);
			str = str.replaceAll("%player", this.player);
			this.s.sendMessage(str);
		} else {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("unsuccessful")));
		}
	}
}
