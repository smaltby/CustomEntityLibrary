package main.java.com.github.customentitylibrary.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import net.minecraft.server.v1_5_R2.*;

public class DefaultPathfinders
{
	public static Map<Integer, PathfinderGoal> getGoalSelectors(EntityLiving ent, float speed)
	{
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
	        pathfinders.put(3, new PathfinderGoalAvoidPlayer((EntityCreature) ent, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
	        pathfinders.put(4, new PathfinderGoalMeleeAttack(ent, 0.25F, false));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, 0.2F));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(6, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityIronGolem)
		{
			pathfinders.put(1, new PathfinderGoalMeleeAttack(ent, 0.25F, true));
	        pathfinders.put(2, new PathfinderGoalMoveTowardsTarget((EntityCreature) ent, 0.22F, 32.0F));
	        pathfinders.put(3, new PathfinderGoalMoveThroughVillage((EntityCreature) ent, 0.16F, true));
	        pathfinders.put(4, new PathfinderGoalMoveTowardsRestriction((EntityCreature) ent, 0.16F));
	        pathfinders.put(5, new PathfinderGoalOfferFlower((EntityIronGolem) ent));
	        pathfinders.put(6, new PathfinderGoalRandomStroll((EntityCreature) ent, 0.16F));
	        pathfinders.put(7, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(8, new PathfinderGoalRandomLookaround(ent));
			if(ent instanceof EntitySnowman)
			{
				pathfinders.put(1, new PathfinderGoalArrowAttack((IRangedEntity) ent, 0.25F, 20, 10.0F));
		        pathfinders.put(2, new PathfinderGoalRandomStroll((EntityCreature) ent, 0.2F));
		        pathfinders.put(3, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
		        pathfinders.put(4, new PathfinderGoalRandomLookaround(ent));
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
	        pathfinders.put(9, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(10, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(11, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 10.0F));
		} else if(ent instanceof EntityPig)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalPanic((EntityCreature) ent, speed * 1.52f));
	        pathfinders.put(2, (PathfinderGoal) setField(ent, NMS.ANIMAL_GOALSELECTOR2, new PathfinderGoalPassengerCarrotStick(ent, speed * 1.36f)));
	        pathfinders.put(3, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(4, new PathfinderGoalTempt((EntityCreature) ent, speed * 1.2f, Item.CARROT_STICK.id, false));
	        pathfinders.put(4, new PathfinderGoalTempt((EntityCreature) ent, speed * 1.2f, Item.CARROT.id, false));
	        pathfinders.put(5, new PathfinderGoalFollowParent((EntityAnimal) ent, speed * 1.12f));
	        pathfinders.put(6, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(7, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 6.0F));
	        pathfinders.put(8, new PathfinderGoalRandomLookaround(ent));
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
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(6, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWitch)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, new PathfinderGoalArrowAttack((IRangedEntity) ent, speed, 60, 10.0F));
	        pathfinders.put(2, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(3, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(3, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWither)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, new PathfinderGoalArrowAttack((IRangedEntity) ent, speed, 40, 20.0F));
	        pathfinders.put(5, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(6, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityWolf)
		{
			pathfinders.put(1, new PathfinderGoalFloat(ent));
	        pathfinders.put(2, (PathfinderGoal) getField(ent, NMS.ANIMAL_GOALSELECTOR2));
	        pathfinders.put(3, new PathfinderGoalLeapAtTarget(ent, 0.4F));
	        pathfinders.put(4, new PathfinderGoalMeleeAttack(ent, speed, true));
	        pathfinders.put(5, new PathfinderGoalFollowOwner((EntityTameableAnimal) ent, speed, 10.0F, 2.0F));
	        pathfinders.put(6, new PathfinderGoalBreed((EntityAnimal) ent, speed));
	        pathfinders.put(7, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(8, new PathfinderGoalBeg((EntityWolf) ent, 8.0F));
	        pathfinders.put(9, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(9, new PathfinderGoalRandomLookaround(ent));
		} else if(ent instanceof EntityZombie)
		{
			pathfinders.put(0, new PathfinderGoalFloat(ent));
	        pathfinders.put(1, new PathfinderGoalBreakDoor(ent));
	        pathfinders.put(2, new PathfinderGoalMeleeAttack(ent, EntityHuman.class, speed, false));
	        pathfinders.put(3, new PathfinderGoalMeleeAttack(ent, EntityVillager.class, speed, true));
	        pathfinders.put(4, new PathfinderGoalMoveTowardsRestriction((EntityCreature) ent, speed));
	        pathfinders.put(5, new PathfinderGoalMoveThroughVillage((EntityCreature) ent, speed, false));
	        pathfinders.put(6, new PathfinderGoalRandomStroll((EntityCreature) ent, speed));
	        pathfinders.put(7, new PathfinderGoalLookAtPlayer(ent, EntityHuman.class, 8.0F));
	        pathfinders.put(7, new PathfinderGoalRandomLookaround(ent));
		}
		return pathfinders;
	}
	
	public static Map<Integer, PathfinderGoal> getTargetSelectors(EntityLiving ent, float speed)
	{
		Map<Integer, PathfinderGoal> pathfinders = new HashMap<Integer, PathfinderGoal>();
		if(ent instanceof EntityChicken)
		{
			
		} else if(ent instanceof EntityCow)
		{
			
		} else if(ent instanceof EntityCreeper)
		{
	        pathfinders.put(1, new PathfinderGoalNearestAttackableTarget(ent, EntityHuman.class, 16.0F, 0, true));
	        pathfinders.put(2, new PathfinderGoalHurtByTarget(ent, false));
		} else if(ent instanceof EntityIronGolem)
		{
	        pathfinders.put(1, new PathfinderGoalDefendVillage((EntityIronGolem) ent));
	        pathfinders.put(2, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(3, new PathfinderGoalNearestAttackableTarget(ent, EntityLiving.class, 16.0F, 0, false, true, IMonster.a));
			if(ent instanceof EntitySnowman)
			{
		        pathfinders.put(1, new PathfinderGoalNearestAttackableTarget(ent, EntityLiving.class, 16.0F, 0, true, false, IMonster.a));
			}
		} else if(ent instanceof EntityOcelot)
		{
	        pathfinders.put(1, new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal) ent, EntityChicken.class, 14.0F, 750, false));
		} else if(ent instanceof EntityPig)
		{
			
		} else if(ent instanceof EntitySheep)
		{
			
		} else if(ent instanceof EntitySkeleton)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderGoalNearestAttackableTarget(ent, EntityHuman.class, 16.0F, 0, true));
		} else if(ent instanceof EntityWitch)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderGoalNearestAttackableTarget(ent, EntityHuman.class, 16.0F, 0, true));
		} else if(ent instanceof EntityWither)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, false));
	        pathfinders.put(2, new PathfinderGoalNearestAttackableTarget(ent, EntityLiving.class, 30.0F, 0, false, false, (IEntitySelector) getField(ent, NMS.WITHER_SELECTOR)));
		} else if(ent instanceof EntityWolf)
		{
	        pathfinders.put(1, new PathfinderGoalOwnerHurtByTarget((EntityTameableAnimal) ent));
	        pathfinders.put(2, new PathfinderGoalOwnerHurtTarget((EntityTameableAnimal) ent));
	        pathfinders.put(3, new PathfinderGoalHurtByTarget(ent, true));
	        pathfinders.put(4, new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal) ent, EntitySheep.class, 16.0F, 200, false));
		} else if(ent instanceof EntityZombie)
		{
	        pathfinders.put(1, new PathfinderGoalHurtByTarget(ent, true));
	        pathfinders.put(2, new PathfinderGoalNearestAttackableTarget(ent, EntityHuman.class, 16.0F, 0, true));
	        pathfinders.put(2, new PathfinderGoalNearestAttackableTarget(ent, EntityVillager.class, 16.0F, 0, false));
		}
		return pathfinders;
	}
	
	private static Object getField(EntityLiving ent, String name)
	{
		try
		{
			Field f = ent.getClass().getField(name);
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
			Field f = ent.getClass().getField(name);
			f.setAccessible(true);
			f.set(ent, value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}
}
