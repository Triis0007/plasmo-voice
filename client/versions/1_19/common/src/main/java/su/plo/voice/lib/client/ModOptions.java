package su.plo.voice.lib.client;

import net.minecraft.client.Minecraft;
import su.plo.lib.client.MinecraftOptions;

public final class ModOptions implements MinecraftOptions {

    private final Minecraft minecraft = Minecraft.getInstance();

    @Override
    public boolean isGuiHidden() {
        return minecraft.options.hideGui;
    }

    @Override
    public int getRenderDistance() {
        return minecraft.options.renderDistance().get();
    }

    @Override
    public float getBackgroundOpacity(float m) {
        return minecraft.options.getBackgroundOpacity(m);
    }
}
