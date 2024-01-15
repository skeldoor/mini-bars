package net.runelite.client.plugins.minibars;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.plugins.itemstats.Effect;
import net.runelite.client.plugins.itemstats.ItemStatChangesService;
import net.runelite.client.plugins.itemstats.StatChange;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class MiniBarsEnergyOverlay extends OverlayPanel{

    private static final Color ENERGY_HEAL_COLOR = new Color (199,  118, 0, 218);
    private static final Color RUN_STAMINA_COLOR = new Color(160, 124, 72, 255);
    private static final Color ENERGY_COLOR = new Color(199, 174, 0, 220);
    private static final int MAX_RUN_ENERGY_VALUE = 100;

    private final Client client;

    private final MiniBarsPlugin plugin;

    private final MiniBarsConfig config;

    private final ItemStatChangesService itemStatService;

    private MiniBarsComponent barRenderer;

    @Inject
    MiniBarsEnergyOverlay(
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
                () -> MAX_RUN_ENERGY_VALUE,
                () -> client.getEnergy() / 100,
                () -> getRestoreValue("Run Energy"),
                () ->
                {
                    if (client.getVarbitValue(Varbits.RUN_SLOWED_DEPLETION_ACTIVE) != 0)
                    {
                        return RUN_STAMINA_COLOR;
                    }
                    else
                    {
                        return ENERGY_COLOR;
                    }
                },
                () -> ENERGY_HEAL_COLOR
        );
    }

    @Override
    public Dimension render( Graphics2D g )
    {
        if ( plugin.isBarsDisplayed() && config.renderEnergy() )
        {
            barRenderer.renderBar( config, g, panelComponent, config.energyFullnessDirection(), config.energyLabelPosition(), config.energySize().width, config.energySize().height );

            return config.energySize();
        }

        return null;
    }

    private int getRestoreValue(String skill)
    {
        final MenuEntry[] menu = client.getMenuEntries();
        final int menuSize = menu.length;
        if (menuSize == 0)
        {
            return 0;
        }

        final MenuEntry entry = menu[menuSize - 1];
        final Widget widget = entry.getWidget();
        int restoreValue = 0;

        if (widget != null && widget.getId() == ComponentID.INVENTORY_CONTAINER)
        {
            final Effect change = itemStatService.getItemStatChanges(widget.getItemId());

            if (change != null)
            {
                for (final StatChange c : change.calculate(client).getStatChanges())
                {
                    final int value = c.getTheoretical();

                    if (value != 0 && c.getStat().getName().equals(skill))
                    {
                        restoreValue = value;
                    }
                }
            }
        }

        return restoreValue;
    }
}
