package me.xxnurioxx.pingbungeecord;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {

	public PingCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(ChatColor.RED + "Only player can use this command..");
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if(!player.hasPermission("BungeePing.use")) {
			sender.sendMessage(ChatColor.RED+"You dont have permissions for this!");
			return;
		}

		String serverName = player.getServer().getInfo().getName();
		int Ping = player.getPing();
		
		if ( args.length < 1 )
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6To see the other player ping use:"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/ping <player> &6or &b/p <player>"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName() + "&f player has &6" + Ping + "ms"));

			return;
		}
		ProxiedPlayer user = ProxyServer.getInstance().getPlayer( args[0] );
		int PingTarget = user.getPing();

		if ( user == null )
		{
			sender.sendMessage(ChatColor.RED + "That user is not online");
			return;
		} 
		else
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + user.getName() + "&f player has &6" + PingTarget + "ms"));
			return;
		}
		


		

	}

}
