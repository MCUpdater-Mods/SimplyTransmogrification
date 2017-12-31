package org.mcupdater.simplytransmogrification;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTalisman extends Item {

	public ItemTalisman() {
		setRegistryName("talisman");
		setUnlocalizedName("talisman");
		this.setMaxStackSize(1);
		this.setContainerItem(this);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand) {
		if (entity.world.isRemote) {
			return false;
		}
		if (entity instanceof EntityAnimal) {
			EntityOcelot kitty = new EntityOcelot(entity.getEntityWorld());
			entity.setDead();
			kitty.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, ((EntityAnimal) entity).rotationYaw, ((EntityAnimal) entity).rotationPitch);
			kitty.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(kitty)),null);
			entity.getEntityWorld().spawnEntity(kitty);
			kitty.spawnExplosionParticle();
			return true;
		}
		return false;
	}
}
