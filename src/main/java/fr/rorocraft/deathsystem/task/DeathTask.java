package fr.rorocraft.deathsystem.task;

import fr.rorocraft.deathsystem.DeathSystem;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathTask extends BukkitRunnable {
    private DeathSystem plugin;

    private int seconds = 0;
    private int timeStuck;

    private ArmorStand armorStand;

    private Player player;


    public DeathTask(DeathSystem plugin, Player player){
        this.plugin = plugin;
        this.armorStand = null;
        this.player = player;
        this.timeStuck = plugin.getConfig().getInt("time_stuck");

    }

    @Override
    public void run() {


        if(!player.isOnline() || player == null || !plugin.getDeathManager().containsPlayer(player) ) {
            if(armorStand != null) armorStand.remove();

            cancel();
        }



        if(seconds >= timeStuck){
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.setHealth(0);

            armorStand.remove();
            cancel();
        }

        if(seconds == 0){
            armorStand = player.getWorld().spawn(player.getLocation().add(0, 2, 0), ArmorStand.class);
            String name = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("death_tag"))
                .replaceAll("<player>", player.getName())
                .replace("<time_left>", String.valueOf(timeStuck - seconds));
            armorStand.setCustomName(name);
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);

            player.setSneaking(true);
            player.setFireTicks(20 * 60);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 *  60, 1 ));
        }



        String name = armorStand.getCustomName();
        int timeLeft = timeStuck - seconds;
        String name2 = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("death_tag"))
            .replaceAll("<player>", player.getName())
            .replace("<time_left>", String.valueOf(timeLeft));


        if(!name.equals(name2)){
            armorStand.setCustomName(name2);
        }



        seconds ++;


    }
}
