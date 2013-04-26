package com.github.customentitylibrary.entities;

import net.minecraft.server.v1_5_R2.Enchantment;
import net.minecraft.server.v1_5_R2.EnchantmentManager;
import net.minecraft.server.v1_5_R2.EntityArrow;
import net.minecraft.server.v1_5_R2.EntityGiantZombie;
import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.IRangedEntity;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class CustomGiant extends EntityGiantZombie implements IRangedEntity
{
	public CustomGiant(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
	
	 /**
	  * Returns true if the newer Entity AI code should be run
	  */
	 @Override
	 protected boolean bh()
	 {
		 return true;
	 }
	 
	 @Override
	 public void a(EntityLiving arg0, float f1)
	 {
		 //Copied from EntitySkeleton class
		 EntityArrow entityarrow = new EntityArrow(this.world, this, arg0, 1.6F, 12.0F);
		 int i = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_DAMAGE.id, this.bG());
		 int j = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK.id, this.bG());

		 if (i > 0)
			 entityarrow.b(entityarrow.c() + (double) i * 0.5D + 0.5D);

		 if (j > 0)
			 entityarrow.a(j);

		 if (EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_FIRE.id, this.bG()) > 0)
			 entityarrow.setOnFire(100);

		 this.makeSound("random.bow", 1.0F, 1.0F / (this.aE().nextFloat() * 0.4F + 0.8F));
		 this.world.addEntity(entityarrow);
	 }
}
