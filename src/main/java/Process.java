public class Process {

    private static int processIdGenerator = 1;
    private final int processId;
    private final int size;
    private final String name;

    public Process(String name) {
        this.name = name;
        this.size = -1;
        this.processId = -1;
    }

    /**
     * Creates a process with a process ID (processId) and required memory allocation (size)
     *
     * @param name - Process name
     * @param size - Memory required to allocate
     */
    public Process(String name, int size) {
        processId = processIdGenerator++;
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getProcessID() {
        return processId;
    }

    @Override
    public String toString() {
        return name + " { ID = " + processId + " , size = " + size + " }\t";
    }


}