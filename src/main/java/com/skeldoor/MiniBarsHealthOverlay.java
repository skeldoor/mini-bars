package com.skeldoor;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Client;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.ProgressBarComponent;

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
        addMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Total XP Counter Overlay");
    }


    @Override
    public Dimension render(Graphics2D graphics) {
        if (config.renderHealth()) renderHealth();
        return super.render(graphics);
    }

    void renderHealth(){
        ProgressBarComponent healthBar = new ProgressBarComponent();
        healthBar.setPreferredLocation(new Point(400,440));
        healthBar.setMinimum(0);
        healthBar.setMaximum(client.getRealSkillLevel(Skill.HITPOINTS));
        healthBar.setValue(client.getBoostedSkillLevel(Skill.HITPOINTS));
        healthBar.setBackgroundColor(Color.BLACK);
        healthBar.setForegroundColor(config.healthColour());
        healthBar.setFontColor(Color.WHITE);
        healthBar.setLabelDisplayMode(ProgressBarComponent.LabelDisplayMode.FULL);
        panelComponent.getChildren().add(healthBar);
    }

}
