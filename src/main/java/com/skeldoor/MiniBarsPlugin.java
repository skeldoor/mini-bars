package com.skeldoor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

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

	@Provides
	MiniBarsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MiniBarsConfig.class);
	}
}
