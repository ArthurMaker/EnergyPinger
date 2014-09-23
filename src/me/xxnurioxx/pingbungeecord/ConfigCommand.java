package me.xxnurioxx.pingbungeecord;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigCommand extends Command {
	private static Configuration config;


	public ConfigCommand(String name) {
		super(name);
	}
   
	
	
	@Override
	public void execute(CommandSender sender, String[] args) {
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
		if(!player.hasPermission("BungeePing.config")) {
			sender.sendMessage(ChatColor.RED+"You dont have permissions for this!");
			return;
		}
		
		if ( args.length < 1 )
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6EnergyPinger Config v 0.3"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&m---------------------------------"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/EnergyConfig lang <ES|EN>"));

			return;
		}
		if ( args.length > 1 )
		{
			if(args[0].equalsIgnoreCase("lang")){
				
				if(args[1].equalsIgnoreCase("ES")){
				config.set("Lang", "ES");
				SaveConfig();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin configurado al español con exito!"));
				return;
				}
				
				if(args[1].equalsIgnoreCase("EN")){
				config.set("Lang", "EN");
				SaveConfig();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin successfully configured to English!"));
				return;
				}
				
				if(!args[1].equalsIgnoreCase("ES") || !args[1].equalsIgnoreCase("EN") || !args[0].equalsIgnoreCase("lang")){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError in command syntax!"));
					return;
				}
			
			
			
			}
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
