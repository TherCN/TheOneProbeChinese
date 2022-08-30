package mcjty.theoneprobe.gui;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.config.ConfigSetup;
import mcjty.theoneprobe.rendering.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

import static mcjty.theoneprobe.config.ConfigSetup.*;
import static net.minecraft.util.text.TextFormatting.*;

public class GuiNote extends GuiScreen {
    private static final int WIDTH = 256;
    private static final int HEIGHT = 160;

    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_MARGIN = 80;
    public static final int BUTTON_HEIGHT = 16;

    private int guiLeft;
    private int guiTop;

    private static final ResourceLocation background = new ResourceLocation(TheOneProbe.MODID, "textures/gui/note.png");

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        guiLeft = (this.width - WIDTH) / 2;
        guiTop = (this.height - HEIGHT) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
        int x = guiLeft+5;
        int y = guiTop+8;
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "你应该了解有关于" + TextFormatting.GOLD + "The One Probe" + TextFormatting.WHITE + "的事情"); y += 10;
        y += 10;

        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, BOLD + "当你看一个方块或实体时"); y += 10;
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, BOLD + "此MOD可以在屏幕上显示工具提示"); y += 10;

        y += 10;
        switch (ConfigSetup.needsProbe) {
            case PROBE_NEEDED:
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "在这个游戏中，配置为"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "需要查看工具显示"); y += 10;
                y += 16;
                y = setInConfig(x, y);
                break;
            case PROBE_NOTNEEDED:
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "在这个游戏中，TOP配置不为"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "需要查看工具显示"); y += 10;
                y += 16;
                y = setInConfig(x, y);
                break;
            case PROBE_NEEDEDFOREXTENDED:
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "在这个游戏中，TOP配置为"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "需要查看扩展信息（当观看"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "时）但不是基本信息"); y += 10;
                y += 6;
                y = setInConfig(x, y);
                break;
            case PROBE_NEEDEDHARD:
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "在这个游戏中，TOP配置为"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "需要查看工具显示"); y += 10;
                RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "用于设置服务器"); y += 10;
                break;
        }

        y += 10;

        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "在客户端检查Mod Option..."); y += 10;
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "侧面配置设置或窃取右键单击"); y += 10;
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, "本说明为更用户友好的设置"); y += 10;
    }

    private int hitX;
    private int hitY;

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX += guiLeft;
        mouseY += guiTop;
        if (mouseY >= hitY && mouseY < hitY + BUTTON_HEIGHT) {
            if (mouseX >= hitX && mouseX < hitX + BUTTON_WIDTH) {
                ConfigSetup.setProbeNeeded(PROBE_NEEDED);
            } else if (mouseX >= hitX+BUTTON_MARGIN && mouseX < hitX + BUTTON_WIDTH+BUTTON_MARGIN) {
                ConfigSetup.setProbeNeeded(PROBE_NOTNEEDED);
            } else if (mouseX >= hitX+BUTTON_MARGIN*2 && mouseX < hitX + BUTTON_WIDTH+BUTTON_MARGIN*2) {
                ConfigSetup.setProbeNeeded(PROBE_NEEDEDFOREXTENDED);
            }
        }
    }

    private int setInConfig(int x, int y) {
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, BOLD + "" + GREEN + "你可以点这里修改:");
        y += 10;

        hitY = y + guiTop;
        hitX = x + guiLeft;
        drawRect(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, 0xff000000);
        RenderHelper.renderText(Minecraft.getMinecraft(), x + 3, y + 4, "需要"); x += BUTTON_MARGIN;

        drawRect(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, 0xff000000);
        RenderHelper.renderText(Minecraft.getMinecraft(), x + 3, y + 4, "不需要"); x += BUTTON_MARGIN;

        drawRect(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, 0xff000000);
        RenderHelper.renderText(Minecraft.getMinecraft(), x + 3, y + 4, "扩展"); x += BUTTON_MARGIN;

        y += BUTTON_HEIGHT - 4;
        return y;
    }

}
