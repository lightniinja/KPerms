package com.lightniinja.kperms.commands;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.KPlayer;
import com.lightniinja.kperms.Utilities;

public class CommandRemovePermission {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	private Utilities u = null;
	private ConfigManager m = null;
	private String player = null;
	private String permission = null;
	public CommandRemovePermission(CommandSender s, KPermsPlugin pl, String player, String permission) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
		this.player = player;
		this.permission = permission;
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.user.permission.remove")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		KPlayer p = new KPlayer(this.player, this.pl);
		if(p.removePermission(this.permission)) {
			p.clearPermissions();
			p.assignPermissions();
			String str = this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("removed-permission"));
			str = str.replaceAll("%perm", this.permission);
			str = str.replaceAll("%player", this.player);
			this.s.sendMessage(str);
		} else {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("unsuccessful")));
		}
	}
}
