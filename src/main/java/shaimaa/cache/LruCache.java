package shaimaa.cache;

import java.util.LinkedList;

class LruCache extends Cache {
    LinkedList<String> lru = new LinkedList<>();

    LruCache(int size) {
        super(size);
    }

    void set(String key, String value) {
        if (this.cache.size() >= this.size) {
            String least = this.lru.getLast();
            this.lru.removeLast();
            this.cache.remove(least);
        }

        this.lru.remove(key);
        this.lru.addFirst(key);

        super.set(key, value);
    }

    String get(String key) {
        this.lru.remove(key);
        this.lru.addFirst(key);

        return super.get(key);
    }

    void print() {
        for (String s : this.lru) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
