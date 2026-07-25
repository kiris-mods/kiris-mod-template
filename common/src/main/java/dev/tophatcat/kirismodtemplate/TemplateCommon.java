package dev.tophatcat.kirismodtemplate;

import com.mojang.logging.LogUtils;
import dev.tophatcat.kirismodtemplate.init.TMCreativeTab;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.ServiceLoader;

import net.minecraft.resources.Identifier;
import net.minecraft.server.Services;
import org.slf4j.Logger;

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
        return ServiceLoader.load(serviceClass, Services.class.getClassLoader()).findFirst().orElseThrow(()
            -> new IllegalStateException("No implementation of " + serviceClass.getName() + " found!"));
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
