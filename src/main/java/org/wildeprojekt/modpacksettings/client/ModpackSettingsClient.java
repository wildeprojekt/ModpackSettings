package org.wildeprojekt.modpacksettings.client;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ClientModInitializer;

@Slf4j
public class ModpackSettingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LOGGER.info("Wildeprojekt modpack settings initialized.");
    }
}