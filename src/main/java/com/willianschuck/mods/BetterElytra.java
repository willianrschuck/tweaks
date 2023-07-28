package com.willianschuck.mods;

import com.willianschuck.TweaksClient;
import com.willianschuck.events.EventManager;
import com.willianschuck.events.listeners.TickListener;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BetterElytra extends Mod implements TickListener {

    public BetterElytra() {
        super(Mods.BETTER_ELYTRA);
    }

    @Override
    public void onEnable() {
        EventManager.add(TickListener.class, this);
    }

    @Override
    public void onDisable() {
        EventManager.remove(TickListener.class, this);
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = TweaksClient.CLIENT.player;

        if (!super.isEnabled() || player == null) return;

        Item chestItem = player.getEquippedStack(EquipmentSlot.CHEST).getItem();

        if (chestItem != Items.ELYTRA || !player.isFallFlying()) {
            return;
        }

        float yaw = (float) Math.toRadians(player.getYaw());
        Vec3d forward = new Vec3d(-MathHelper.sin(yaw) * 0.05, 0,MathHelper.cos(yaw) * 0.05);

        Vec3d playerVelocity = player.getVelocity();

        if (TweaksClient.CLIENT.options.forwardKey.isPressed()) {
            player.setVelocity(playerVelocity.add(forward));
        } else if (TweaksClient.CLIENT.options.backKey.isPressed()) {
            player.setVelocity(playerVelocity.subtract(forward));
        }
    }

}
