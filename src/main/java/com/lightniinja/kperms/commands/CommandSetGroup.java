package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KPlayer;
import com.lightniinja.kperms.Utilities;

public class CommandSetGroup {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String player = null;
	private String group = null;
	public CommandSetGroup(CommandSender s, KPermsPlugin pl, String player, String group) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.player = player;
		this.group = group;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.user.group.set")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		KPlayer p = new KPlayer(this.player, this.pl);
		if(p.setPrimaryGroup(this.group)) {
			p.clearPermissions();
			p.assignPermissions();
			String str = this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("set-group"));
			str = str.replaceAll("%g", this.group);
			str = str.replaceAll("%player", this.player);
			this.s.sendMessage(str);
		} else {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("unsuccessful")));
		}
	}
}
