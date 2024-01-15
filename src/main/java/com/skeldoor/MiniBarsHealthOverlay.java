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

public class MiniBarsHealthOverlay extends OverlayPanel{

    private static final Color HEALTH_COLOR = new Color(225, 35, 0, 125);
    private static final Color POISONED_COLOR = new Color(0, 145, 0, 150);
    private static final Color VENOMED_COLOR = new Color(0, 65, 0, 150);
    private static final Color HEAL_COLOR = new Color(255, 112, 6, 150);
    private static final Color DISEASE_COLOR = new Color(255, 193, 75, 181);
    private static final Color PARASITE_COLOR = new Color(196, 62, 109, 181);

    private final Client client;

    private final MiniBarsPlugin plugin;

    private final MiniBarsConfig config;

    private final ItemStatChangesService itemStatService;

    private MiniBarsComponent barRenderer;

    @Inject
    MiniBarsHealthOverlay(
            Client client,
            MiniBarsPlugin plugin,
            MiniBarsConfig config,
            ItemStatChangesService itemstatservice)
    {
        //super(plugin);

        //setPriority(OverlayPriority.LOW);
        //setBounds( new Rectangle( config.healthSize().width, config.healthSize().height ) );
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
                () -> inLms() ? Experience.MAX_REAL_LEVEL : client.getRealSkillLevel(Skill.HITPOINTS),
                () -> client.getBoostedSkillLevel(Skill.HITPOINTS),
                () -> getRestoreValue(Skill.HITPOINTS.getName()),
                () ->
                {
                    final int poisonState = client.getVarpValue(VarPlayer.POISON);

                    if (poisonState >= 1000000)
                    {
                        return VENOMED_COLOR;
                    }

                    if (poisonState > 0)
                    {
                        return POISONED_COLOR;
                    }

                    if (client.getVarpValue(VarPlayer.DISEASE_VALUE) > 0)
                    {
                        return DISEASE_COLOR;
                    }

                    if (client.getVarbitValue(Varbits.PARASITE) >= 1)
                    {
                        return PARASITE_COLOR;
                    }

                    return HEALTH_COLOR;
                },
                () -> HEAL_COLOR
        );
    }

    @Override
    public Dimension render( Graphics2D g )
    {
        if ( plugin.isBarsDisplayed() && config.renderHealth() )
        {
            barRenderer.renderBar( config, g, panelComponent, config.healthFullnessDirection(), config.healthLabelPosition(), config.healthSize().width, config.healthSize().height );
            return config.healthSize();
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
