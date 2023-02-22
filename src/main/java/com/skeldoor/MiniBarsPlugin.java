package com.skeldoor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ScriptPreFired;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "Mini Bars"
)
public class MiniBarsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MiniBarsConfig config;

	@Inject
	private MiniBarsHealthOverlay healthOverlay;

	@Inject
	private MiniBarsPrayerOverlay prayerOverlay;

	@Inject
	private MiniBarsEnergyOverlay energyOverlay;

	@Inject
	private MiniBarsSpecOverlay specOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp()
	{
		overlayManager.add(healthOverlay);
		overlayManager.add(prayerOverlay);
		overlayManager.add(energyOverlay);
		overlayManager.add(specOverlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(healthOverlay);
		overlayManager.remove(prayerOverlay);
		overlayManager.remove(energyOverlay);
		overlayManager.remove(specOverlay);
	}

	@Subscribe
	public void onScriptPreFired (ScriptPreFired scriptPreFired)
	{

	}

	@Provides
	MiniBarsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MiniBarsConfig.class);
	}
}
