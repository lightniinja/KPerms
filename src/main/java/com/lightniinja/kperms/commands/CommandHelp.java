package com.lightniinja.kperms.commands;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.ConfigManager;
import com.lightniinja.kperms.KPermsPlugin;
import com.lightniinja.kperms.Utilities;

public class CommandHelp {
	private CommandSender s = null;
	private KPermsPlugin pl = null;
	Utilities u = null;
	ConfigManager m = null;
	public CommandHelp(CommandSender s, KPermsPlugin pl) {
		this.s = s;
		this.pl = pl;
		this.u = new Utilities(this.pl);
		this.m = new ConfigManager(this.pl);
	}
	public void execute() {
		if(!this.s.hasPermission("kperms.help")) {
			this.s.sendMessage(this.u.format(this.m.getMessage("prefix") + " " + this.m.getMessage("no-permission")));
			return;
		}
		
		HashMap<String, String> commands = new HashMap<String, String>();
		commands.put("help", "View all avaliable commands.");
		commands.put("reload", "Reload the plugin and config.");
		commands.put("user <username>", "View <username>s permissions and groups.");
		commands.put("user <username> permission <add/remove> <permission>", "Adds or removes <permission> from <username>.");
		commands.put("user <username> group <set/add/remove> <group>", "Sets, adds, or removes <group> from <username>.");
		commands.put("group <group>", "View <group>s permissions.");
		commands.put("group <group> <add/remove>", "Adds or removes a group.");
		commands.put("group <group> permission <add/remove>", "Adds or removes a permission from a group.");
		s.sendMessage(this.u.format("&c&l*=*=*=*=*=*=*=*=* &r" + this.m.getMessage("prefix") + " &c&l*=*=*=*=*=*=*=*=*"));
		for(String str: commands.keySet()) {
			s.sendMessage(this.u.format("&d/kperms &e" + str + " &7| &d" + commands.get(str)));
		}
		s.sendMessage(this.u.format("&c&l*=*=*=*=*=*=*=*=* &r" + this.m.getMessage("prefix") + " &c&l*=*=*=*=*=*=*=*=*"));
	}
}
