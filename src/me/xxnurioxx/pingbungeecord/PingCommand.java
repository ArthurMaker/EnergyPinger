package me.xxnurioxx.pingbungeecord;

import java.io.IOException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class PingCommand extends Command {

	private static Configuration config;

	public PingCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String ES = "ES";
		String EN = "EN";
		
		try {
				config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(main.configFile);
				
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(ChatColor.RED + "Only player can use this command..");
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if(!player.hasPermission("BungeePing.use")) {
			if(config.getString("Lang").toString().toString().equalsIgnoreCase(ES)){
			sender.sendMessage(ChatColor.RED+"You dont have permissions for this!");
			}
			if(config.getString("Lang").toString().equalsIgnoreCase(ES)){
			sender.sendMessage(ChatColor.RED+"No tienes permisos para esto!");
			}
			return;
		}

		String serverName = player.getServer().getInfo().getName();
		int Ping = player.getPing();
		
		if ( args.length < 1 )
		{
			
			if(config.getString("Lang").toString().equalsIgnoreCase(EN)){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6To see the other player ping use:"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/ping <player> &6or &b/p <player>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName() + "&f tiene &6" + Ping + "ms"));
			}
			if(config.getString("Lang").toString().equalsIgnoreCase(ES)){

				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Para ver el ping de otro jugador usa:"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/ping <nick> &6o &b/p <nick>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + player.getName() + "&f tiene &6" + Ping + "ms"));
				
			}
			return;
		}
		ProxiedPlayer user = ProxyServer.getInstance().getPlayer( args[0] );
		int PingTarget = user.getPing();

		if ( user == null )
		{
			if(config.getString("Lang").toString().equalsIgnoreCase(ES)){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cJugador no encontrado"));
			}
			if(config.getString("Lang").toString().equalsIgnoreCase(EN)){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat user is not online"));
			}
			return;
		} 
		else
		{
			if(config.getString("Lang").toString().equalsIgnoreCase(EN)){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + user.getName() + "&f player has &6" + PingTarget + "ms"));
			}
			if(config.getString("Lang").toString().equalsIgnoreCase(ES)){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + user.getName() + "&f tiene &6" + PingTarget + "ms"));

			}
			return;
		}
		


		

	}
	
	
	 public static void SaveConfig(){
		 try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, main.configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }	

}
