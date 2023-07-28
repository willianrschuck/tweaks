package com.willianschuck.mixin;

import com.willianschuck.events.EventManager;
import com.willianschuck.events.listeners.KeyPressListener;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        EventManager.fire(new KeyPressListener.KeyPressEvent(key, scancode, action, modifiers));
    }

}
