package com.github.customentitylibrary.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.customentitylibrary.utils.Utils;

import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.PathfinderGoal;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class EntityTypeConfiguration implements EntityType
{
	FileConfiguration config;
	String type;
	String name;
	int damage;
	int armorPierce;
	int health;
	double healthModifier;
	float range;
	float speed;
	List<DamageCause> immunities = new ArrayList<DamageCause>();
	ItemStack[] items = new ItemStack[5];
	int minimumSpawnWave;
	double spawnChance;
	int shootDelay;
	boolean melee;
	boolean ranged;
	boolean isWither;
	int rangedDelay;
	String rangedType;
	String skinURL;
	List<DamageEffect> damageEffects = new ArrayList<DamageEffect>();
	List<SpecialEffect> specialEffects = new ArrayList<SpecialEffect>();
	
	public EntityTypeConfiguration(FileConfiguration config)
	{
		this.config = config;
		type = config.getString("Type", "Zombie");
		name = config.getString("Name", type);
		damage = config.getInt("Damage", 2);
		armorPierce = config.getInt("Armor Piercing", 0);
		health = config.getInt("Max Health", 10);
		healthModifier = config.getDouble("HealthModifier", 1);
		range = (float) config.getDouble("Range", 16);
		speed = (float) config.getDouble("Speed", .23);
		minimumSpawnWave = config.getInt("MinimumSpawnWave", 1);
		spawnChance = config.getDouble("SpawnChance", .05);
		shootDelay = config.getInt("ShootDelay", 60);
		isWither = config.getBoolean("Wither", false);
		melee = config.getBoolean("Use Melee", true);
		ranged = config.getBoolean("Use Ranged", type.equalsIgnoreCase("skeleton") && !isWither);
		rangedType = config.getString("Ranged Attack Type", "Arrow");
		rangedDelay = config.getInt("Ranged Attack Delay", 60);
		skinURL = config.getString("Skin URL", (type.equalsIgnoreCase("skeleton")) ? "http://i.imgur.com/L2Zy5.png" : (type.equalsIgnoreCase("wolf")) ? "http://i.imgur.com/9Iimp.png" : "http://i.imgur.com/XJuFX.png");
		if(config.getStringList("Immunities") != null)
		{
			for(String damageCauseName : config.getStringList("Immunities"))
			{
				DamageCause damageCause = DamageCause.valueOf(damageCauseName.toUpperCase().replaceAll(" ", "_"));
				if(damageCause != null)
					immunities.add(damageCause);
			}
		}
		items[0] = new ItemStack(Material.valueOf(config.getString("Weapon", Material.AIR.toString()).toUpperCase().replaceAll(" ", "_")).getId());
		int index = 1;
		if(config.getStringList("Armor") != null)
		{
			for(String itemName : config.getStringList("Armor"))
			{
				Material material = Material.getMaterial(itemName.toUpperCase().replaceAll(" ", "_"));
				if(material != null)
					items[index] = new ItemStack(material.getId());
				index++;
				if(index > 4)
					break;
			}
		}
		if(config.getStringList("DamageEffects") != null)
		{
			for(String effectName : config.getStringList("DamageEffects"))
			{
				String[] args = Utils.getConfigArgs(effectName);
				DamageEffect damageEffect = new DamageEffect(effectName.split("\\s")[0], args);
				damageEffects.add(damageEffect);
			}
		}
		if(config.getStringList("SpecialEffects") != null)
		{
			for(String effectName : config.getStringList("SpecialEffects"))
			{
				String[] args = Utils.getConfigArgs(effectName);
				SpecialEffect specialEffect = new SpecialEffect(effectName.split("\\s")[0], args);
				specialEffects.add(specialEffect);
			}
		}
	}
	
	@Override
	public int getDamage()
	{
		return damage;
	}
	
	@Override
	public int getArmorPiercingDamage()
	{
		return armorPierce;
	}

	@Override
	public int getMaxHealth()
	{
		return health;
	}
	
	public double getHealthModifier()
	{
		return healthModifier;
	}
	
	@Override
	public float getRange()
	{
		return range;
	}
	
	public boolean useMelee()
	{
		return melee;
	}
	
	public boolean useRanged()
	{
		return ranged;
	}
	
	public String getRangedAttackType()
	{
		return rangedType;
	}

	@Override
	public float getSpeed()
	{
		return speed;
	}
	
	public String getType()
	{
		return type.toLowerCase();
	}
	
	public String getName()
	{
		return name;
	}

	@Override
	public List<DamageCause> getImmunities()
	{
		return immunities;
	}

	@Override
	public ItemStack[] getItems()
	{
		return items;
	}
	
	public int getMinimumSpawnWave()
	{
		return minimumSpawnWave;
	}
	
	public String getSkinURL()
	{
		return skinURL;
	}
	
	public double getSpawnChance()
	{
		return spawnChance;
	}
	
	public int getShootDelay()
	{
		return shootDelay;
	}
	
	@Override
	public void dealEffects(Player player, Location location)
	{
		if(((CraftPlayer) player).getHandle().hurtTicks > 0)
			return;
		for(DamageEffect effect : damageEffects)
		{
			effect.dealEffect(player, location);
		}
	}

	@Override
	public void showSpecialEffects(LivingEntity entity)
	{
		for(SpecialEffect effect : specialEffects)
		{
			effect.showEffect(entity);
		}
	}

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public Map<Integer, PathfinderGoal> getGoalSelectors(EntityLiving ent, EntityType type)
	{
		return null;
	}

	@Override
	public Map<Integer, PathfinderGoal> getTargetSelectors(EntityLiving ent, EntityType type)
	{
		return null;
	}

	@Override
	public boolean isWither()
	{
		return isWither;
	}

	@Override
	public int getRangedType()
	{
		return Material.getMaterial(rangedType).getId();
	}

	@Override
	public int getRangedDelay()
	{
		return rangedDelay;
	}
}
