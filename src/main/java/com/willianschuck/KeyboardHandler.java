package com.willianschuck;

import com.willianschuck.events.listeners.KeyPressListener;
import com.willianschuck.mods.Mod;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyboardHandler implements KeyPressListener {

    Map<Integer, List<Mod>> modsByKey;

    public KeyboardHandler(List<Mod> mods) {
        modsByKey = mods.stream().collect(Collectors.groupingBy(Mod::getKey));
    }

    @Override
    public void onKeyPress(KeyPressEvent event) {
        if (event.getAction() != GLFW.GLFW_PRESS) {
            return;
        }
        List<Mod> mods = modsByKey.get(event.getKeyCode());
        if (mods != null) {
            mods.forEach(Mod::toggle);
        }
    }

}
