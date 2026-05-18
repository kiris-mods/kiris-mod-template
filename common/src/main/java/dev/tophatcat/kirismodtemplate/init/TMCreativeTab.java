package dev.tophatcat.kirismodtemplate.init;

import dev.tophatcat.kirismodtemplate.platform.IPlatform;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class TMCreativeTab {

    public static final Supplier<CreativeModeTab> TEMPLATE_TAB = IPlatform.INSTANCE.registerCreativeModeTab(
        "example_creative_tab", Items.ENCHANTED_BOOK::getDefaultInstance, CreativeModeTab.Builder::build);
}
