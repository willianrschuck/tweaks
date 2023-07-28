package com.willianschuck.mods;

import org.lwjgl.glfw.GLFW;

public enum Mods {
    KILL_AURA("killAura", GLFW.GLFW_KEY_R),
    BETTER_ELYTRA("betterElytra", GLFW.GLFW_KEY_C);

    private final String name;
    private final int key;

    Mods(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }

}
