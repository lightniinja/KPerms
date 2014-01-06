package com.lightniinja.kperms;

public class ConfigManager {
	private KPermsPlugin pl;
	public ConfigManager(KPermsPlugin pl) {
		this.pl = pl;
	}
	public String getDefaultGroup() {
		return this.pl.getConfig().getString("defaultGroup");
	}
	public String getMessage(String id) {
		if(this.pl.getConfig().getString("messages." + id) == null) {
			return new Utilities(this.pl).format("&cLanguage values not completed - check config.yml");
		}
		return new Utilities(this.pl).format(this.pl.getConfig().getString("messages." + id));
	}
}
