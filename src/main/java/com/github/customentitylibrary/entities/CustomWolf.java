package main.java.com.github.customentitylibrary.entities;

import main.java.com.github.customentitylibrary.CustomEntityMoveEvent;
import net.minecraft.server.v1_5_R2.DamageSource;
import net.minecraft.server.v1_5_R2.Entity;
import net.minecraft.server.v1_5_R2.EntityAgeable;
import net.minecraft.server.v1_5_R2.EntityAnimal;
import net.minecraft.server.v1_5_R2.EntityHuman;
import net.minecraft.server.v1_5_R2.EntityWolf;
import net.minecraft.server.v1_5_R2.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class CustomWolf extends EntityWolf
{
	public CustomWolf(World world)
	{
		super(((CraftWorld) world).getHandle());
        this.a(0.6F, 0.8F);
	}

	@Override
    public boolean m(Entity entity) {
        int damage = 2;
        return entity.damageEntity(DamageSource.mobAttack(this), damage);
    }

    @Override
    public boolean a_(EntityHuman entityhuman)
    {
        return false;	//No feeding/taming this wolf
    }

    @Override
    public boolean c(ItemStack itemstack)
    {
    	return false;	//This wolf needs no food
    }

    @Override
    public boolean isAngry()
    {
        return true;	//Let the anger flow through you
    }

    @Override
    public void setAngry(boolean flag)
    {
        //Redundant, this wolf is always angry
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityanimal)
    {
        return null;
    }

    @Override
    public boolean mate(EntityAnimal entityanimal)
    {
        return false;	//The war is your mate!
    }
	
	@Override
	public void move(double d0, double d1, double d2)
	{
		CustomEntityMoveEvent event = new CustomEntityMoveEvent(this.getBukkitEntity(), new Location(this.world.getWorld(), lastX, lastY, lastZ), new Location(this.world.getWorld(), locX, locY, locZ));
		if(event != null)
			Bukkit.getServer().getPluginManager().callEvent(event);
		super.move(d0, d1, d2);
	}
}
