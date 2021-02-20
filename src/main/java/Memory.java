import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Memory implements Iterable<Block> {
    private final BlockList blockList;
    private int numberOfAllocatedBlocks = 0;
    private int totalSizeOfAllocatedProcesses = 0;
    private int totalFragmentedSize = 0;
    private int totalSizeOfNonAllocatedBlocks;

    /**
     * @param blockList - LinkedList of memory blocks (partitions)
     */
    public Memory(BlockList blockList) {
        this.blockList = blockList;
        totalSizeOfNonAllocatedBlocks = blockList.getSize();
    }

    public BlockList getBlockList() {
        return blockList;
    }

    public int allocate(MemoryAllocation allocationStrategy, Process process) {
        int result = allocationStrategy.allocate(process, this);
        if (result != -1) {
            numberOfAllocatedBlocks++;
            totalSizeOfAllocatedProcesses += process.getSize();
            totalFragmentedSize += blockList.getBlock(result).getSize() - process.getSize();
            totalSizeOfNonAllocatedBlocks -= blockList.getBlock(result).getSize();
        }
        return result;
    }

    public int deallocate(Process process) {
        for (Block block : blockList) {
            if (block.isAllocated() && process.getName().equals(block.getProcess().getName())) {
                block.releaseProcess();
                numberOfAllocatedBlocks--;
                totalSizeOfAllocatedProcesses -= process.getSize();
                totalSizeOfNonAllocatedBlocks += block.getSize();
                return block.getBlockID();
            }
        }
        return -1;
    }

    public String statsReport() {
        return "*-*-*-*-*| Memory Statistics Report |*-*-*-*-*\n" +
                "> Summary Report:\n" +
                "---------------------------------------------------------------------\n" +
                "Memory Consist of " + blockList.getNumberOfBlocks() + " Blocks with total size = " + blockList.getSize() + " KB" +
                (numberOfAllocatedBlocks != 0 ? String.format("\nand allocated with %d processes with total size = %d KB in %d blocks Causes %d KB of Fragmentation." +
                                "\nin other words..." +
                                "\nTotal used memory = %d KB (actually in use = %d KB , Fragmented = %d KB )." +
                                "\nTotal unused memory = %d\n",
                        numberOfAllocatedBlocks,
                        totalSizeOfAllocatedProcesses,
                        numberOfAllocatedBlocks,
                        totalFragmentedSize,
                        (totalFragmentedSize + totalSizeOfAllocatedProcesses),
                        totalSizeOfAllocatedProcesses,
                        totalFragmentedSize,
                        totalSizeOfNonAllocatedBlocks) : " (all are unused).\n") +
                "\n--------------------------------------------------------------------\n" +
                "> Detailed Report:\n" +
                blockList + "\n" + "*-".repeat(30);
    }


    public void requestHandler(Request request) {
        int blockID;
        if (request.getType() == RequestType.ALLOCATE) {
            blockID = allocate(request.getAllocationType(), request.getProcess());
            if (blockID != -1)
                System.out.printf("Process successfully allocated in block number %d \n", blockID);
            else
                System.out.println("there is insufficient memory space to allocate to this process!");
        } else if (request.getType() == RequestType.DEALLOCATE) {
            blockID = deallocate(request.getProcess());
            if (blockID != -1)
                System.out.printf("Process successfully deallocated from block number %d \n", blockID);
            else
                System.out.println("there is no such process in memory!");
        } else {
            writeReport();
        }

    }

    public void writeReport() {
        try {
            writeFileFromString(statsReport());
            System.out.println("Report successfully written in report.txt");
        } catch (IOException e) {
            System.out.println("Error while writing report to file report.txt" + e.getMessage());
        }
    }

    private static void writeFileFromString(String data) throws IOException {
        File outFile = new File("report.txt");
        FileWriter writer = new FileWriter(outFile);
        writer.write(String.valueOf(data));
        writer.close();
    }

    @Override
    public String toString() {
        return statsReport();
    }

    @Override
    public Iterator<Block> iterator() {
        return new Iterator<Block>() {
            private final Iterator<Block> iter = blockList.iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Block next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed");
            }

        };
    }
}
