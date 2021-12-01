import java.util.NoSuchElementException;

public class SimpleWzimBoundedQueue<E> implements WzimBoundedQueue<E> {

    private Object[] queue;
    private int maxCapacity;
    private int head = 1;
    private int tail = 0;
    private int currentSize = 0;

    public SimpleWzimBoundedQueue(final int maxCapacity) {
        this.queue = new Object[maxCapacity];
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean add(E e) {
        if (maxCapacity == currentSize) {
            throw new IllegalStateException("Kolejka jest pełna!");
        }
        tail++;
        queue[tail] = e;
        currentSize++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (maxCapacity == currentSize) {
            return false;
        }
        tail++;
        queue[tail] = e;
        currentSize++;
        return true;
    }

    @Override
    public E remove() {
        if (currentSize == 0) {
            throw new NoSuchElementException("Kolejka jest pusta!");
        }
        currentSize--;
        Object valueToRemove = queue[head];
        head++;
        return (E) valueToRemove;
    }

    @Override
    public E poll() {
        if (currentSize == 0) {
            return null;
        }
        currentSize--;
        Object valueToRemove = queue[head];
        head++;
        return (E) valueToRemove;
    }

    @Override
    public E element() {
        if (currentSize == 0) {
            throw new NoSuchElementException("Kolejka jest pusta!");
        }
        return (E) queue[head];
    }

    @Override
    public E peek() {
        return (E) queue[head];
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public int capacity() {
        return maxCapacity;
    }

    public static void main(String[] args) {
        SimpleWzimBoundedQueue<Integer> q = new SimpleWzimBoundedQueue<Integer>(4);
        q.add(40);
        q.add(56);
        q.add(30);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.size());
        System.out.println(q.capacity());
    }
}
