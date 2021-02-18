public class Block {
    private final int size;
    private boolean isAllocated;
    private Process process;
    private int nonAllocatedSize;
    private static int idGenerator = 1;
    private int id;

    public Block(int size, boolean isAllocated, Process process) {
        this.size = size;
        setProcess(process);
    }

    public Block(int size) {
        this.size = size;
        process = null;
        isAllocated = false;
        nonAllocatedSize = size;
        id = idGenerator++;
    }

    boolean isAllocated() {
        return isAllocated;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
        nonAllocatedSize = size - process.getSize();
        isAllocated = true;
    }

    public void releaseProcess() {
        this.process = null;
        isAllocated = false;
        nonAllocatedSize = size;
    }

    @Override
    public String toString() {
        return "Block " +
                "id = " + id +
                " { size = " + size +
                (isAllocated ? " and allocated with Process : " + process + ", and non Allocated size = " + nonAllocatedSize : " , Not Allocated") +
                "}";
    }

    public int getSize() {
        return size;
    }

    public int getNonAllocatedSize() {
        return nonAllocatedSize;
    }
    /*
     * TODO
     */

    public byte getStartAddress() {
        return Byte.parseByte(super.toString());
    }

    public byte getEndAddress() {
        return Byte.parseByte(super.toString() + size);
    }

    public int getBlockID() {
        return id;
    }
}
