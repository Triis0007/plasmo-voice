package su.plo.lib.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import su.plo.lib.chat.TextComponent;
import su.plo.lib.chat.TextConverter;
import su.plo.lib.client.ModClientLib;
import su.plo.lib.client.gui.narration.NarrationOutput;
import su.plo.lib.client.gui.screen.GuiScreen;
import su.plo.lib.client.gui.screen.MinecraftScreen;

import java.util.List;

public final class ModScreenWrapper extends Screen implements MinecraftScreen {

    private final GuiScreen screen;
    private final ModGuiRender render;
    private final TextConverter<Component> textConverter;

    public ModScreenWrapper(@NotNull ModClientLib minecraftLib,
                            @NotNull GuiScreen screen) {
        super(minecraftLib.getTextConverter().convert(screen.getTitle()));

        this.screen = screen;
        this.textConverter = minecraftLib.getTextConverter();
        this.render = new ModGuiRender(
                minecraftLib.getTesselator(),
                textConverter,
                minecraftLib.getResources()
        );
    }

    // Screen override

    @Override
    public void tick() {
        screen.tick();
    }

    @Override
    protected void init() {
        screen.init();
    }

    @Override
    public void removed() {
        screen.removed();
    }

    @Override
    public void onClose() {
        if (!screen.onClose()) {
            super.onClose();
        }
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float delta) {
        render.getMatrix().setPoseStack(poseStack);
        screen.render(render, mouseX, mouseY, delta);
    }

    @Override
    protected void updateNarratedWidget(@NotNull NarrationElementOutput narrationOutput) {
        screen.updateNarratedWidget(new ModScreenNarrationOutput(narrationOutput));
    }

    // ContainerEventHandler override
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return screen.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return screen.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return screen.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return screen.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int button, int keyCode, int scanCode) {
        return screen.keyPressed(button, keyCode, scanCode) || super.keyPressed(button, keyCode, scanCode);
    }

    @Override
    public boolean keyReleased(int button, int keyCode, int scanCode) {
        return screen.keyReleased(button, keyCode, scanCode);
    }

    @Override
    public boolean charTyped(char c, int i) {
        return screen.charTyped(c, i);
    }

    @Override
    public boolean changeFocus(boolean focus) {
        return screen.changeFocus(focus);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        screen.mouseMoved(mouseX, mouseY);
    }

    // MinecraftScreen impl
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void renderBackground() {
        super.renderBackground(render.getMatrix().getPoseStack());
    }

    @Override
    public void drawTextShadow(@NotNull TextComponent text, int x, int y, int color) {
        font.drawShadow(render.getMatrix().getPoseStack(), textConverter.convert(text), x, y, color);
    }

    @Override
    public void renderTooltip(List<TextComponent> tooltip, int mouseX, int mouseY) {
        renderComponentTooltip(render.getMatrix().getPoseStack(), textConverter.convert(tooltip), mouseX, mouseY);
    }

    @RequiredArgsConstructor
    class ModScreenNarrationOutput implements NarrationOutput {

        private final NarrationElementOutput narrationOutput;

        @Override
        public void add(@NotNull Type type, @NotNull TextComponent component) {
            narrationOutput.add(
                    NarratedElementType.valueOf(type.name()),
                    textConverter.convert(component)
            );
        }

        @Override
        public NarrationOutput nest() {
            return new ModScreenNarrationOutput(narrationOutput.nest());
        }
    }
}