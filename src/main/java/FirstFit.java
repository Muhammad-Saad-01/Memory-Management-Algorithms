public class FirstFit implements AllocationAlgorithm {

    /**
     * Assigns the process to the first available open memory segment of sufficient size
     * @param process   - The process to be allocated
     * @param memory    - A reference to the Memory instance the Algorithm is instantiated in
     * @return          - No. of nodes traversed to find proper location (-1 if allocation failed)
     */
    @Override
    public int allocateMemory(Process process, Memory memory) {
        int id = -1;

        for (Block block : memory.getBlocks()) {
            int difference = block.getSize() - process.getSize();
            if (!block.isAllocated() && difference >= 0 ) {
                id = block.getBlockID();
                break;
            }
        }
        if (id != -1) {
            for (Block block : memory.getBlocks()) {
                if (block.getBlockID() == id)
                    block.setProcess(process);
            }
        }
        return id;
    }
}
