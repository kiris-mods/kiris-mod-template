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

import com.mojang.logging.LogUtils;
import dev.tophatcat.kirismodtemplate.init.TMCreativeTab;
import dev.tophatcat.kirismodtemplate.platform.IPlatform;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ServiceLoader;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateCommon {

    public static final String MOD_ID = "kirismodtemplate";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        loadClass(TMCreativeTab.class);
    }

    public static <T> T loadService(Class<T> serviceClass) {
        return ServiceLoader.load(serviceClass, serviceClass.getClassLoader()).findFirst()
            .orElseThrow(() -> new NoSuchElementException("Unable to find implementation service for " + serviceClass.getName()));
    }

    private static void loadClass(Class<?> clazz) {
        var mask = Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;
        var count = Arrays.stream(clazz.getDeclaredFields()).filter(field -> (field.getModifiers() | mask) == mask).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Access error while registering %s from %s".formatted(field.getName(), clazz.getName()), e);
                }
            })
            .filter(Objects::nonNull)
            .count();
        LOGGER.debug("Loaded {} objects from {}", count, clazz.getName());
    }
}
