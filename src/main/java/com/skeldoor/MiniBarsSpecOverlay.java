package com.skeldoor;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.VarPlayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

public class MiniBarsSpecOverlay extends OverlayPanel{

    @Inject
    private MiniBarsConfig config;

    @Inject
    private Client client;

    @Inject
    MiniBarsSpecOverlay(
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
        if (config.renderSpec()) renderSpec();

        return super.render(graphics);
    }

    void renderSpec(){
        setPreferredSize(new Dimension(
            config.specSize().width,
            0));
        MiniBarsUtils.buildPanel(
               panelComponent,
               0,
               100,
               client.getVarpValue(VarPlayer.SPECIAL_ATTACK_PERCENT)/10f,
               config.specColour(),
               config.specSize(),
                config.totalLabels(),
                config.showLabels());
    }
}
