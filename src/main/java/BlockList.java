import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BlockList implements Iterable<Block> {
    private final LinkedList<Block> blocks;
    private int size = 0;
    private int conversionFactor = 1024;

    public BlockList() {
        blocks = new LinkedList<>();
    }

    public void addBlock(Block block) {
        size += block.getSize();
        Block temp = blocks.peekLast();
        if (temp == null) {
            block.setStartAddress(0);
            block.setEndAddress(block.getSize() * conversionFactor - 1);
        } else {
            block.setStartAddress(temp.getEndAddress() + 1);
            block.setEndAddress(block.getStartAddress() + block.getSize() * conversionFactor - 1);
        }
        blocks.add(block);
    }

    public int getNumberOfBlocks() {
        return blocks.size();
    }

    public Block getBlock(int id) {
        for (Block block : blocks) {
            if (block.getBlockID() == id)
                return block;
        }
        return null;
    }

    public void removeBlock(Block block) {
        if (blocks.remove(block)) {
            size -= block.getSize();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        for (Block block : blocks) {
            tempString.append("\t").append(block);
        }
        return tempString.toString();
    }

    @Override
    public Iterator<Block> iterator() {
        return new Iterator<Block>() {
            private final Iterator<Block> iter = blocks.iterator();

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
