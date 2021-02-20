import java.util.Arrays;
import java.util.Scanner;

public class Request {

    private RequestType requestType;
    private Process process;
    private static final Scanner reader = new Scanner(System.in);
    private MemoryAllocation allocationType;


    /**
     * Explicit Request creation, used for testing purposes
     *
     * @param requestType - The RequestType to be assigned
     */

    public Request(RequestType requestType) {
        this.requestType = requestType;
        switch (requestType) {
            case ALLOCATE -> {
                String[] input;
                boolean nameFlag = false, sizeFlag = false, strategyFlag = false;
                int processSize = 0;
                do {
                    System.out.println("Please Enter the process name, the size of the process in KB, " +
                            "and the allocation strategy [for First-fit write (F) or (f),for Best-fit write (B) or (b), or for Worst-fit write (W) or (w)] \n" +
                            "like the following example:\nP0 40 W");
                    input = reader.nextLine().split(" ");
                    if (input.length != 3) {
                        System.out.println("Please rewrite all of them correctly with spaces between them..");
                        continue;
                    }
                    nameFlag = true;
                    try {
                        processSize = Integer.parseInt(input[1]);
                        sizeFlag = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please rewrite size correctly and don't forget spaces between them..");
                        continue;
                    }
                    if (input[2].equals("F") || input[2].equals("B") || input[2].equals("W") ||
                            input[2].equals("f") || input[2].equals("b") || input[2].equals("w")) {
                        strategyFlag = true;
                    } else {
                        System.out.println("Please choose the allocation strategy correctly \n" +
                                "Write B or b for Best-fit or\n" +
                                "Write w or W for Worst-fit or \n" +
                                "Write F or f for First-fit");
                    }

                } while (!nameFlag || !sizeFlag || !strategyFlag);
                process = new Process(input[0], processSize);
                switch (input[2]) {
                    case "W":
                    case "w":
                        allocationType = new MemoryAllocation(new WorstFit());
                    case "F":
                    case "f":
                        allocationType = new MemoryAllocation(new FirstFit());
                    case "B":
                    case "b":
                        allocationType = new MemoryAllocation(new BestFit());
                }
            }
            case DEALLOCATE -> {
                System.out.print("Please Enter the process name ");
                String name = reader.nextLine();
                process = new Process(name);
            }
        }
    }


    public RequestType getType() {
        return requestType;
    }

    public Process getProcess() {
        return process;
    }

    public MemoryAllocation getAllocationType() {
        return allocationType;
    }
}
