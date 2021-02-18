import java.util.ArrayList;

public class Memory {
    ArrayList<Block> blocks;
    private MemoryAllocation allocation;

    /**
     * @param blocks - array of memory blocks (partitions)
     */
    public Memory(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int allocate(MemoryAllocation allocationStrategy, Process process) {
        return allocationStrategy.allocate(process, this);
    }

    public int deallocate(Process process) {
        for (Block block : blocks) {
            if (block.isAllocated() && process.getName().equals(block.getProcess().getName())) {
                block.releaseProcess();
                return block.getBlockID();
            }

        }
        return -1;
    }

    public String statsReport() {
        return "";
    }

    @Override
    public String toString() {
        StringBuilder memoryString = new StringBuilder("\\*\\*\\MEMORY STATS/*/*/\n");
        for (Block block : blocks) {
            memoryString.append("\t").append(block).append("\n");
        }
        return memoryString.toString();
    }

    public void requestHandler(Request request) {
        switch (request.getType()) {
            case ALLOCATE:
                allocate(request.allocationType, request.getProcess());
            case DEALLOCATE:
                deallocate(request.getProcess());
            case REPORT:
                System.out.println(this);
        }
    }
}
