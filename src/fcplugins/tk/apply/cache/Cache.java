package fcplugins.tk.apply.cache;

import java.util.HashMap;
import java.util.UUID;

import fcplugins.tk.apply.objects.Punishment;

public class Cache {

	private static HashMap<UUID, Punishment> punishmentCache;

	public Cache() {
		punishmentCache = new HashMap<UUID, Punishment>();
	}

	public static HashMap<UUID, Punishment> getPunishmentCache() {
		return punishmentCache;
	}

}
