package de.thexxturboxx.twodcraft;

import de.thexxturboxx.twodcraft.gen.TerrariaWorldType;
import de.thexxturboxx.twodcraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = TwoDCraft.ID, version = TwoDCraft.VERSION, name = TwoDCraft.NAME)
public class TwoDCraft {
	
	public static final String ID = "twodcraft";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "2D Craft";
	
	public static TerrariaWorldType TERRARIA;
	
	@Instance(ID)
	public static TwoDCraft instance;
	
	@SidedProxy(modId = ID,
			clientSide = "de.thexxturboxx.twodcraft.proxy.ClientProxy",
			serverSide = "de.thexxturboxx.twodcraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		TERRARIA = new TerrariaWorldType();
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
}