package fcplugins.tk.apply.objects.manager;

import java.util.Collection;
import java.util.UUID;

import fcplugins.tk.apply.cache.Cache;
import fcplugins.tk.apply.config.manager.ConfigManager;
import fcplugins.tk.apply.objects.Punishment;

public class PunishmentManager {

	public void punishPlayer(final Punishment punishedPlayer, final boolean status) { // defining punishment status
		if (status) {
			Cache.getPunishmentCache().put(punishedPlayer.getUuid(), punishedPlayer);
		} else {
			Cache.getPunishmentCache().remove(punishedPlayer.getUuid());
			ConfigManager.deletePunishment(punishedPlayer);
		}
	}

	public Punishment getPunishment(final UUID uuid) { // getting punishment
		return Cache.getPunishmentCache().get(uuid);
	}

	public boolean hasPunishment(final UUID uuid) { // cheking if has punishment
		return Cache.getPunishmentCache().containsKey(uuid) && Cache.getPunishmentCache().get(uuid) != null;
	}

	public Collection<Punishment> getPunishments() { // get all punishments
		return Cache.getPunishmentCache().values();
	}

}
