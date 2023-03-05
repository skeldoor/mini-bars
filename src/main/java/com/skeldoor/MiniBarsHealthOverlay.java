package com.skeldoor;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

public class MiniBarsHealthOverlay extends OverlayPanel{

    @Inject
    private Client client;

    @Inject
    private MiniBarsConfig config;

    @Inject
    MiniBarsHealthOverlay(
            Client client,
            MiniBarsPlugin plugin)
    {
        super(plugin);
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        setPriority(OverlayPriority.LOW);
        setMovable(true);
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (config.renderHealth()) renderHealth();
        return super.render(graphics);
    }

    void renderHealth(){
        setPreferredSize(new Dimension(
                config.healthSize().width,
                0));
        MiniBarsUtils.buildPanel(
                panelComponent,
                0,
                client.getRealSkillLevel(Skill.HITPOINTS),
                client.getBoostedSkillLevel(Skill.HITPOINTS),
                config.healthColour(),
                config.healthSize());
    }
}
