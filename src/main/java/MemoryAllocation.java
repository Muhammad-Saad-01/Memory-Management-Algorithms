public class MemoryAllocation {
    AllocationAlgorithm algorithm;

    public MemoryAllocation(AllocationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int allocate(Process process, Memory memory) {
        return this.algorithm.allocateMemory(process, memory);
    }

    public void setAllocationAlgorithm(AllocationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
