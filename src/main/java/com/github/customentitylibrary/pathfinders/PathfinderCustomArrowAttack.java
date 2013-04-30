package com.github.customentitylibrary.pathfinders;

import org.bukkit.craftbukkit.v1_5_R2.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;

import net.minecraft.server.v1_5_R2.Entity;
import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.IRangedEntity;
import net.minecraft.server.v1_5_R2.MathHelper;
import net.minecraft.server.v1_5_R2.Vec3D;

public class PathfinderCustomArrowAttack extends PathfinderBase
{
	private EntityLiving entity;	//Entity executing this pathfinder
    private IRangedEntity rangedEntity;	//Ranged entity executing this pathfinder
    //This decrements to 0 while the target is in range. When it reaches 0, the entity fires, and this goes back up to attackSpeed
    private int rangedAttackTime;
    private float speed;//Speed
    private int f = 0;	//Not sure
    private int attackSpeed;	//Attack speed
    private int maxRange;
    private int minRange = -1;
    
    public PathfinderCustomArrowAttack(IRangedEntity irangedentity, float speed, int attackSpeed, int maxRange)
    {
        if (!(irangedentity instanceof EntityLiving))
        	throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        this.rangedEntity = irangedentity;
        this.entity = (EntityLiving) irangedentity;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
        this.rangedAttackTime = attackSpeed;
        this.maxRange = maxRange*maxRange;	//All uses of range require the squared range, so we must as well get it done here
        this.a(3);
    }
    
    public PathfinderCustomArrowAttack(IRangedEntity irangedentity, float speed, int attackSpeed, int maxRange, int minRange)
    {
    	this(irangedentity, speed, attackSpeed, maxRange);
        this.minRange = minRange*minRange;	//All uses of range require the squared range, so we must as well get it done here
    }
    
	@Override
	public boolean shouldExecute()
	{
		EntityLiving target = this.entity.getGoalTarget();
		if (target == null)
			return false;
		Vec3D entityLocation = Vec3D.a(entity.locX, entity.locY, entity.locZ);
		Vec3D targetLocation = Vec3D.a(target.locX, target.locY, target.locZ);
		if(entityLocation.distanceSquared(targetLocation) < minRange)
			return false;
		return true;
	}

	@Override
	public boolean continueExecuting()
	{
		EntityLiving target = this.entity.getGoalTarget();
		if(target == null)
			return false;
		Vec3D entityLocation = Vec3D.a(entity.locX, entity.locY, entity.locZ);
    	Vec3D targetLocation = Vec3D.a(target.locX, target.locY, target.locZ);
    	if(entityLocation.distanceSquared(targetLocation) < minRange)
    		return false;
        return this.a() || !this.entity.getNavigation().f();
	}

	@Override
	public void startExecuting()
	{
		
	}

	@Override
	public void stopExecuting()
	{
		EntityLiving target = entity.getGoalTarget();
		// CraftBukkit start
        EntityTargetEvent.TargetReason reason = (target == null || target.isAlive()) ? EntityTargetEvent.TargetReason.FORGOT_TARGET : EntityTargetEvent.TargetReason.TARGET_DIED;
        CraftEventFactory.callEntityTargetEvent((Entity) rangedEntity, null, reason);
        // CraftBukkit end
        this.f = 0;
	}

	@Override
	public void update()
	{
		EntityLiving target = entity.getGoalTarget();
		if(target == null) return;
        double d0 = this.entity.e(target.locX, target.boundingBox.b, target.locZ);
        boolean canSee = this.entity.aD().canSee(target);

        if (canSee) {
            ++this.f;
        } else {
            this.f = 0;
        }

        if (d0 <= (double) maxRange && this.f >= 20) {
            entity.getNavigation().g();
        } else {
            entity.getNavigation().a(target, this.speed);
        }

        float f1 = MathHelper.sqrt(d0) / 10f;
        if (f1 > 1.0F) {
            f1 = 1.0F;
        }
        
        this.entity.getControllerLook().a(target, 30.0F, 30.0F);
        this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
        if (this.rangedAttackTime <= 0) {
            if (d0 <= (double) maxRange && canSee) {
                this.rangedEntity.a(target, f1);
                this.rangedAttackTime = this.attackSpeed;	//Reset timer to attack speed
            }
        }
	}
}
