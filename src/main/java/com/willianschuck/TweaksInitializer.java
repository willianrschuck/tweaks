package com.willianschuck;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TweaksInitializer implements ModInitializer {

    public static final String MOD_ID = "willtweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        TweaksClient.INSTANCE.init();
    }
}
