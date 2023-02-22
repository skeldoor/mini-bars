package com.example;

import java.awt.*;
import javax.inject.Inject;

import com.google.common.base.Strings;
import net.runelite.api.Client;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.ProgressBarComponent;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.QuantityFormatter;

public class MiniBarsOverlay extends OverlayPanel{

    private final Client client;

    @Inject
    private ExampleConfig config;

    @Inject
    MiniBarsOverlay(
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
        if (config.renderPrayer()) renderPrayer();
        if (config.renderHealth()) renderHealth();
        if (config.renderEnergy()) renderEnergy();

        return super.render(graphics);
    }

    void renderPrayer(){
        ProgressBarComponent prayerBar = new ProgressBarComponent();
        prayerBar.setPreferredLocation(new Point(400,400));
        prayerBar.setMinimum(0);
        prayerBar.setMaximum(client.getRealSkillLevel(Skill.PRAYER));
        prayerBar.setValue(client.getBoostedSkillLevel(Skill.PRAYER));
        prayerBar.setBackgroundColor(Color.BLACK);
        prayerBar.setForegroundColor(config.prayerColour());
        prayerBar.setFontColor(Color.WHITE);
        prayerBar.setLabelDisplayMode(ProgressBarComponent.LabelDisplayMode.FULL);
        panelComponent.getChildren().add(prayerBar);
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

    void renderEnergy(){
        ProgressBarComponent healthBar = new ProgressBarComponent();
        healthBar.setPreferredLocation(new Point(400,440));
        healthBar.setMinimum(0);
        healthBar.setMaximum(100);
        healthBar.setValue((int)(client.getEnergy() / 100f));
        healthBar.setBackgroundColor(Color.BLACK);
        healthBar.setForegroundColor(config.energyColour());
        healthBar.setFontColor(Color.WHITE);
        healthBar.setLabelDisplayMode(ProgressBarComponent.LabelDisplayMode.FULL);
        panelComponent.getChildren().add(healthBar);
    }

}
