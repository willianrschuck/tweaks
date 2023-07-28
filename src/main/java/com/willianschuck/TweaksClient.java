package com.willianschuck;

import com.willianschuck.events.EventManager;
import com.willianschuck.events.listeners.KeyPressListener;
import com.willianschuck.mods.BetterElytra;
import com.willianschuck.mods.KillAura;
import com.willianschuck.mods.Mod;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class TweaksClient {

    public static final TweaksClient INSTANCE = new TweaksClient();
    private final List<Mod> mods;

    public static MinecraftClient CLIENT;

    private TweaksClient() {
        mods = List.of(
                new BetterElytra(),
                new KillAura()
        );
        EventManager.add(KeyPressListener.class, new KeyboardHandler(mods));
    }

    public void init() {
        CLIENT = MinecraftClient.getInstance();
    }

}
