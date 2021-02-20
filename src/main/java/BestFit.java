public class BestFit implements AllocationAlgorithm {
    private final int MAX_BLOCK_SIZE = 256;

    /**
     * Assigns the process to the best available open memory segment of sufficient size
     *
     * @param process - The process to be allocated
     * @param memory  - A reference to the Memory instance the Algorithm is instantiated in
     * @return - Block id of allocated process (-1 if allocation failed)
     */
    @Override
    public int allocateMemory(Process process, Memory memory) {
        int id = -1;
        int smallestDifferance = MAX_BLOCK_SIZE;
        for (Block block : memory) {
            int difference = block.getSize() - process.getSize();
            if (!block.isAllocated() && difference >= 0 && difference <= smallestDifferance) {
                smallestDifferance = difference;
                id = block.getBlockID();
            }
        }
        if (id != -1) {
            for (Block block : memory) {
                if (block.getBlockID() == id)
                    block.setProcess(process);
            }
        }
        return id;
    }

}
