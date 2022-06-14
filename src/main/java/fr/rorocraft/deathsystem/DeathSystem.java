package fr.rorocraft.deathsystem;

import fr.rorocraft.deathsystem.event.DeathEvent;
import fr.rorocraft.deathsystem.manager.DeathManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathSystem extends JavaPlugin {
    private DeathManager deathManager;

    @Override
    public void onEnable() {

        registerEvents();

        this.saveDefaultConfig();

        this.deathManager = new DeathManager(this);

        System.out.println("[DeathSystem] Plugin is ready");
    }

    @Override
    public void onDisable() {
        System.out.println("[DeathSystem] Plugin is closed");
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
    }

    public DeathManager getDeathManager(){
        return deathManager;
    }
}
