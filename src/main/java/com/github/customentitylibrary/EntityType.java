package main.java.com.github.customentitylibrary;

import java.util.List;
import java.util.Map;

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
	
	public Map<Integer, PathfinderGoal> getGoalSelectors();
	
	public int getHealth();
	
	public List<DamageCause> getImmunities();
	
	public ItemStack[] getItems();
	
	public int getRange();
	
	public float getSpeed();
	
	public Map<Integer, PathfinderGoal> getTargetSelectors();
	
	public double getWorthModifier();
	
	public boolean isWither();
	
	public void showSpecialEffects(LivingEntity entity);
	
	public String toString();
}
