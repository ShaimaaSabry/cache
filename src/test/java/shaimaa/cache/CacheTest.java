package shaimaa.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class CacheTest {

    @Test
    void givenFifoCache_whenCacheIsFull_thenShouldRemoveFirstItemIn() {
        // given
        Cache cache = CacheFactory.create(Cache.Type.FIFO, 5);

        cache.set("1", "1");
        cache.set("2", "2");
        cache.set("3", "3");
        cache.set("4", "4");
        cache.set("5", "5");
        cache.print();

        // when
        cache.set("6", "6");
        cache.print();


        // then
        assertNull(cache.get("1"));
    }

    @Test
    void givenLruCache_whenCacheIsFull_thenShouldRemoveLeastRecentlyUsedItem() {
        // given
        Cache cache = CacheFactory.create(Cache.Type.LRU, 5);

        cache.set("1", "1");
        cache.set("2", "2");
        cache.set("3", "3");
        cache.set("4", "4");
        cache.set("5", "5");
        cache.print();

        // when
        cache.get("1");
        cache.get("3");
        cache.get("4");
        cache.get("5");

        cache.set("6", "6");
        cache.print();

        // then
        assertNull(cache.get("2"));
    }

    @Test
    void givenLruCache_whenCacheIsFull_andItemAccessed_thenShouldRemoveLeastFrequentlyUsedItem() {
        // given
        Cache cache = CacheFactory.create(Cache.Type.LFU, 5);

        cache.set("1", "1");
        cache.set("2", "2");
        cache.set("3", "3");
        cache.set("4", "4");
        cache.set("5", "5");
        cache.print();

        // when
        cache.get("1");
        cache.get("3");
        cache.get("4");
        cache.get("5");

        cache.set("6", "6");
        cache.print();

        // then
        assertNull(cache.get("2"));
    }
}
