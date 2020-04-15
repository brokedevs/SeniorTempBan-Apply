package fcplugins.tk.apply.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fcplugins.tk.apply.Main;
import fcplugins.tk.apply.objects.Punishment;
import fcplugins.tk.apply.utils.TimeUtils;

public class PunishCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if (s instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("tempmute")) {
				if (!s.hasPermission("punish.punish")) {
					s.sendMessage("§cYou dont have permissions!");
					return true;
				}

				if (args.length != 2) {
					s.sendMessage("§cCorrect use §f/tempmute (player) (time)");
					return false;
				}

				final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				int time;

				if (target == null) {
					s.sendMessage("§cCould not find §f" + args[0] + "§c online.");
					return true;
				}

				try {
					time = Integer.parseInt(args[1]);
				} catch (Exception e) {
					s.sendMessage("§cPlease use a valid number.");
					return true;
				}
				time = TimeUtils.toSeconds(args[1]);

				if (Main.getInstance().punishementManager.hasPunishment(target.getUniqueId())) {
					s.sendMessage("§cPlayer §f" + target.getName() + " §cis already tempmuted.");
					return true;
				}

				final int expireTime = (time * 1000);
				final Punishment punish = new Punishment(target.getUniqueId(), System.currentTimeMillis() + expireTime);
				Main.getInstance().punishementManager.punishPlayer(punish, true);

				Bukkit.broadcastMessage("§f" + target.getName() + "§c was tempmuted by §f" + s.getName());
			}

			if (cmd.getName().equalsIgnoreCase("unmute")) {
				if (!s.hasPermission("punish.unpunish")) {
					s.sendMessage("§cYou dont have permissions!");
					return true;
				}

				if (args.length != 1) {
					s.sendMessage("§cCorrect use §f/unmute (player)");
					return false;
				}

				final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

				if (target == null) {
					s.sendMessage("§cCould not find §f" + args[0] + "§c online.");
					return true;
				}

				if (!Main.getInstance().punishementManager.hasPunishment(target.getUniqueId())) {
					s.sendMessage("§f" + target.getName() + " §cis not tempmuted.");
					return true;
				}

				Main.getInstance().punishementManager.getPunishment(target.getUniqueId())
						.setTime(System.currentTimeMillis());
				Bukkit.broadcastMessage("§f" + target.getName() + "§c was unmuted by §f" + s.getName());
			}
		}
		return false;
	}
}
