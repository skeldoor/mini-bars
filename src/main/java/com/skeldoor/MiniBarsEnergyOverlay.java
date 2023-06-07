package com.skeldoor;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

public class MiniBarsEnergyOverlay extends OverlayPanel{

    @Inject
    private Client client;

    @Inject
    private MiniBarsConfig config;

    @Inject
    MiniBarsEnergyOverlay(
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
        if (config.renderEnergy()) renderEnergy();

        return super.render(graphics);
    }

    void renderEnergy(){
        setPreferredSize(new Dimension(
                config.energySize().width,
                0));
        MiniBarsUtils.buildPanel(
                panelComponent,
                0,
                100,
                (int)(client.getEnergy() / 100f),
                config.energyColour(),
                config.energySize(),
                config.totalLabels(),
                config.showLabels());
    }
}
