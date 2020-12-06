package Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyDoubleLinkedlist<T> implements List<T> {

    protected int size;
    protected LinkedListNode<T> f, b;

    public MyDoubleLinkedlist() {
        this.size = 0;
        this.f = null;
        this.b = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T curr : this) {
            if (curr.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private LinkedListNode<T> curr = f;

            @Override
            public boolean hasNext() {
                return this.curr != null;
            }

            @Override
            public T next() {
                T re = this.curr.val;
                this.curr = this.curr.next;
                return re;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        T[] re = (T[]) new Object[this.size()];
        Iterator<T> iter = this.iterator();
        for (int i = 0; i < this.size() && iter.hasNext(); i++) {
            re[i] = iter.next();
        }
        return re;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (this.f == null) {
            this.f = new LinkedListNode<>(t);
            this.b = this.f;
        } else {
            this.b.next = new LinkedListNode<>(t, null, this.b);
            this.b = this.b.next;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.size = 0;
        this.f = null;
        this.b = null;
    }

    @Override
    public T get(int index) {
        if (index > this.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<T> curr = getNodeAtIndex(index);
        return curr.val;
    }

    @Override
    public T set(int index, T element) {
        if (index > this.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<T> curr = getNodeAtIndex(index);
        T re = curr.val;
        curr.val = element;
        return re;
    }

    @Override
    public void add(int index, T element) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.size()) {
            this.add(element);
        } else if (index == 0) {
            LinkedListNode<T> toAdd = new LinkedListNode<>(element, this.f, null);
            this.f.prev = toAdd;
            this.f = toAdd;
        } else {
            LinkedListNode<T> curr = getNodeAtIndex(index);
            LinkedListNode<T> toAdd = new LinkedListNode<>(element, curr, curr.prev);
            curr.prev.next = toAdd;
            curr.prev = toAdd;
        }
        this.size++;
    }

    @Override
    public T remove(int index) {
        if (index > this.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<T> curr = getNodeAtIndex(index);
        if (curr.prev == null) {
            this.f = curr.next;
        } else {
            curr.prev.next = curr.next;
        }
        if (curr.next == null) {
            this.b = curr.prev;
        } else {
            curr.next.prev = curr.prev;
        }
        this.size--;
        return curr.val;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected class LinkedListNode<E> {
        protected E val;
        protected LinkedListNode<E> next;
        protected LinkedListNode<E> prev;
        protected LinkedListNode (E v, LinkedListNode<E> n, LinkedListNode<E> p) {
            this.val = v;
            this.next = n;
            this.prev = p;
        }
        protected LinkedListNode (E v) {
            this(v, null, null);
        }
    }

    private LinkedListNode<T> getNodeAtIndex(int index) {
        LinkedListNode<T> re = this.f;
        while (index > 0) {
            index--;
            re = re.next;
        }
        return re;
    }
}
