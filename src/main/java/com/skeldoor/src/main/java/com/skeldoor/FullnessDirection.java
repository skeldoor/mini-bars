package net.runelite.client.plugins.minibars;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FullnessDirection
{
    LEFT("Left"),
    RIGHT("Right"),
    TOP("Top"),
    BOTTOM("Bottom");

    private final String name;

    @Override
    public String toString()
    {
        return getName();
    }
}
