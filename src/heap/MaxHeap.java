package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.swap;

public class MaxHeap {
    List<Integer> heap = new ArrayList<>();
    public int size() {
        return heap.size();
    }


    public void heapify(int index) {
        int size = heap.size();
        while (true) {
            int largest = index;
            int leftIdx = 2 * index + 1;
            int rightIdx = 2 * index + 2;

            if (leftIdx < size && heap.get(leftIdx) > heap.get(largest)) largest = leftIdx;
            if (rightIdx < size && heap.get(rightIdx) > heap.get(largest)) largest = rightIdx;

            if (largest == index) break;   // heap property restored

            swap(heap, index, largest);
            index = largest;               // leftIdx/rightIdx recalculated at top of loop
        }
    }

    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            if (heap.get(parentIdx) < heap.get(index)) {
                swap(heap, parentIdx, index);
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    public int peek() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    public int pop() {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        int lastIdx = heap.size() - 1;
        swap(heap, 0, lastIdx);
        int removed = heap.remove(lastIdx);
        if (!heap.isEmpty()) heapify(0);   // skip heapify on empty heap
        return removed;
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }

}