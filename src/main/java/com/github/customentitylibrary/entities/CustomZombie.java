package main.java.com.github.customentitylibrary.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

import main.java.com.github.customentitylibrary.CustomEntityMoveEvent;
import net.minecraft.server.v1_5_R2.Enchantment;
import net.minecraft.server.v1_5_R2.EnchantmentManager;
import net.minecraft.server.v1_5_R2.EntityArrow;
import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.EntityZombie;
import net.minecraft.server.v1_5_R2.IRangedEntity;

public class CustomZombie extends EntityZombie implements IRangedEntity
{
	public CustomZombie(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
	
	@Override
	public void move(double d0, double d1, double d2)
	{
		CustomEntityMoveEvent event = new CustomEntityMoveEvent(this.getBukkitEntity(), new Location(this.world.getWorld(), lastX, lastY, lastZ), new Location(this.world.getWorld(), locX, locY, locZ));
		if(event != null)
			Bukkit.getServer().getPluginManager().callEvent(event);
		super.move(d0, d1, d2);
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
