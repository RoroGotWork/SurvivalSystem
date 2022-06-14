package fr.rorocraft.deathsystem.utils;

import org.bukkit.event.entity.EntityDamageEvent;

public class Utils {

    public static final EntityDamageEvent.DamageCause[] CAUSES = new EntityDamageEvent.DamageCause[]{
            EntityDamageEvent.DamageCause.ENTITY_ATTACK, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK,
            EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
    };
}
