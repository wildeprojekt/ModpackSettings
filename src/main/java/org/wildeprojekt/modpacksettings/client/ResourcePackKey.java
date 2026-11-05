package org.wildeprojekt.modpacksettings.client;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Slf4j
public class ResourcePackKey implements ClientModInitializer {

    private static final KeyBinding RP_KEY = new KeyBinding(
            "key.rpkey", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_L, "category.rpkey"
    );

    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(RP_KEY);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (RP_KEY.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new PackScreen(
                            client.getResourcePackManager(),
                            resourcePackManager -> {
                                // Apply the selected resource packs.
                                client.options.refreshResourcePacks(resourcePackManager);
                                // Return to the game when the screen closes.
                                client.setScreen(null);
                            }, client.getResourcePackDir(), Text.translatable("resourcePack.title"))
                        );
                    }
                }
            }
        );
    }
}