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

public class MiniBarsPrayerOverlay extends OverlayPanel{

    private static final Color PRAYER_COLOR = new Color(50, 200, 200, 175);
    private static final Color ACTIVE_PRAYER_COLOR = new Color(57, 255, 186, 225);
    private static final Color PRAYER_HEAL_COLOR = new Color(57, 255, 186, 75);

    private final Client client;

    private final MiniBarsPlugin plugin;

    private final MiniBarsConfig config;

    private final ItemStatChangesService itemStatService;

    private MiniBarsComponent barRenderer;

    @Inject
    MiniBarsPrayerOverlay(
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
                () -> inLms() ? Experience.MAX_REAL_LEVEL : client.getRealSkillLevel(Skill.PRAYER),
                () -> client.getBoostedSkillLevel(Skill.PRAYER),
                () -> getRestoreValue(Skill.PRAYER.getName()),
                () ->
                {
                    Color prayerColor = PRAYER_COLOR;

                    for (Prayer pray : Prayer.values())
                    {
                        if (client.isPrayerActive(pray))
                        {
                            prayerColor = ACTIVE_PRAYER_COLOR;
                            break;
                        }
                    }

                    return prayerColor;
                },
                () -> PRAYER_HEAL_COLOR
        );
    }

    @Override
    public Dimension render( Graphics2D g )
    {
        if ( plugin.isBarsDisplayed() && config.renderPrayer() )
        {
            barRenderer.renderBar( config, g, panelComponent, config.prayerFullnessDirection(), config.prayerLabelStyle(), config.prayerLabelPosition(), config.prayerSize().width, config.prayerSize().height );

            return config.prayerSize();
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

    private boolean inLms()
    {
        return client.getWidget(ComponentID.LMS_INGAME_INFO) != null;
    }
}
