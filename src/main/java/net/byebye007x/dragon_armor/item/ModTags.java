package net.byebye007x.dragon_armor.item;

import net.byebye007x.dragon_armor.DragonArmor;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> DRAGON_ARMOR_MOD_ITEMS = createTag("dragon_armor_mod_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(DragonArmor.MOD_ID, name));
        }
    }
}
