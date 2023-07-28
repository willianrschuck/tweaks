package com.willianschuck.mods;

import com.willianschuck.TweaksClient;
import com.willianschuck.events.EventManager;
import com.willianschuck.events.listeners.TickListener;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.Hand;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class KillAura extends Mod implements TickListener {

    public KillAura() {
        super(Mods.KILL_AURA);
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = TweaksClient.CLIENT.player;
        if (!super.isEnabled() || player == null) return;

        boolean canAttack = player.getAttackCooldownProgress(0) >= 1;

        if (!canAttack) return;

        ToDoubleFunction<Entity> f = player::squaredDistanceTo;

        Stream<AnimalEntity> animalEntityStream = StreamSupport.stream(TweaksClient.CLIENT.world.getEntities().spliterator(), true)
                .filter(e -> e instanceof AnimalEntity)
                .map(e -> (AnimalEntity) e)
                .filter(a -> !a.isRemoved() && a.getHealth() > 0)
                .filter(e -> player.distanceTo(e) < 2.5);

        animalEntityStream.min(Comparator.comparingDouble(player::squaredDistanceTo))
                .ifPresent(animalEntity -> {
                    TweaksClient.CLIENT.interactionManager.attackEntity(player, animalEntity);
                    player.swingHand(Hand.MAIN_HAND);
                });
    }

    @Override
    public void onEnable() {
        EventManager.add(TickListener.class, this);
    }

    @Override
    public void onDisable() {
        EventManager.remove(TickListener.class, this);
    }

}
