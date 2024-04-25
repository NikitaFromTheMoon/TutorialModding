package net.bored_moon.examplemod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinding {

    public static String KEY_CATEGORY_MADNESS = "key.category.examplemod.madness";
    public static String KEY_MADNESS_LEVEL = "key.examplemod.madness_level";

    public static String KEY_MADNESS_LEVEL_LOW = "key.examplemod.madness_level.low";

    public static final KeyMapping MADNESS_KEY = new KeyMapping(KEY_MADNESS_LEVEL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_MADNESS);


}
