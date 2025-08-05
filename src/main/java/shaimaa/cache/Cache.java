package shaimaa.cache;

import java.util.HashMap;
import java.util.Map;

abstract class Cache {
    enum Type {
        FIFO,
        LRU,
        LFU
    }

    int size;
    Map<String, String> cache = new HashMap<>();

    Cache(int size) {
        this.size = size;
    }

    void set(String key, String value) {
        this.cache.put(key, value);
    }

    String get(String key) {
        return this.cache.get(key);
    }

    abstract void print();
}
