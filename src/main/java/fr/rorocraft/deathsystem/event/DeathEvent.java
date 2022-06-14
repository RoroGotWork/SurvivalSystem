package fr.rorocraft.deathsystem.event;

import fr.rorocraft.deathsystem.DeathSystem;
import fr.rorocraft.deathsystem.manager.DeathManager;
import fr.rorocraft.deathsystem.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;


public class DeathEvent implements Listener {
    private DeathSystem plugin;

    public DeathEvent(DeathSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        double newHealth = player.getHealth() - event.getFinalDamage();


        if(newHealth <= 0){
            player.setHealth(0.5);


            DeathManager deathManager = plugin.getDeathManager();

            if(!deathManager.containsPlayer(player)){
                deathManager.addPlayer(player);

            }

            if(!Arrays.asList(Utils.CAUSES).contains(event.getCause())){
                event.setCancelled(true);
            }

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        if(event.getHand() == EquipmentSlot.OFF_HAND) return;
        Player player = event.getPlayer();

        if(!(event.getRightClicked() instanceof  Player)) return;

        Player deadPlayer = (Player) event.getRightClicked();

        if(!plugin.getDeathManager().containsPlayer(deadPlayer)) return;

        Material material = Material.matchMaterial(plugin.getConfig().getString("item"));


        if(player.getInventory().getItemInMainHand().getType() == material){
            player.getInventory().removeItem(new ItemStack(material, 1));

            plugin.getDeathManager().removePlayer(deadPlayer);

            double health = plugin.getConfig().getDouble("health_on_revival");
            deadPlayer.setHealth(health);
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(plugin.getDeathManager().containsPlayer(player)){
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(plugin.getDeathManager().containsPlayer(player)){
            plugin.getDeathManager().removePlayer(player);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();

        if(plugin.getDeathManager().containsPlayer(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event){
        Player player = event.getPlayer();

        if(plugin.getDeathManager().containsPlayer(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();

        if(plugin.getDeathManager().containsPlayer(player)){
            event.setCancelled(true);
        }
    }




}
