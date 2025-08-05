package shaimaa.cache;

import java.util.*;
import java.util.stream.Collectors;

class LfuCache extends Cache {
    Map<String, Integer> count = new HashMap<>();

    LfuCache(int size) {
        super(size);
    }

    void set(String key, String value) {
        if (this.count.size() >= this.size) {
            this.count = this.count
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) ->e1, LinkedHashMap::new
                            )
                    );

            String least = new ArrayList<>(this.count.keySet()).get(0);
            this.count.remove(least);

            this.cache.remove(least);
        }

        this.count.put(key, this.count.getOrDefault(key, 0) + 1);

        super.set(key, value);
    }

    String get(String key) {
        String val = super.get(key);

        if (val != null) {
            this.count.put(key, this.count.get(key) + 1);
        }

        return super.get(key);
    }

    @Override
    void print() {
        for(Map.Entry<String, Integer> entry : this.count.entrySet()) {
            System.out.print(entry.getKey() + " (" + entry.getValue() + ") , ");
        }
        System.out.println();
    }
}
