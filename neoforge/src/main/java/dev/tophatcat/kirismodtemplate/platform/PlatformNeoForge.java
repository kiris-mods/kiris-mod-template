package dev.tophatcat.kirismodtemplate.platform;

import com.google.auto.service.AutoService;
import dev.tophatcat.kirismodtemplate.TemplateCommon;
import dev.tophatcat.kirismodtemplate.TemplateNeo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;

@AutoService(IPlatform.class)
public class PlatformNeoForge implements IPlatform {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        var modList = ModList.get();
        if (modList == null) {
            return FMLLoader.getCurrent().getLoadingModList().getMods().stream().anyMatch(it -> it.getModId().equals(modId));
        }

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.isProduction();
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType) {
        return TemplateNeo.BLOCK_ENTITIES.register(id, blockEntityType);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String id, Function<BlockBehaviour.Properties, T> factory) {
        return TemplateNeo.BLOCKS.registerBlock(id, factory);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String id, Function<BlockBehaviour.Properties, T> factory, Supplier<BlockBehaviour.Properties> propertiesGetter) {
        return TemplateNeo.BLOCKS.registerBlock(id, factory, propertiesGetter);
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> entity, MobCategory mobCategory, UnaryOperator<EntityType.Builder<T>> properties) {
        return TemplateNeo.ENTITIES.registerEntityType(id, entity, mobCategory, properties);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Function<Item.Properties, T> factory) {
        return TemplateNeo.ITEMS.registerItem(id, factory);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Function<Item.Properties, T> factory, Supplier<Item.Properties> propertiesGetter) {
        return TemplateNeo.ITEMS.registerItem(id, factory, propertiesGetter);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return TemplateNeo.SOUND_EVENTS.register(id, sound);
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeModeTab(String id, Supplier<ItemStack> icon, Consumer<CreativeModeTab.Builder> tab) {
        return TemplateNeo.CREATIVE_TABS.register(id, () -> {
            var builder = CreativeModeTab.builder().icon(icon).title(Component.translatable(Util.makeDescriptionId("itemGroup", TemplateCommon.id(id))));
            tab.accept(builder);
            return builder.build();
        });
    }
}
