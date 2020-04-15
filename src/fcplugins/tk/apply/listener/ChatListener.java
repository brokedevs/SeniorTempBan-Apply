package fcplugins.tk.apply.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fcplugins.tk.apply.Main;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (!Main.getInstance().punishementManager.getPunishment(p.getUniqueId()).isExpired()) {
			e.setCancelled(true);
			p.sendMessage("§cYou can't talk while tempmuted, wait untill your mute expire.");
			return;
		}

		Main.getInstance().punishementManager
				.punishPlayer(Main.getInstance().punishementManager.getPunishment(p.getUniqueId()), false);
		p.sendMessage("§aYour tempmute is finished.");

	}

}
