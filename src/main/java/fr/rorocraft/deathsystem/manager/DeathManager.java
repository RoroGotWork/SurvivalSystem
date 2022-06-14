package fr.rorocraft.deathsystem.manager;

import fr.rorocraft.deathsystem.DeathSystem;
import fr.rorocraft.deathsystem.task.DeathTask;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class DeathManager {
    private List<Player> players;

    private DeathSystem plugin;

    public DeathManager(DeathSystem plugin) {
        this.players = new ArrayList<>();

        this.plugin = plugin;

    }

    public void addPlayer(Player player){
        players.add(player);

        new DeathTask(plugin, player).runTaskTimer(plugin, 20, 20);
    }

    public void removePlayer(Player player){
        players.remove(player);

        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.setHealth(20);
        player.setFireTicks(0);

    }

    public boolean containsPlayer(Player player){
        return players.contains(player);
    }


}
