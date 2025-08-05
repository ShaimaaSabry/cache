package shaimaa.cache;

class CacheFactory {

    private CacheFactory() {
        // Prevent instantiation
    }

    static Cache create(Cache.Type type, int size) {
        switch (type) {
            case FIFO -> {
                return new FifoCache(size);
            }

            case LRU -> {
                return new LruCache(size);
            }

            case LFU -> {
                return new LfuCache(size);
            }
        }

        throw new IllegalArgumentException("Invalid cache type");
    }
}
