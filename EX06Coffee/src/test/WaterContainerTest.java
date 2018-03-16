package test;

import ee.ttu.iti0202.coffee.coffemakercontainer.WaterContainer;
import org.junit.Test;

import static org.junit.Assert.*;

public class WaterContainerTest {

    @Test
    public void testWaterContainer() {
        WaterContainer waterContainer = new WaterContainer(100);

        assertEquals(100, waterContainer.waterLeft());
        waterContainer.useWater(50);
        assertEquals(50, waterContainer.waterLeft());
        waterContainer.useWater(-20);
        assertEquals(50, waterContainer.waterLeft());
        waterContainer.useWater(200);
        assertEquals(0, waterContainer.waterLeft());
        assertTrue(waterContainer.isEmpty());
        waterContainer.fillTank();
        assertEquals(100, waterContainer.waterLeft());
    }

}
