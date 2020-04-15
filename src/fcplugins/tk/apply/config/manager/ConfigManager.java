package fcplugins.tk.apply.config.manager;

import java.util.List;
import java.util.stream.Collectors;

import fcplugins.tk.apply.Main;
import fcplugins.tk.apply.cache.Cache;
import fcplugins.tk.apply.objects.Punishment;

public class ConfigManager {

	public static void savePunishment(final Punishment punishedPlayer) {
		Main.getInstance().data.set("Punishments." + punishedPlayer.getUuid().toString() + ".Time",
				punishedPlayer.getTime());
		Main.getInstance().data.saveConfig();
	}

	public static void deletePunishment(final Punishment punishedPlayer) {
		Main.getInstance().data.set("Punishments." + punishedPlayer.getUuid().toString(), null);
		Main.getInstance().data.saveConfig();
	}

	public static List<Punishment> getAll() {
		return Cache.getPunishmentCache().values().stream().collect(Collectors.toList());
	}

}
