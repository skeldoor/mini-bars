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

public class MiniBarsPrayerOverlay extends OverlayPanel{

    @Inject
    private Client client;

    @Inject
    private MiniBarsConfig config;

    @Inject
    MiniBarsPrayerOverlay(
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
}
