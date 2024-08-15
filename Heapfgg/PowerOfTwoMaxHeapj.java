import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeapj {
    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(1);
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);

        System.out.println(heap.popMax());
        System.out.println(heap.popMax());
        System.out.println(heap.popMax());


        PowerOfTwoMaxHeap largeHeap = new PowerOfTwoMaxHeap(3);

    }




    public static final class PowerOfTwoMaxHeap {
        private final ArrayList<Integer> heap;
        private int numChildrenExponent;
        private final int numChildren;  // This will be 2^numChildrenExponent

        public PowerOfTwoMaxHeap(int numChildrenExponent) {
            if (numChildrenExponent < 0) {
                throw new IllegalArgumentException("numChildrenExponent must be non-negative");
            }
            this.numChildrenExponent = numChildrenExponent;
            this.numChildren = 1 << numChildrenExponent;  // This calculates 2^numChildrenExponent
            this.heap = new ArrayList<>();
        }

        public void insert(int value) {
            heap.add(value);
            bubbleUp(heap.size() - 1);
        }

        public int popMax() {
            if (heap.isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }

            int max = heap.get(0);
            int lastElement = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                bubbleDown(0);
            }

            return max;
        }

        private void bubbleUp(int index) {
            int parentIndex = (index - 1) / numChildren;
            while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / numChildren;
            }
        }

        private void bubbleDown(int index) {
            while (true) {
                int largestIndex = index;
                for (int i = 1; i <= numChildren; i++) {
                    int childIndex = numChildren * index + i;
                    if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largestIndex)) {
                        largestIndex = childIndex;
                    }
                }

                if (largestIndex == index) {
                    break;
                }

                swap(index, largestIndex);
                index = largestIndex;
            }
        }

        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public int size() {
            return heap.size();
        }
    }

}
