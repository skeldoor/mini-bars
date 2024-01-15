package net.runelite.client.plugins.minibars;

import net.runelite.client.config.*;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import java.awt.*;

@ConfigGroup("Mini Bars")
public interface MiniBarsConfig extends Config
{
	String GROUP = "minibars";

	@ConfigItem(
			position = 1,
			keyName = "showHealth",
			name = "Show Health",
			description = "Render Health overlay"
	)
	default boolean renderHealth() { return false; }

	@ConfigSection(
			position = 2,
			name = "Health Bar Settings",
			description = "Health Bar Settings"
	)
	String healthSettingsSection = "healthSettings";

	@ConfigItem(
			position = 3,
			keyName = "healthSize",
			name = "Health Bar Size",
			description = "Choose the size of the Health bar",
			section = healthSettingsSection
	)
	@Range(
			min = MiniBarsConstants.WIDTH_MIN
	)
	default Dimension healthSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, MiniBarsConstants.STANDARD_HEIGHT); }

	@ConfigItem(
			position = 4,
			keyName = "healthFullnessDirection",
			name = "Health Fullness Direction",
			description = "Choose the direction of fullness of the Health bar",
			section = healthSettingsSection
	)
	default FullnessDirection healthFullnessDirection()
	{
		return FullnessDirection.RIGHT;
	}

	@ConfigItem(
			position = 5,
			keyName = "healthLabelPosition",
			name = "Health Label Position",
			description = "Choose the location of the Health bar's label",
			section = healthSettingsSection
	)
	default LabelPlacement healthLabelPosition()
	{
		return LabelPlacement.TOP;
	}


	@ConfigItem(
			position = 6,
			keyName = "showPrayer",
			name = "Show Prayer",
			description = "Render Prayer overlay"
	)
	default boolean renderPrayer() { return false; }

	@ConfigSection(
			position = 7,
			name = "Prayer Bar Settings",
			description = "Prayer Bar Settings"
	)
	String prayerSettingsSection = "prayerSettings";

	@ConfigItem(
			position = 8,
			keyName = "prayerSize",
			name = "Prayer Bar Size",
			description = "Choose the size of the Prayer bar",
			section = prayerSettingsSection
	)
	@Range(
			min = MiniBarsConstants.WIDTH_MIN
	)
	default Dimension prayerSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, MiniBarsConstants.STANDARD_HEIGHT); }

	@ConfigItem(
			position = 9,
			keyName = "prayerFullnessDirection",
			name = "Prayer Fullness Direction",
			description = "Choose the direction of fullness of the Prayer bar",
			section = prayerSettingsSection
	)
	default FullnessDirection prayerFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 10,
			keyName = "prayerLabelPosition",
			name = "Prayer Label Position",
			description = "Choose the location of the Prayer bar's label",
			section = prayerSettingsSection
	)
	default LabelPlacement prayerLabelPosition()
	{
		return LabelPlacement.TOP;
	}


	@ConfigItem(
			position = 11,
			keyName = "showEnergy",
			name = "Show Energy",
			description = "Render Run Energy overlay"
	)
	default boolean renderEnergy() { return false; }

	@ConfigSection(
			position = 12,
			name = "Energy Bar Settings",
			description = "Energy Bar Settings"
	)
	String energySettingsSection = "energySettings";

	@ConfigItem(
			position = 13,
			keyName = "energySize",
			name = "Energy Bar Size",
			description = "Choose the size of the Energy bar",
			section = energySettingsSection
	)
	@Range(
			min = MiniBarsConstants.WIDTH_MIN
	)
	default Dimension energySize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, MiniBarsConstants.STANDARD_HEIGHT); }

	@ConfigItem(
			position = 14,
			keyName = "energyFullnessDirection",
			name = "Energy Fullness Direction",
			description = "Choose the direction of fullness of the Energy bar",
			section = energySettingsSection
	)
	default FullnessDirection energyFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 15,
			keyName = "energyLabelPosition",
			name = "Energy Label Position",
			description = "Choose the location of the Energy bar's label",
			section = energySettingsSection
	)
	default LabelPlacement energyLabelPosition()
	{
		return LabelPlacement.TOP;
	}


	@ConfigItem(
			position = 16,
			keyName = "showSpecial",
			name = "Show Special",
			description = "Render Special Attack overlay"
	)
	default boolean renderSpecial() { return false; }

	@ConfigSection(
			position = 17,
			name = "Special Bar Settings",
			description = "Special Bar Settings"
	)
	String specialSettingsSection = "specialSettings";

	@ConfigItem(
			position = 18,
			keyName = "specialSize",
			name = "Special Bar Size",
			description = "Choose the size of the Special bar",
			section = specialSettingsSection
	)
	@Range(
			min = MiniBarsConstants.WIDTH_MIN
	)
	default Dimension specialSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, MiniBarsConstants.STANDARD_HEIGHT); }

	@ConfigItem(
			position = 19,
			keyName = "specialFullnessDirection",
			name = "Special Fullness Direction",
			description = "Choose the direction of fullness of the Special bar",
			section = specialSettingsSection
	)
	default FullnessDirection specialFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 20,
			keyName = "specialLabelPosition",
			name = "Special Label Position",
			description = "Choose the location of the Special bar's label",
			section = specialSettingsSection
	)
	default LabelPlacement specialLabelPosition()
	{
		return LabelPlacement.TOP;
	}


	@ConfigItem(
			position = 21,
			keyName = "showLabels",
			name = "Show Labels",
			description = "Show labels with the minibars"
	)
	default boolean showLabels() { return true; }

	@ConfigItem(
			position = 22,
			keyName = "totalLabels",
			name = "Total Labels",
			description = "Whether to display labels as the value or percentage of the total value"
	)
	default boolean totalLabels() { return true; }

	@ConfigItem(
			position = 23,
			keyName = "enableRestorationBars",
			name = "Show Restores",
			description = "Visually shows how much will be restored to your bars."
	)
	default boolean enableRestorationBars() { return true; }

	@ConfigItem(
			position = 24,
			keyName = "hideAfterCombatDelay",
			name = "Hide after combat delay",
			description = "Amount of ticks before hiding status bars after no longer in combat. 0 = always show status bars."
	)
	@Units(Units.TICKS)
	default int hideAfterCombatDelay() { return 0; }

}
