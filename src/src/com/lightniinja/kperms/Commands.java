package com.lightniinja.kperms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private KPerms plugin;
	
	public Commands(KPerms plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			if(sender.hasPermission("kperms.help")) {
				displayHelpMessage(sender);
			} else {
				displayNoPerms(sender);
			}
		} else if (args.length == 1) {
			 if(args[0].equalsIgnoreCase("help")) {
				 if(sender.hasPermission("kperms.help")) {
						displayHelpMessage(sender);
					} else {
						displayNoPerms(sender);
					}
			 } else if(args[0].equalsIgnoreCase("reload")) {
				 if(sender.hasPermission("kperms.reload")) {
					 plugin.reloadPermissions();
					 sender.sendMessage(ChatColor.BLUE + "KPerms: " + ChatColor.WHITE + "Permissions where reloaded.");
				 } else {
					 displayNoPerms(sender);
				 }
			 } else {
				 sender.sendMessage(ChatColor.BLUE + "KPerms: " + ChatColor.WHITE + "Unknown command.");
			 }
		} else if (args.length == 3) {
			if(args[0].equalsIgnoreCase("setgroup")) {
				if(sender.hasPermission("kperms.setgroup")) {
					String player = args[1];
					String group = args[2];
					if(Bukkit.getPlayerExact(player) != null) {
						Player p = Bukkit.getPlayerExact(player);
						for(String perm: plugin.getGroupPermissions(plugin.getConfig().getString("player." + player))) {
							if(perm.startsWith("-")) {
								p.addAttachment(plugin, perm.replace("-", ""), true);
							} else {
								p.addAttachment(plugin, perm, false);
							}
						}
						plugin.setGroup(p, group);
					}
					plugin.getConfig().set("player." + player, group);
					plugin.saveConfig();
				} else {
					displayNoPerms(sender);
				}
			} else {
				sender.sendMessage(ChatColor.BLUE + "KPerms: " + ChatColor.WHITE + "Unknown command.");
			}
		} else {
			 sender.sendMessage(ChatColor.BLUE + "KPerms: " + ChatColor.WHITE + "Unknown command.");
		}
		return true;
	}

	private void displayHelpMessage(CommandSender s) {
		s.sendMessage(ChatColor.BLUE + "================ KPERMS ================");
		s.sendMessage(ChatColor.WHITE + "help: " + ChatColor.AQUA + "Show this message");
		s.sendMessage(ChatColor.WHITE + "reload: " + ChatColor.AQUA + "Reload permissions");
		s.sendMessage(ChatColor.WHITE + "setgroup [player] [group]: " + ChatColor.AQUA + "Sets the players group");
		s.sendMessage(ChatColor.BLUE + "================ KPERMS ================");
	}
	
	public void displayNoPerms(CommandSender s) {
		s.sendMessage(ChatColor.BLUE + "KPerms: " + ChatColor.WHITE + " You don't have permission to do that.");
	}

}
