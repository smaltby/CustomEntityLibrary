package com.github.customentitylibrary.utils;

import net.minecraft.server.v1_5_R2.MathHelper;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Utils
{
	/**
	 * Get arguments from a String gotten from a YAMLConfiguration entry, split by commas.
	 * @param regex	the String to get the arguments from
	 * @return		array of arguments
	 */
	public static String[] getConfigArgs(String regex)
	{
		String[] args = new String[0];
		if(regex.contains(" "))
		{
			String argsNotArray = regex.split("\\s")[1];
			args = argsNotArray.split(",");
		}
		return args;
	}
	
	/**
	 * Parse a String to a int
	 * @param toParse	string to be parsed
	 * @param defaultValue	default value to return if 'toParse' is not parsable
	 * @return	int value of 'toParse', or 'defaultValue' if it is not parsable
	 */
	public static int parseInt(String toParse, int defaultValue)
	{
		try
		{
			return Integer.parseInt(toParse);
		} catch(NumberFormatException e)
		{
			return defaultValue;
		}
	}
	
	/**
	 * Parse a String to a double
	 * @param toParse	string to be parsed
	 * @param defaultValue	default value to return if 'toParse' is not parsable
	 * @return	double value of 'toParse', or 'defaultValue' if it is not parsable
	 */
	public static double parseDouble(String toParse, double defaultValue)
	{
		try
		{
			return Double.parseDouble(toParse);
		} catch(NumberFormatException e)
		{
			return defaultValue;
		}
	}
	
	//I suspect the verticalModifier isn't working...
		public static void knockBack(LivingEntity entity, Location source, double horizontalModifier, double verticalModifier)
		{
			if(((CraftLivingEntity) entity).getHandle().hurtTicks < 0)
				return;
			Location playerLocation = entity.getLocation();
			double xPower = source.getX() - playerLocation.getX();
			double yPower = 0.4D;
			double zPower;
			
	        for (zPower = source.getZ() - playerLocation.getZ(); xPower * xPower + zPower * zPower < 1.0E-4D; zPower = (Math.random() - Math.random()) * 0.01D)
	        {
	        	xPower = (Math.random() - Math.random()) * 0.01D;
	        }
			
			if (yPower > 0.4000000059604645)
	        {
				yPower = 0.4000000059604645;
	        }
	        
			Vector v = entity.getVelocity();
			double motionX = v.getX();
			double motionY = v.getY();
			double motionZ = v.getZ();
			double horizontal = MathHelper.sqrt(xPower * xPower + zPower * zPower);
			double vertical = yPower;
			motionX /= 2.0D;
			motionY /= 2.0D;
			motionZ /= 2.0D;
			motionX -= xPower / (double)horizontal * (double)vertical;
			motionY += (double)vertical;
			motionZ -= zPower / (double)horizontal * (double)vertical;
			
			motionX *= horizontalModifier;
			motionY *= verticalModifier;
			motionZ *= horizontalModifier;

			entity.setVelocity(new Vector(motionX, motionY, motionZ));
		}
}
