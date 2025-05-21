package net.byebye007x.dragon_armor;

import net.byebye007x.dragon_armor.item.ModItems;
import net.byebye007x.dragon_armor.item.ModLootTableModifier;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonArmor implements ModInitializer {
	public static final String MOD_ID = "dragon_armor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModLootTableModifier.modifyLootTables();
	}
}