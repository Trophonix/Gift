package main.java.com.trophonix.gift;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		try {
			init();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onDisable() {

	}

	public void init() {
		getCommand("gift").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be executed by a player!");
			return true;
		}
		Player gifter = (Player) sender;

		if (args.length > 0) {
			Player receiver;
			try {
				receiver = Bukkit.getPlayer(args[0]);
			} catch (NullPointerException ex) {
				gifter.sendMessage(ChatColor.RED + "Cannot locate player with name " + args[0]);
				return true;
			}

			// Add 100 (or whatever amount) coins to receiver's balance
			// Remove 100 (or whatever amount) coins from gifter's balance

			gifter.sendMessage(ChatColor.YELLOW + "You sent a gift to " + args[0]);
			
			String msg = ChatColor.YELLOW + "You were sent a gift by " + gifter.getName() + "!";
			String note = "";
			if (args.length > 1) {
				msg = msg + " It had a note attached!";
				for (int i = 1; i < args.length; i++) {
					note = note + args[i] + " ";
				}
				receiver.sendMessage(msg);
				receiver.sendMessage(ChatColor.GOLD + note);
				return true;
			}

			receiver.sendMessage(msg);
			return true;
		}

		gifter.sendMessage(ChatColor.RED + "Invalid command! Usage: /gift <player> [note]");
		return true;
	}

}
