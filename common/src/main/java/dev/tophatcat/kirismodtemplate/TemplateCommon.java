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

import dev.tophatcat.kirismodtemplate.platform.IPlatform;
import java.util.ServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateCommon {

    public static final String MOD_ID = "kirismodtemplate";
    public static final String MOD_NAME = "Kiri's Mod Template";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final IPlatform COMMON_PLATFORM = ServiceLoader.load(IPlatform.class).findFirst().orElseThrow();

    public static void init() {
        LOG.debug("We are currently loaded via the {} mod loader in a {} environment!",
            COMMON_PLATFORM.getPlatformName(),
            COMMON_PLATFORM.getEnvironmentName());
    }
}
