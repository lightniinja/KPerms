package com.lightniinja.kperms;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

	private KPermsPlugin pl = null;
	public PlayerEvents(KPermsPlugin pl) {
		this.pl = pl;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		KPlayer p = new KPlayer(e.getPlayer().getName(), this.pl);
		if(!p.isGenerated()) {
			p.make();
			this.pl.getLogger().info("Generated playerdata for " + e.getPlayer().getName());
		}
		p.assignPermissions();
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		KPlayer p = new KPlayer(e.getPlayer().getName(), this.pl);
		p.clearPermissions();
	}
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		KPlayer p = new KPlayer(e.getPlayer().getName(), this.pl);
		p.clearPermissions();
	}
	
}
