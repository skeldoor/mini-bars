package com.skeldoor;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

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
    }


    @Override
    public Dimension render(Graphics2D graphics) {
        if (config.renderPrayer()) renderPrayer();

        return super.render(graphics);
    }

    void renderPrayer(){
        setPreferredSize(new Dimension(
                config.prayerSize().width,
                0));
        MiniBarsUtils.buildPanel(
                panelComponent,
                0,
                client.getRealSkillLevel(Skill.PRAYER),
                client.getBoostedSkillLevel(Skill.PRAYER),
                config.prayerColour(),
                config.prayerSize());
    }
}
