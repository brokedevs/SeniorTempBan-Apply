package fcplugins.tk.apply.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fcplugins.tk.apply.Main;
import fcplugins.tk.apply.objects.Punishment;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (Main.getInstance().punishementManager.getPunishment(p.getUniqueId()) != null) {
			if (!Main.getInstance().punishementManager.getPunishment(p.getUniqueId()).isExpired()) {
				e.setCancelled(true);
				p.sendMessage("§cYou can't talk while tempmuted, wait §f" + Punishment
						.getFormatedTime(Main.getInstance().punishementManager.getPunishment(p.getUniqueId()).getTime()
								- System.currentTimeMillis()) + "§c to talk again!");
				return;
			} else {
				Main.getInstance().punishementManager
						.punishPlayer(Main.getInstance().punishementManager.getPunishment(p.getUniqueId()), false);
				p.sendMessage("§aYour tempmute is now over.");
			}
		}
	}
}
