package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{

	@ConfigItem
			(
					position = 1,
					keyName = "showHealth",
					name = "Show Health",
					description = "Render Health overlay"
			)

	default boolean renderHealth() { return false; }

	@ConfigItem(
			position = 2,
			keyName = "healthColour",
			name = "Health Colour",
			description = "Choose the background colour of the health bar"
	)
	default Color healthColour() { return Color.RED; }
	@ConfigItem
			(
					position = 3,
					keyName = "showPrayer",
					name = "Show Prayer",
					description = "Render Prayer overlay"
			)

	default boolean renderPrayer() { return false; }

	@ConfigItem(
			position = 4,
			keyName = "prayerColour",
			name = "Prayer Colour",
			description = "Choose the background colour of the prayer bar"
	)
	default Color prayerColour() { return Color.CYAN; }

	@ConfigItem
			(
					position = 5,
					keyName = "showEnergy",
					name = "Show Energy",
					description = "Render Run Energy overlay"
			)

	default boolean renderEnergy() { return false; }

	@ConfigItem(
			position = 6,
			keyName = "energyColour",
			name = "Energy Colour",
			description = "Choose the background colour of the run energy bar"
	)
	default Color energyColour() { return Color.orange; }


}
