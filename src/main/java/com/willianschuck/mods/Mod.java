package com.willianschuck.mods;

public abstract class Mod {

    protected final Mods mod;
    private boolean enabled;

    protected Mod(Mods mod) {
        this.mod = mod;
    }

    public void toggle() {
        enabled = !enabled;
        System.out.println("Set " + mod.getName() + " to " + enabled);
        if (enabled) onEnable();
        else onDisable();
    }

    public int getKey() {
        return mod.getKey();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void onEnable() {
    }

    public void onDisable() {

    }

}
