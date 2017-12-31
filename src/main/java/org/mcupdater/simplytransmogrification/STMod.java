package org.mcupdater.simplytransmogrification;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
@Mod(modid="simplytransmogrification", useMetadata=true)
public class STMod {
	public static ModMetadata metadata;
	@GameRegistry.ObjectHolder("simplytransmogrification:talisman")
	public static ItemTalisman talisman;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		metadata = evt.getModMetadata();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt){
		evt.getRegistry().register(new ItemTalisman());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		talisman.initModel();
	}
}
