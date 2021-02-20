public class Block {
    private final int size;
    private boolean isAllocated;
    private Process process;
    private int internalFragmentation;
    private static int idGenerator = 1;
    private int id;
    private int startAddress, endAddress;

   // Block next = null, previous = null;

    public Block(int size, boolean isAllocated, Process process) {
        this.size = size;
        setProcess(process);
    }

    public Block(int size) {
        this.size = size;
        process = null;
        isAllocated = false;
        internalFragmentation = size;
        id = idGenerator++;
        startAddress = endAddress = 0;
    }

    boolean isAllocated() {
        return isAllocated;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
        internalFragmentation = size - process.getSize();
        isAllocated = true;
    }

    public void releaseProcess() {
        this.process = null;
        isAllocated = false;
        internalFragmentation = size;
    }

    @Override
    public String toString() {
        return "Block " +
                "# " + id +
                " { size = " + size +" KB"+
                (isAllocated ? " and allocated with Process : " + process + ", and internal fragmentation size = " + internalFragmentation+" KB." : " , Not Allocated") +
                " , start from address " + getStartAddress() +
                " to address " + getEndAddress() +
                "}\n";
    }

    public int getSize() {
        return size;
    }

    public int getInternalFragmentation() {
        return internalFragmentation;
    }
    /*
     * TODO
     */

    public int getStartAddress() {
        return startAddress;
    }

    public int getEndAddress() {
        return endAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public void setEndAddress(int endAddress) {
        this.endAddress = endAddress;
    }

    public int getBlockID() {
        return id;
    }


}
