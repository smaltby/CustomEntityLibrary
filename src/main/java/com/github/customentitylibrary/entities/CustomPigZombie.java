package com.github.customentitylibrary.entities;

import net.minecraft.server.v1_6_R3.EntityPigZombie;
import net.minecraft.server.v1_6_R3.World;

public class CustomPigZombie extends EntityPigZombie
{
	public CustomPigZombie(World world)
	{
		super(world);
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean bf()
	{
		return true;
	}
}