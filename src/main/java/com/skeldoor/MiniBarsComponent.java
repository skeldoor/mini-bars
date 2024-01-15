/*
 * Copyright (c) 2019, Jos <Malevolentdev@gmail.com>
 * Copyright (c) 2019, Rheon <https://github.com/Rheon-D>
 * Copyright (c) 2024, Seung <swhahm94@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.minibars;

import java.awt.*;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.components.TextComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

@RequiredArgsConstructor
class MiniBarsComponent
{
    private static final Color BACKGROUND = new Color(0, 0, 0, 150);
    private static final Color OVERHEAL_COLOR = new Color(216, 255, 139, 150);
    private static final int BORDER_SIZE = 1;
    private static final DecimalFormat df = new DecimalFormat("0");

    private final Supplier<Integer> maxValueSupplier;
    private final Supplier<Integer> currentValueSupplier;
    private final Supplier<Integer> healSupplier;
    private final Supplier<Color> colorSupplier;
    private final Supplier<Color> healColorSupplier;
    private int maxValue;
    private int currentValue;

    private void refreshSkills()
    {
        maxValue = maxValueSupplier.get();
        currentValue = currentValueSupplier.get();
    }

    void renderBar(MiniBarsConfig config, Graphics2D graphics, PanelComponent component, FullnessDirection dir, LabelPlacement labelLoc, int width, int height)
    {
        PanelComponent boundingBox = new PanelComponent();
        boundingBox.setBorder( new Rectangle( component.getBounds().x, component.getBounds().y, width, height ) );
        boundingBox.setPreferredSize( new Dimension( width, height ) );
        //boundingBox.setBackgroundColor( MiniBarsConstants.COLOUR_TRANSPARENT );
        component.getChildren().add(boundingBox);


        // start by assuming the bar will be filled rightward
        int eX = component.getBounds().x;
        int eY = component.getBounds().y;
        int filledWidth = getBarSize(maxValue, currentValue, width);
        int filledHeight = height;

        if ( dir == FullnessDirection.TOP )
        {
            filledHeight = getBarSize( maxValue, currentValue, height );
            filledWidth = width;
            eY = height - filledHeight;
        }
        else if ( dir == FullnessDirection.BOTTOM )
        {
            filledHeight = getBarSize( maxValue, currentValue, height );
            filledWidth = width;
        }
        else if ( dir == FullnessDirection.LEFT )
        {
            eX = width - filledWidth;
        }

        final Color fill = colorSupplier.get();






        refreshSkills();

        graphics.setColor(BACKGROUND);
        graphics.drawRect(component.getBounds().x, component.getBounds().y, width, height);
        graphics.fillRect(component.getBounds().x, component.getBounds().y, width, height );

        graphics.setColor(fill);
        graphics.fillRect(eX + BORDER_SIZE,
                eY + BORDER_SIZE,
                filledWidth - BORDER_SIZE,
                filledHeight - BORDER_SIZE);

        if (config.enableRestorationBars())
        {
            renderRestore(config, graphics, dir, component.getBounds().x, component.getBounds().y, width, height);
        }

        if ( config.showLabels() )
        {
            renderText(config, graphics, labelLoc, component.getBounds().x, component.getBounds().y, width, height );
        }
    }

    private void renderText(MiniBarsConfig config, Graphics2D graphics, LabelPlacement labelLoc, int x, int y, int barWidth, int barHeight )
    {
        graphics.setFont(FontManager.getRunescapeSmallFont());
        String counterText = Integer.toString(currentValue);
        if ( !config.totalLabels() )
        {
            df.setRoundingMode( RoundingMode.DOWN );
            counterText = df.format( (float) (currentValue * 100) / maxValue ) + "%";
        }

        int sizeOfCounterX = graphics.getFontMetrics().stringWidth(counterText);
        int sizeOfCounterY = graphics.getFontMetrics().getHeight();
        int xOffset = (barWidth / 2) - (sizeOfCounterX / 2);
        int yOffset = 0;

        if ( labelLoc == LabelPlacement.CENTRE )
        {
            xOffset = (barWidth / 2) - (sizeOfCounterX / 2);
            yOffset = (barHeight / 2);
        }
        else if ( labelLoc == LabelPlacement.BOTTOM )
        {
            yOffset = barHeight + sizeOfCounterY;
        }
        else if ( labelLoc == LabelPlacement.LEFT )
        {
            xOffset = -(int) Math.floor(sizeOfCounterX * 1.125);
            yOffset = (barHeight / 2);
        }
        else if ( labelLoc == LabelPlacement.RIGHT )
        {
            xOffset = barWidth + 2;
            yOffset = (barHeight / 2);
        }

        final TextComponent textComponent = new TextComponent();
        textComponent.setText( counterText );
        textComponent.setPosition( new Point(x + xOffset, y + yOffset) );
        textComponent.render( graphics );
    }

    private void renderRestore(MiniBarsConfig config, Graphics2D graphics, FullnessDirection dir, int x, int y, int width, int height)
    {
        final Color color = healColorSupplier.get();
        final int heal = healSupplier.get();

        if (heal <= 0)
        {
            return;
        }

        int filledCurrentSize = getBarSize(maxValue, currentValue, width);
        int filledHealSize = getBarSize(maxValue, heal, width);
        int fillX = x, fillY = y, fillWidth = width, fillHeight = height, fillThreshold = width;
        graphics.setColor(color);

        if ( dir == FullnessDirection.TOP )
        {
            fillThreshold = height;
            filledCurrentSize = getBarSize( maxValue, currentValue, height );
            filledHealSize = getBarSize( maxValue, heal, height );

            fillX = x;
            fillY = y + height - filledCurrentSize - filledHealSize;

            fillWidth = width;
            fillHeight = filledHealSize + 1;
        }
        else if ( dir == FullnessDirection.BOTTOM )
        {
            fillThreshold = height;
            filledCurrentSize = getBarSize( maxValue, currentValue, height );
            filledHealSize = getBarSize( maxValue, heal, height );

            //fillX = x;
            fillY = y + filledCurrentSize - 1;

            fillWidth = width;
            fillHeight = filledHealSize + 1;
        }
        else if ( dir == FullnessDirection.LEFT )
        {
            //fillThreshold = width;
            filledCurrentSize = getBarSize( maxValue, currentValue, width );
            filledHealSize = getBarSize( maxValue, heal, width );

            fillX = x + width - filledCurrentSize - filledHealSize;
            fillY = y;

            fillWidth = filledHealSize + 1;
            fillHeight = height;
        }
        else if ( dir == FullnessDirection.RIGHT )
        {
            //fillThreshold = width;
            filledCurrentSize = getBarSize( maxValue, currentValue, width );
            filledHealSize = getBarSize( maxValue, heal, width );

            fillX = x + filledCurrentSize - 1;
            fillY = y;

            fillWidth = filledHealSize + 1;
            fillHeight = height;
        }

        if ( filledHealSize + filledCurrentSize > fillThreshold )
        {
            graphics.setColor( OVERHEAL_COLOR );

            if ( dir == FullnessDirection.TOP )
            {
                fillHeight = height - filledCurrentSize + 1;
                fillY = y;
            }
            else if ( dir == FullnessDirection.BOTTOM )
            {
                fillHeight = height - filledCurrentSize + 1;
            }
            else if ( dir == FullnessDirection.LEFT )
            {
                fillWidth = width - filledCurrentSize + 1;
                fillX = x;
            }
            else if ( dir == FullnessDirection.RIGHT )
            {
                fillWidth = width - filledCurrentSize + 1;
            }

        }

        graphics.fillRect( fillX + BORDER_SIZE , fillY + BORDER_SIZE, fillWidth - BORDER_SIZE, fillHeight - BORDER_SIZE );
    }

    private static int getBarSize(int base, int current, int size)
    {
        final double ratio = (double) current / base;

        if (ratio >= 1)
        {
            return size;
        }

        return (int) Math.round(ratio * size);
    }
}