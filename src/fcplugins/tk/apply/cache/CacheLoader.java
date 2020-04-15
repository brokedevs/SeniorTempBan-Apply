package fcplugins.tk.apply.cache;

import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;

import fcplugins.tk.apply.Main;
import fcplugins.tk.apply.config.manager.ConfigManager;
import fcplugins.tk.apply.objects.Punishment;

public class CacheLoader {

	public void loadPunishments() {
		ConfigurationSection section = Main.getInstance().data.getConfig().getConfigurationSection("Punishments");
		if (section != null) {
			for (String punishedPlayer : section.getKeys(false)) {
				Cache.getPunishmentCache().put(UUID.fromString(punishedPlayer),
						new Punishment(UUID.fromString(punishedPlayer), Long.parseUnsignedLong(
								Main.getInstance().data.getString("Punishments." + punishedPlayer + ".Time"))));
			}
		}
	}

	public void savePunishments() {
		for (Punishment punishedPlayer : ConfigManager.getAll()) {
			ConfigManager.savePunishment(punishedPlayer);
		}
	}
}