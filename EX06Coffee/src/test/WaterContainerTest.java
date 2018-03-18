package test;

import ee.ttu.iti0202.coffee.coffemakercontainer.WaterContainer;
import org.junit.Test;

import static org.junit.Assert.*;

public class WaterContainerTest {

    public static final int testWaterAmount = 50;
    public static final int testWaterAmount2 = 50;
    public static final int AMOUNT = -20;
    public static final int AMOUNT1 = 200;
    public static final int EXPECTED = 100;
    public static final int EXPECTED1 = 50;
    public static final int EXPECTED2 = 100;
    public static final int EXPECTED3 = 0;
    public static final int TANK_CAPACITY = 100;

    @Test
    public void testWaterContainer() {
        WaterContainer waterContainer = new WaterContainer(TANK_CAPACITY);

        assertEquals(EXPECTED2, waterContainer.waterLeft());
        waterContainer.useWater(testWaterAmount);
        assertEquals(testWaterAmount2, waterContainer.waterLeft());
        waterContainer.useWater(AMOUNT);
        assertEquals(EXPECTED1, waterContainer.waterLeft());
        waterContainer.useWater(AMOUNT1);
        assertEquals(EXPECTED3, waterContainer.waterLeft());
        assertTrue(waterContainer.isEmpty());
        waterContainer.fillTank();
        assertEquals(EXPECTED, waterContainer.waterLeft());
    }

}
