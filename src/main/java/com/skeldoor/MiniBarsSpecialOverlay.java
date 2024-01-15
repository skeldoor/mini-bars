package net.runelite.client.plugins.minibars;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.client.plugins.itemstats.ItemStatChangesService;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class MiniBarsSpecialOverlay extends OverlayPanel{

    private static final Color SPECIAL_ATTACK_COLOR = new Color(3, 153, 0, 195);
    private static final int MAX_SPECIAL_ATTACK_VALUE = 100;

    private final Client client;

    private final MiniBarsPlugin plugin;

    private final MiniBarsConfig config;

    private final ItemStatChangesService itemStatService;

    private MiniBarsComponent barRenderer;

    @Inject
    MiniBarsSpecialOverlay(
            Client client,
            MiniBarsPlugin plugin,
            MiniBarsConfig config,
            ItemStatChangesService itemstatservice)
    {
        super(plugin);

        //setPriority(OverlayPriority.LOW);
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setMovable(true);
        setResizable( false );
        setSnappable( true );
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.itemStatService = itemstatservice;

        initRenderer();
    }

    private void initRenderer()
    {
        barRenderer = new MiniBarsComponent(
                () -> MAX_SPECIAL_ATTACK_VALUE,
                () -> client.getVarpValue(VarPlayer.SPECIAL_ATTACK_PERCENT) / 10,
                () -> 0,
                () -> SPECIAL_ATTACK_COLOR,
                () -> SPECIAL_ATTACK_COLOR
        );
    }

    @Override
    public Dimension render( Graphics2D g )
    {
        if ( plugin.isBarsDisplayed() && config.renderSpecial() )
        {
            barRenderer.renderBar( config, g, panelComponent, config.specialFullnessDirection(), config.specialLabelPosition(), config.specialSize().width, config.specialSize().height );

            return config.specialSize();
        }

        return null;
    }
}
