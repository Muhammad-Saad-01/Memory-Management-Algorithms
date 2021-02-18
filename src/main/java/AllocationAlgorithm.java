/**
 * AllocationAlgorithm is an interface that will allow Memory
 * to instantiate any implementation of a memory allocation
 * algorithm and use it in the same way.
 */
public interface AllocationAlgorithm {
    /**
     * Allocates the Process to an appropriate spot in the memory based
     * on the implementation of this interface
     * @param process   - The Process to be allocated
     * @param memory    - A reference to the Memory object that the algorithm will act on
     * @return          - The number of nodes the algorithm traversed (-1 if allocation failed)
     */
    int allocateMemory(Process process, Memory memory);
}
