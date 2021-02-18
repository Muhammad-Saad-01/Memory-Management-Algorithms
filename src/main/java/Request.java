public class Request {

    private RequestType requestType;
    private Process process;
    MemoryAllocation allocationType;

    /**
     * Explicit Request creation, used for testing purposes
     *
     * @param requestType - The RequestType to be assigned
     * @param process     - The Process to be allocated/deallocated
     */
    private Request(RequestType requestType, Process process) {
        this.requestType = requestType;
        this.process = process;
    }

    public Request() {
        allocate(new MemoryAllocation(new BestFit()), new Process("", 50));
    }

    private void allocate(MemoryAllocation allocationType, Process process) {
        this.requestType = RequestType.ALLOCATE;
        this.process = process;
    }

    private void deallocate(Process process) {
        this.requestType = RequestType.DEALLOCATE;
        this.process = process;
    }

    private void writeReport() {
        this.requestType = RequestType.DEALLOCATE;
    }


    public RequestType getType() {
        return requestType;
    }

    public Process getProcess() {
        return process;
    }
}
