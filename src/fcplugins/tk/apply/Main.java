package fcplugins.tk.apply;

import org.bukkit.plugin.java.JavaPlugin;

import fcplugins.tk.apply.cache.Cache;
import fcplugins.tk.apply.cache.CacheLoader;
import fcplugins.tk.apply.commands.PunishCommand;
import fcplugins.tk.apply.config.BConfig;
import fcplugins.tk.apply.listener.ChatListener;
import fcplugins.tk.apply.objects.manager.PunishmentManager;

public class Main extends JavaPlugin {

	public PunishmentManager punishementManager;

	public BConfig data = new BConfig(this, "data.yml");

	@Override
	public void onEnable() {

		punishementManager = new PunishmentManager();

		loadConfigFiles();
		loadCommands();
		loadListener();

		new Cache();
		new CacheLoader().loadPunishments();

	}

	public void onDisable() {
		if (Cache.getPunishmentCache().size() != 0) {
			new CacheLoader().savePunishments();
		}
	}

	public void loadConfigFiles() {
		data.saveDefaultConfig();
	}

	public void loadListener() {
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
	}

	public void loadCommands() {
		getCommand("tempmute").setExecutor(new PunishCommand());
		getCommand("unmute").setExecutor(new PunishCommand());
	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
