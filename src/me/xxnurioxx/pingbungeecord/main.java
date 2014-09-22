package me.xxnurioxx.pingbungeecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class main extends Plugin {

	 private Configuration config;



	@Override
	    public void onEnable() {
	        // You should not put an enable message in your plugin.
	        // BungeeCord already does so
	        getLogger().info("[EnergyPing] Loading...");
	        
	        getLogger().info("[EnergyPing] Geting Config...");
	        if (!getDataFolder().exists()) {
		        getLogger().info("[EnergyPing] Making config folder...");
	            getDataFolder().mkdir();
		        getLogger().info("[EnergyPing] Config Folder ¡OK!");


	        }
	        File configFile = new File(getDataFolder(), "config.yml");
	        if (!configFile.exists()) {
	            try {
	                configFile.createNewFile();
	                try (InputStream is = getResourceAsStream("config.yml");
	                     OutputStream os = new FileOutputStream(configFile)) {
	                    ByteStreams.copy(is, os);
	                }
	            } catch (IOException e) {
	                throw new RuntimeException("Unable to create configuration file", e);
	            }
	        
	        
		        try {
					config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
					
		        } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        getLogger().info("[EnergyPing] Creating configuration files...");
		        config.set("DisableCommands.CMD_P", false);
		        config.set("DisableCommands.CMD_PING", false);
		        SaveConfig();
		        getLogger().info("[EnergyPing] Config Files ¡OK!");

	        }
	        getLogger().info("[EnergyPing] Loading CMD_P...");

	        if(config.getBoolean("DisableCommands.CMD_P") == true){
		        getLogger().info("[EnergyPing] Config File: CMD_P Disabled!");
	        }else if(config.getBoolean("DisableCommands.CMD_P") == false){
		        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand("p"));
		        getLogger().info("[EnergyPing] Config File: CMD_P Enabled!");
	        }else{
	        	 getLogger().info("[EnergyPing] Error! Only put: true/false");
	        }
	        
	        getLogger().info("[EnergyPing] Loading CMD_PING...");

	        if(config.getBoolean("DisableCommands.CMD_PING") == true){
		        getLogger().info("[EnergyPing] Config File: CMD_PING Disabled!");
	        }else if(config.getBoolean("DisableCommands.CMD_PING") == false){
		        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand("ping"));
		        getLogger().info("[EnergyPing] Config File: CMD_PING Enabled!");
	        }else{
	        	 getLogger().info("[EnergyPing] Error! Only put: true/false");
	        }

	        getLogger().info("[EnergyPing] Loaded!");
	    }
	 
	 
	 
	 public void SaveConfig(){
		 try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }	 
}
