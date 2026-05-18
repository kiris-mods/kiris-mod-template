/*
 * The template workspace that Kiri uses for making mods for Minecraft on both NeoForge and Fabric.
 * Copyright (C) KiriCattus 2013 - 2025
 * https://github.com/kiris-mods/kiris-mod-template/blob/dev/LICENSE.md
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package dev.tophatcat.kirismodtemplate;

import dev.tophatcat.kirismodtemplate.client.TemplateRenderingNeo;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod(TemplateCommon.MOD_ID)
public class TemplateNeo {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES
        = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TemplateCommon.MOD_ID);
    public static final DeferredRegister.Blocks BLOCKS
        = DeferredRegister.createBlocks(TemplateCommon.MOD_ID);
    public static final DeferredRegister.Entities ENTITIES
        = DeferredRegister.createEntities(TemplateCommon.MOD_ID);
    public static final DeferredRegister.Items ITEMS
        = DeferredRegister.createItems(TemplateCommon.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
        = DeferredRegister.create(Registries.SOUND_EVENT, TemplateCommon.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS
        = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TemplateCommon.MOD_ID);
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS
        = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, TemplateCommon.MOD_ID);

    public TemplateNeo(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
        BLOCKS.register(bus);
        CREATIVE_TABS.register(bus);
        ENTITIES.register(bus);
        ENTITY_DATA_SERIALIZERS.register(bus);
        ITEMS.register(bus);
        SOUND_EVENTS.register(bus);
        TemplateCommon.init();

        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            bus.addListener(TemplateRenderingNeo::registerEntityModels);
            bus.addListener(TemplateRenderingNeo::registerModelLayers);
        }
    }
}
