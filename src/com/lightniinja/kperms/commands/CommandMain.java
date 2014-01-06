package com.lightniinja.kperms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lightniinja.kperms.KPermsPlugin;

public class CommandMain implements CommandExecutor {
	private KPermsPlugin pl = null;
	public CommandMain(KPermsPlugin pl) {
		this.pl = pl;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			new CommandHelp(sender, this.pl).execute();
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("help")) {
				new CommandHelp(sender, this.pl).execute();
			} else if(args[0].equalsIgnoreCase("reload")) {
				new CommandReload(sender, this.pl).execute();
			} else {
				new CommandUnknown(sender, this.pl).execute();
			}
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("user")) {
				new CommandUserInfo(sender, this.pl, args[1]).execute();
			} else {
				new CommandUnknown(sender, this.pl).execute();
			}
		} else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("group")) {
				if(args[2].equalsIgnoreCase("add")) {
					new CommandGroupAdd(sender, this.pl, args[1]).execute();
				} else if(args[2].equalsIgnoreCase("remove")) {
					new CommandGroupRemove(sender, this.pl, args[1]).execute();
				} else {
					new CommandUnknown(sender, this.pl).execute();
				}
			} else {
				new CommandUnknown(sender, this.pl).execute();
			}
		}else if(args.length == 5) {
			if(args[0].equalsIgnoreCase("user")) {
				if(args[2].equalsIgnoreCase("permission")) {
					if(args[3].equalsIgnoreCase("add")) {
						new CommandAddPermission(sender, this.pl, args[1], args[4]).execute();
					} else if(args[3].equalsIgnoreCase("remove")) {
						new CommandRemovePermission(sender, this.pl, args[1], args[4]).execute();
					} else {
						new CommandUnknown(sender, this.pl).execute();
					}
				} else if(args[2].equalsIgnoreCase("group")) {
					if(args[3].equalsIgnoreCase("set")) {
						new CommandSetGroup(sender, this.pl, args[1], args[4]).execute();
					} else if(args[3].equalsIgnoreCase("add")) {
						new CommandAddGroup(sender, this.pl, args[1], args[4]).execute();
					} else if(args[3].equalsIgnoreCase("remove")) {
						new CommandRemoveGroup(sender, this.pl, args[1], args[4]).execute();
					} else {
						new CommandUnknown(sender, this.pl).execute();
					}
				} else {
					new CommandUnknown(sender, this.pl).execute();
				}
			} else if(args[0].equalsIgnoreCase("group")) {
				if (args[2].equalsIgnoreCase("permission")) {
					if(args[3].equalsIgnoreCase("add")) {
						new CommandGroupPAdd(sender, this.pl, args[1], args[4]).execute();
					} else if(args[3].equalsIgnoreCase("remove")) {
						new CommandGroupPRemove(sender, this.pl, args[1], args[4]).execute();
					} else {
						new CommandUnknown(sender, this.pl).execute();
					}
				} else {
					new CommandUnknown(sender, this.pl).execute();
				}
			} else {
				new CommandUnknown(sender, this.pl).execute();
			}
		} else {
			new CommandUnknown(sender, this.pl).execute();
		}
		return true;
	}
}
