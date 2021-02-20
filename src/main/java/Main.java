import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final MemoryAllocation bestFitAllocation = new MemoryAllocation(new BestFit());
    private static final MemoryAllocation firstFitAllocation = new MemoryAllocation(new FirstFit());
    private static final MemoryAllocation worstFitAllocation = new MemoryAllocation(new WorstFit());

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        printHeader("Memory Initialization Phase");
        System.out.print("Please Enter the number of Blocks (Partitions) : ");
        int numberOfBlocks = Integer.parseInt(reader.nextLine());
        BlockList blocks = new BlockList();
        System.out.println("Please Enter the sizes of each Block (Partition) : ");
        for (int i = 1; i <= numberOfBlocks; i++) {
            System.out.print("Enter size of Block No. " + i + " : ");
            int blockSize = Integer.parseInt(reader.nextLine());
            blocks.addBlock(new Block(blockSize));
        }
        Memory memory = new Memory(blocks);
        System.out.println(memory);
        printHeader("End of Memory Initialization Phase");
        System.out.println("********************************************************************");
        printHeader("Memory Allocation\\De-allocation Phase");
        int option;
        Request request;
        while (true) {
            printMenu();
            option = Integer.parseInt(reader.nextLine());
            if (option == 1) {
                request = new Request(RequestType.ALLOCATE);
                memory.requestHandler(request);
            } else if (option == 2) {
                request = new Request(RequestType.DEALLOCATE);
                memory.requestHandler(request);
            } else if (option == 3) {
                request = new Request(RequestType.REPORT);
                memory.requestHandler(request);
            } else if (option == 4) {
                System.out.println("Goodbye!...");
                break;
            } else {
                System.out.println("Please choose one of the correct options");
            }
        }

    }

    public static void printHeader(String header) {
        System.out.println("_______________" + "_".repeat(header.length() + 2) + "_____________");
        System.out.println("|*-*-*-*-*-*-*- " + header + " -*-*-*-*-*-*|");
        System.out.println("---------------" + "-".repeat(header.length() + 2) + "-------------");
    }

    public static void printMenu() {
        System.out.println("\nPlease choose and enter one of the following numbers 1,2,3 or 4 ");
        System.out.println("\t1. Request for a contiguous block of memory.");
        System.out.println("\t2. Release of a contiguous block of memory.");
        System.out.println("\t3. Report detailed information about regions of free and allocated memory.");
        System.out.println("\t4. Exit the program");
        System.out.print("Enter your option number : ");
    }
}

class test {
    private static final MemoryAllocation bestFitAllocation = new MemoryAllocation(new BestFit());
    private static final MemoryAllocation firstFitAllocation = new MemoryAllocation(new FirstFit());
    private static final MemoryAllocation worstFitAllocation = new MemoryAllocation(new WorstFit());

    public static void main(String[] args) {
        BlockList blockList = new BlockList();
        blockList.addBlock(new Block(8));
        blockList.addBlock(new Block(8));
        blockList.addBlock(new Block(8));
        blockList.addBlock(new Block(8));
        blockList.addBlock(new Block(8));
        System.out.println(blockList);
        Memory memory = new Memory(blockList);

        Block b1 = new Block(50);
        Block b2 = new Block(50);

        System.out.println(b2.getEndAddress());

        BlockList blockList2 = new BlockList();
        blockList2.addBlock(new Block(8));
        blockList2.addBlock(new Block(8));
        blockList2.addBlock(new Block(8));
        blockList2.addBlock(new Block(8));
        blockList2.addBlock(new Block(8));
        System.out.println(blockList);
        Memory memory1 = new Memory(blockList);
        Process p1 = new Process("P1", 4);
        Process p2 = new Process("P2", 5);
        Process p3 = new Process("P3", 6);
        Process p4 = new Process("P4", 3);
        System.out.println(memory1.allocate(worstFitAllocation, p1));
        System.out.println(memory1.allocate(worstFitAllocation, p2));
        System.out.println(memory1.allocate(worstFitAllocation, p3));
        System.out.println(memory1.allocate(worstFitAllocation, p4));
        System.out.println(memory1);
    }
}