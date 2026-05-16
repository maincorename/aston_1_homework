import java.util.Objects;

public class CustomHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public CustomHashMap() {
        buckets = (Node<K, V>[]) new Node[capacity];
    }

    private int capacity = 16;
    private int size = 0;
    private float loadFactor = 0.75f;
    private Node<K, V>[] buckets;

    private int indexForKey(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();

        return (hash & Integer.MAX_VALUE) % capacity; // положительный хеш в пределах вместимости
    }

    public void put (K key, V value) {
        int index = indexForKey(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // добавляем в начало списка новый узел
        buckets[index] = new Node<>(key, value, buckets[index]);
        size++;

        if ((float) size / capacity > loadFactor) {
            resize();
        }
    }

    public V get (K key) {
        int index = indexForKey(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public V remove (K key) {
        int index = indexForKey(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    private void resize () {
        capacity = capacity * 2;

        Node<K, V>[] newBuckets = (Node<K,V>[]) new Node[capacity];

        // рехеширование существующих узлов
        for (Node<K, V> head : buckets) {
            while (head != null) {
                Node<K, V> next = head.next;
                int newIndex = (head.key == null ? 0 :
                        (head.key.hashCode() & Integer.MAX_VALUE) % capacity);
                head.next = newBuckets[newIndex];
                newBuckets[newIndex] = head;
                head = next;
            }
        }
        buckets = newBuckets;
    }
}
