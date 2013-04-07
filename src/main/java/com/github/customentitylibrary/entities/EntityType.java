package com.github.customentitylibrary.entities;

import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.PathfinderGoal;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public interface EntityType
{
	public void dealEffects(Player player, Location source);
	
	public int getArmorPiercingDamage();
	
	public int getDamage();
	
	public Map<Integer, PathfinderGoal> getGoalSelectors(EntityLiving ent, EntityType type);
	
	public int getMaxHealth();
	
	public List<DamageCause> getImmunities();
	
	public ItemStack[] getItems();
	
	public float getRange();
	
	public int getRangedDelay();
	
	public int getRangedType();
	
	public float getSpeed();
	
	public Map<Integer, PathfinderGoal> getTargetSelectors(EntityLiving ent, EntityType type);
	
	public boolean isWither();
	
	public void showSpecialEffects(LivingEntity entity);
	
	public String toString();
	
	public boolean useMelee();
	
	public boolean useRanged();
}
