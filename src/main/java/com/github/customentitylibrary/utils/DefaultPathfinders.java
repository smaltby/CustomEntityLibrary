package com.github.customentitylibrary.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.github.customentitylibrary.entities.EntityType;
import com.github.customentitylibrary.pathfinders.ClassEntitySelector;
import com.github.customentitylibrary.pathfinders.PathfinderCustomArrowAttack;
import com.github.customentitylibrary.pathfinders.PathfinderMoveToTarget;
import com.github.customentitylibrary.pathfinders.PathfinderTargetSelector;
import net.minecraft.server.v1_5_R2.*;

public class DefaultPathfinders
{
	/**
	 * Gets the default goal selectors of the entity. They are identical to those of normal entities, save the following changes:
	 * No pathfinders of overlapping priority.
	 * PathfinderGoalMeleeAttack and PathfinderGoalArrowAttack are both replaced by <br>
	 * <br>
	 * <code>
	 * if(type.useRanged())<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;pathfinders.put(4, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, 
	 * &nbsp;&nbsp;&nbsp;&nbsp;type.getRangedDelay(), [int], [int]));<br>
	 * if(type.useMelee())<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;pathfinders.put(5, new PathfinderGoalMeleeAttack(ent, speed, [bool]));<br>
	 * PathfinderMoveToTarget((EntityCreature) ent, speed));<br>
	 * </code>
	 * <br>
	 * All instances where a constant speed or range value is inputted are replaced by type.getSpeed() and type.getRange() respectively.<br>
	 * All instances where a non accesible field from the entity is required use reflection to get that field.
	 * @param ent	entity to get goal selectors for
	 * @param type	type of the entity
	 * @return		map of priorities to that priorities pathfinder
	 */
	public static Map<Integer, PathfinderGoal> getGoalSelectors(EntityLiving ent, EntityType type)
	{
		float speed = type.getSpeed();
		Map<Integer, PathfinderGoal> pathfinders = new HashMap<Integer, PathfinderGoal>();
		if(ent instanceof EntityChicken)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalPanic((EntityCreature) ent, speed * 1.52f));
	        pathfinders.put(2, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(3, new PathfinderGoalTempt((EntityCreature) ent, speed, Item.SEEDS.id, false));
	        pathfinders.put(4, new PathfinderGoalFollowParent((EntityAnimal) ent, speed * 1.12f));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityCow)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalPanic((EntityCreature) ent, speed * 1.52f));
	        pathfinders.put(2, new PathfinderGoalBreed((EntityAnimal) ent, speed * .8f));
	        pathfinders.put(3, new PathfinderGoalTempt((EntityCreature) ent, speed, Item.WHEAT.id, false));
	        pathfinders.put(4, new PathfinderGoalFollowParent((EntityAnimal) ent, speed));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed * .8f));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityCreeper)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, new PathfinderGoalSwell((EntityCreeper) ent));
	        pathfinders.put(3, new PathfinderGoalAvoidPlayer((EntityCreature) ent, EntityOcelot.class, 6.0F, speed, 0.3F));
	        if(type.useRanged())
	        	pathfinders.put(4, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(5, new PathfinderGoalMeleeAttack(ent, speed, false));
	        pathfinders.put(6, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(7, new PathfinderGoalRandomStroll((EntityCreature) ent, speed * .8f));
	        pathfinders.put(8, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(9, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityIronGolem)
		{
			if(type.useRanged())
	        	pathfinders.put(1, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(2, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(3, new PathfinderGoalMoveTowardsTarget((EntityCreature) ent, speed, 32.0F));
	        pathfinders.put(4, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(5, new PathfinderGoalMoveThroughVillage((EntityCreature) ent, speed * .727f, true));
	        pathfinders.put(6, new PathfinderGoalMoveTowardsRestriction((EntityCreature) ent, speed * .727f));
	        pathfinders.put(7, new PathfinderGoalOfferFlower((EntityIronGolem) ent));
	        pathfinders.put(8, new PathfinderGoalRandomStroll((EntityCreature) ent, speed * .727f));
	        pathfinders.put(9, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(10, new PathfinderGoalRandomLookaround(ent));
			if(ent instanceof EntitySnowman)
			{
				if(type.useRanged())
		        	pathfinders.put(1, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 20, 4));
		        if(type.useMelee())
		        	pathfinders.put(2, new PathfinderGoalMeleeAttack(ent, speed, true));
			}
		} else if(ent instanceof EntityOcelot)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, (PathfinderGoal) getField(ent, NMS.ANIMAL_GOALSELECTOR2));
	        pathfinders.put(3, (PathfinderGoal) setField(ent, NMS.OCELOT_TEMPT, new PathfinderGoalTempt((EntityCreature) ent, 0.18F, Item.RAW_FISH.id, true)));
	        pathfinders.put(4, new PathfinderGoalAvoidPlayer((EntityCreature) ent, EntityHuman.class, 16.0F, speed, 0.4F));
	        pathfinders.put(5, new PathfinderGoalFollowOwner((EntityTameableAnimal) ent, speed * 1.3f, 10.0F, 5.0F));
	        pathfinders.put(6, new PathfinderGoalJumpOnBlock((EntityOcelot) ent, speed * 1.74f));
	        pathfinders.put(7, new PathfinderGoalLeapAtTarget(ent, speed * 1.3f));
	        pathfinders.put(8, new PathfinderGoalOcelotAttack(ent));
	        pathfinders.put(9, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(10, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(11, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(12, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 10.0F));
		} else if(ent instanceof EntityPig)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalPanic((EntityCreature) ent, speed * 1.52f));
	        pathfinders.put(2, (PathfinderGoal) setField(ent, NMS.ANIMAL_GOALSELECTOR2, new PathfinderGoalPassengerCarrotStick(ent, speed * 1.36f)));
	        pathfinders.put(3, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(4, new PathfinderGoalTempt((EntityCreature) ent, speed * 1.2f, Item.CARROT_STICK.id, false));
	        pathfinders.put(5, new PathfinderGoalTempt((EntityCreature) ent, speed * 1.2f, Item.CARROT.id, false));
	        pathfinders.put(6, new PathfinderGoalFollowParent((EntityAnimal) ent, speed * 1.12f));
	        pathfinders.put(7, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(8, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(9, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntitySheep)
		{
	        pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalPanic((EntityCreature) ent, speed * 1.652f));
	        pathfinders.put(2, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(3, new PathfinderGoalTempt((EntityCreature) ent, speed * 1.087f, Item.WHEAT.id, false));
	        pathfinders.put(4, new PathfinderGoalFollowParent((EntityAnimal) ent, speed * 1.087f));
	        pathfinders.put(5, (PathfinderGoal) getField(ent, NMS.SHEEP_EAT_TILE));
	        pathfinders.put(6, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(7, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(8, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntitySkeleton)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, new PathfinderGoalRestrictSun((EntityCreature) ent));
	        pathfinders.put(3, new PathfinderGoalFleeSun((EntityCreature) ent, speed));
	        if(type.useRanged())
	        	pathfinders.put(4, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(5, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(6, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(7, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(8, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(9, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWitch)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
			if(type.useRanged())
	        	pathfinders.put(2, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(3, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(4, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWither)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
			if(type.useRanged())
	        	pathfinders.put(1, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 20, 4));
	        if(type.useMelee())
	        	pathfinders.put(2, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(3, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWolf)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, (PathfinderGoal) getField(ent, NMS.ANIMAL_GOALSELECTOR2));
	        pathfinders.put(3, new PathfinderGoalLeapAtTarget(ent, 0.4F));
	        if(type.useRanged())
	        	pathfinders.put(4, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(5, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(6, new PathfinderGoalFollowOwner((EntityTameableAnimal) ent, speed, 10.0F, 2.0F));
	        pathfinders.put(7, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(8, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(9, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(10, new PathfinderGoalBeg((EntityWolf) ent, 8.0F));
	        pathfinders.put(11, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(12, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityZombie)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalBreakDoor(ent));
	        if(type.useRanged())
	        	pathfinders.put(2, new PathfinderCustomArrowAttack((IRangedEntity) ent, speed, type.getRangedDelay(), 16, 4));
	        if(type.useMelee())
	        	pathfinders.put(3, new PathfinderGoalMeleeAttack(ent, speed, false));
	        pathfinders.put(4, new PathfinderMoveToTarget((EntityCreature) ent, speed));
	        pathfinders.put(5, new PathfinderGoalMoveTowardsRestriction((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalMoveThroughVillage((EntityCreature) ent, speed, false));
	        pathfinders.put(7, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(8, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(9, new PathfinderGoalRandomLookaround(ent));
		}
		return pathfinders;
	}
	
	/**
	 * Gets the default target selectors of the entity. They are identical to those of normal entities, save the following changes:<br>
	 * No pathfinders of overlapping priority.<br>
	 * All instances of PathfinderGoalNearestAttackableTarget are replaced by PathfinderTargetSelector.<br>
	 * All instances where a constant speed or range value is inputted are replaced by type.getSpeed() and type.getRange() respectively.<br>
	 * All instances where a non accesible field from the entity is required use reflection to get that field.<br>
	 * @param ent	entity to get target selectors for
	 * @param type	type of the entity
	 * @return		map of priorities to that priorities pathfinder
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, PathfinderGoal> getTargetSelectors(EntityLiving ent, EntityType type)
	{
		float range = type.getRange();
		Map<Integer, PathfinderGoal> pathfinders = new HashMap<Integer, PathfinderGoal>();
		if(ent instanceof EntityChicken)
		{
			
		} else if(ent instanceof EntityCow)
		{
			
		} else if(ent instanceof EntityCreeper)
		{
	        pathfinders.put(1, new PathfinderTargetSelector((EntityCreature) ent, new ClassEntitySelector(EntityHuman.class), range));
	        pathfinders.put(2, new PathfinderGoalHurtByTarget(ent, false));
		} else if(ent instanceof EntityIronGolem)
		{
	        pathfinders.put(1, new PathfinderGoalDefendVillage((EntityIronGolem) ent));
	        pathfinders.put(2, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(3, new PathfinderTargetSelector((EntityCreature) ent, IMonster.a, range));
			if(ent instanceof EntitySnowman)
			{
				pathfinders.put(1, new PathfinderTargetSelector((EntityCreature) ent, IMonster.a, range));
			}
		} else if(ent instanceof EntityOcelot)
		{
	        pathfinders.put(1, new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal) ent, EntityChicken.class, range, 750, false));
		} else if(ent instanceof EntityPig)
		{
			
		} else if(ent instanceof EntitySheep)
		{
			
		} else if(ent instanceof EntitySkeleton)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderTargetSelector((EntityCreature) ent, new ClassEntitySelector(EntityHuman.class), range));
		} else if(ent instanceof EntityWitch)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderTargetSelector((EntityCreature) ent, new ClassEntitySelector(EntityHuman.class), range));
		} else if(ent instanceof EntityWither)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderTargetSelector((EntityCreature) ent, (IEntitySelector) getField(ent, NMS.WITHER_SELECTOR), range));
		} else if(ent instanceof EntityWolf)
		{
	        pathfinders.put(1, new PathfinderGoalOwnerHurtByTarget((EntityTameableAnimal) ent));
	        pathfinders.put(2, new PathfinderGoalOwnerHurtTarget((EntityTameableAnimal) ent));
	        pathfinders.put(3, new PathfinderGoalHurtByTarget(ent, true));
	        pathfinders.put(4, new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal) ent, EntitySheep.class, range, 200, false));
		} else if(ent instanceof EntityZombie)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, true));
	        pathfinders.put(2, new PathfinderTargetSelector((EntityCreature) ent, new ClassEntitySelector(EntityHuman.class, EntityVillager.class), range));
		}
		return pathfinders;
	}
	
	private static Object getField(EntityLiving ent, String name)
	{
		try
		{
			Field f = ent.getClass().getDeclaredField(name);
			f.setAccessible(true);
			return f.get(ent);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private static Object setField(EntityLiving ent, String name, Object value)
	{
		try
		{
			Field f = ent.getClass().getDeclaredField(name);
			f.setAccessible(true);
			f.set(ent, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}
}
