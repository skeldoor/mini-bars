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
	default Dimension healthSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 15 ); }

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
			keyName = "healthLabelStyle",
			name = "Health Label Style",
			description = "Choose the style of the Health bar's label",
			section = healthSettingsSection
	)
	default LabelStyle healthLabelStyle()
	{
		return LabelStyle.SHOW_CURRENT_AND_MAXIMUM;
	}

	@ConfigItem(
			position = 6,
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
			position = 7,
			keyName = "showPrayer",
			name = "Show Prayer",
			description = "Render Prayer overlay"
	)
	default boolean renderPrayer() { return false; }

	@ConfigSection(
			position = 8,
			name = "Prayer Bar Settings",
			description = "Prayer Bar Settings"
	)
	String prayerSettingsSection = "prayerSettings";

	@ConfigItem(
			position = 9,
			keyName = "prayerSize",
			name = "Prayer Bar Size",
			description = "Choose the size of the Prayer bar",
			section = prayerSettingsSection
	)
	default Dimension prayerSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 15 ); }

	@ConfigItem(
			position = 10,
			keyName = "prayerFullnessDirection",
			name = "Prayer Fullness Direction",
			description = "Choose the direction of fullness of the Prayer bar",
			section = prayerSettingsSection
	)
	default FullnessDirection prayerFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 11,
			keyName = "prayerLabelStyle",
			name = "Prayer Label Style",
			description = "Choose the style of the Prayer bar's label",
			section = prayerSettingsSection
	)
	default LabelStyle prayerLabelStyle()
	{
		return LabelStyle.SHOW_CURRENT_AND_MAXIMUM;
	}

	@ConfigItem(
			position = 12,
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
			position = 13,
			keyName = "showEnergy",
			name = "Show Energy",
			description = "Render Run Energy overlay"
	)
	default boolean renderEnergy() { return false; }

	@ConfigSection(
			position = 14,
			name = "Energy Bar Settings",
			description = "Energy Bar Settings"
	)
	String energySettingsSection = "energySettings";

	@ConfigItem(
			position = 15,
			keyName = "energySize",
			name = "Energy Bar Size",
			description = "Choose the size of the Energy bar",
			section = energySettingsSection
	)
	default Dimension energySize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 15 ); }

	@ConfigItem(
			position = 16,
			keyName = "energyFullnessDirection",
			name = "Energy Fullness Direction",
			description = "Choose the direction of fullness of the Energy bar",
			section = energySettingsSection
	)
	default FullnessDirection energyFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 17,
			keyName = "energyLabelStyle",
			name = "Energy Label Style",
			description = "Choose the style of the Energy bar's label",
			section = energySettingsSection
	)
	default LabelStyle energyLabelStyle()
	{
		return LabelStyle.SHOW_CURRENT_AND_MAXIMUM;
	}

	@ConfigItem(
			position = 18,
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
			position = 19,
			keyName = "showSpecial",
			name = "Show Special",
			description = "Render Special Attack overlay"
	)
	default boolean renderSpecial() { return false; }

	@ConfigSection(
			position = 20,
			name = "Special Bar Settings",
			description = "Special Bar Settings"
	)
	String specialSettingsSection = "specialSettings";

	@ConfigItem(
			position = 21,
			keyName = "specialSize",
			name = "Special Bar Size",
			description = "Choose the size of the Special bar",
			section = specialSettingsSection
	)
	default Dimension specialSize() { return new Dimension(ComponentConstants.STANDARD_WIDTH, 15 ); }

	@ConfigItem(
			position = 22,
			keyName = "specialFullnessDirection",
			name = "Special Fullness Direction",
			description = "Choose the direction of fullness of the Special bar",
			section = specialSettingsSection
	)
	default FullnessDirection specialFullnessDirection() { return FullnessDirection.RIGHT; }

	@ConfigItem(
			position = 23,
			keyName = "specialLabelStyle",
			name = "Special Label Style",
			description = "Choose the style of the Special bar's label",
			section = specialSettingsSection
	)
	default LabelStyle specialLabelStyle()
	{
		return LabelStyle.SHOW_CURRENT_AND_MAXIMUM;
	}

	@ConfigItem(
			position = 24,
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
			position = 25,
			keyName = "enableRestorationBars",
			name = "Show Restores",
			description = "Visually shows how much will be restored to your bars."
	)
	default boolean enableRestorationBars() { return true; }

	@ConfigItem(
			position = 26,
			keyName = "barOutlineThickness",
			name = "Outline Effects Thickness",
			description = "How thick to draw an outline around the bars to show regeneration, stamina potion duration, etc."
	)
	@Range(
			min = 0,
			max = 5
	)
	default int outlineThickness() { return 3; }

	@ConfigItem(
			position = 27,
			keyName = "hideAfterCombatDelay",
			name = "Hide after combat delay",
			description = "Amount of ticks before hiding status bars after no longer in combat. 0 = always show status bars."
	)
	@Units(Units.TICKS)
	default int hideAfterCombatDelay() { return 0; }

}
