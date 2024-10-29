package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GlidedRoseTest {


    @Test
    void testAgedBrieIncreasesQualityBeforeSellDate() {
        Item[] items = { new Item("Aged Brie", 5, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(11, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    void testAgedBrieIncreasesQualityAfterSellDate() {
        Item[] items = { new Item("Aged Brie", 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(12, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void testAgedBrieQualityMax50() {
        Item[] items = { new Item("Aged Brie", 5, 50) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesQualityAtSellDateBoundary() {
        Item[] items = { new Item("Aged Brie", 1, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(11, items[0].quality);
        assertEquals(0, items[0].sellIn);
    }


    @Test
    void testSulfurasQualityStaysConstant() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(5, items[0].sellIn);
    }

    @Test
    void testSulfurasSellInRemainsConstant() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(0, items[0].sellIn);
    }

    @Test
    void testBackstagePassIncreasesQualityMoreThan10Days() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(21, items[0].quality);
        assertEquals(14, items[0].sellIn);
    }

    @Test
    void testBackstagePassIncreasesQualityLessThan10Days() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(22, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testBackstagePassIncreasesQualityLessThan5Days() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(23, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    void testBackstagePassDropsQualityAfterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void testBackstagePassQualityMax50() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }


    @Test
    void testRegularItemQualityDecreasesBeforeSellDate() {
        Item[] items = { new Item("+5 Dexterity Vest", 5, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    void testRegularItemQualityDecreasesTwiceAfterSellDate() {
        Item[] items = { new Item("+5 Dexterity Vest", 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void testRegularItemQualityNotNegative() {
        Item[] items = { new Item("+5 Dexterity Vest", 5, 0) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    void testConjuredItemQualityNotNegative() {
        Item[] items = { new Item("Conjured Mana Cake", 5, 1) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }


    @Test
    void testItemWithNegativeSellInValue() {
        Item[] items = { new Item("+5 Dexterity Vest", -1, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }

    @Test
    void testItemWithSellIn0() {
        Item[] items = { new Item("+5 Dexterity Vest", 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }

    @Test
    void testItemWithQuality50() {
        Item[] items = { new Item("Aged Brie", 5, 50) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    @Test
    void testItemWithQuality0() {
        Item[] items = { new Item("+5 Dexterity Vest", 5, 0) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    void testQualityDegradesBy2AfterSellDate() {
        Item[] items = { new Item("Elixir of the Mongoose", -1, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }

    @Test
    void testQualityOfMultipleItems() {
        Item[] items = { new Item("Aged Brie", 2, 0), new Item("+5 Dexterity Vest", 5, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(1, items[0].quality);
        assertEquals(9, items[1].quality);
    }
}
