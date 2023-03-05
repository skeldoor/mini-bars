package com.skeldoor;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import java.awt.*;

@ConfigGroup("Mini Bars")
public interface MiniBarsConfig extends Config
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

	@ConfigItem(
			position = 3,
			keyName = "healthSize",
			name = "Health Size",
			description = "Choose the size of the Health bar"
	)
	default Dimension healthSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 0); }
	@ConfigItem
			(
					position = 4,
					keyName = "showPrayer",
					name = "Show Prayer",
					description = "Render Prayer overlay"
			)

	default boolean renderPrayer() { return false; }

	@ConfigItem(
			position = 5,
			keyName = "prayerColour",
			name = "Prayer Colour",
			description = "Choose the background colour of the prayer bar"
	)
	default Color prayerColour() { return Color.CYAN; }


	@ConfigItem(
			position = 6,
			keyName = "prayerSize",
			name = "Prayer Size",
			description = "Choose the size of the Prayer bar"
	)
	default Dimension prayerSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 0); }


	@ConfigItem
			(
					position = 7,
					keyName = "showEnergy",
					name = "Show Energy",
					description = "Render Run Energy overlay"
			)

	default boolean renderEnergy() { return false; }

	@ConfigItem(
			position = 8,
			keyName = "energyColour",
			name = "Energy Colour",
			description = "Choose the background colour of the run energy bar"
	)
	default Color energyColour() { return Color.orange; }

	@ConfigItem(
			position = 9,
			keyName = "energySize",
			name = "Energy Size",
			description = "Choose the size of the Energy bar"
	)
	default Dimension energySize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 0); }

	@ConfigItem
			(
					position = 10,
					keyName = "showSpec",
					name = "Show Spec",
					description = "Render Special Attack overlay"
			)

	default boolean renderSpec() { return false; }

	@ConfigItem(
			position = 11,
			keyName = "specColour",
			name = "Spec Colour",
			description = "Choose the background colour of the Spec bar"
	)
	default Color specColour() { return Color.yellow; }

	@ConfigItem(
			position = 12,
			keyName = "specSize",
			name = "Spec Size",
			description = "Choose the size of the Spec bar"
	)
	default Dimension specSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 0); }

}
