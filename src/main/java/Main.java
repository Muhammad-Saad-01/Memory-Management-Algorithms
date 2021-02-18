import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final MemoryAllocation bestFitAllocation = new MemoryAllocation(new BestFit());
    private static final MemoryAllocation firstFitAllocation = new MemoryAllocation(new FirstFit());
    private static final MemoryAllocation worstFitAllocation = new MemoryAllocation(new WorstFit());

    public static void main(String[] args) {

       /* ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block(50));
        blocks.add(new Block(250));
        blocks.add(new Block(150));
        blocks.add(new Block(120));
        blocks.add(new Block(40));
        Memory memory = new Memory(blocks);

        Process p1 = new Process("P1", 50);
        Process p2 = new Process("P2", 250);
        Process p3 = new Process("P3", 150);
        Process p4 = new Process("P4", 20);
        System.out.println(memory.allocate(worstFitAllocation, p1));
        System.out.println(memory.allocate(worstFitAllocation, p2));
        System.out.println(memory.allocate(worstFitAllocation, p3));
        System.out.println(memory.allocate(worstFitAllocation, p4));
*/
        Scanner reader = new Scanner(System.in);
        System.out.println("*-*-*-*-*-*-*-*- Memory Initialization Phase -*-*-*-*-*-*-*-*");
        System.out.print("Please Enter the number of Blocks (Partitions) : ");
        int numberOfBlocks = Integer.parseInt(reader.nextLine());
        ArrayList<Block> blocks = new ArrayList<>(numberOfBlocks);
        System.out.println("Please Enter the sizes of each Block (Partition) : ");
        for (int i = 1; i <= numberOfBlocks; i++) {
            System.out.print("Enter size of Block No. " + i + " : ");
            int blockSize = Integer.parseInt(reader.nextLine());
            blocks.add(new Block(blockSize));
        }
        Memory memory = new Memory(blocks);
//        System.out.println("\nSome Memory Statistics :");
        System.out.println(memory);

        System.out.println("*-*-*-*-*-*-*- End of Memory Initialization Phase -*-*-*-*-*-*\n");

        Request request = new Request();


    }
}
