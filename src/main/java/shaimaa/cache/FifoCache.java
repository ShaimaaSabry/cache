package shaimaa.cache;

import java.util.LinkedList;
import java.util.Queue;

class FifoCache extends Cache {
    Queue<String> queue = new LinkedList<>();

    FifoCache(int size) {
        super(size);
    }

    void set(String key, String value) {
        if (this.queue.size() >= this.size) {
            String first = this.queue.poll();
            cache.remove(first);
        }

        this.queue.add(key);

        super.set(key, value);
    }

    void print() {
        for (String s : this.queue) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
