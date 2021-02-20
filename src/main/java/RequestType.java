import java.util.List;
import java.util.Random;


public enum RequestType {
    ALLOCATE, DEALLOCATE, REPORT;

    private static final List<RequestType> TYPES = List.of(values());
    private static final int SIZE = TYPES.size();
    private static final Random rand = new Random();

    /**
     * Used to generate a random RequestType
     *
     * @return - A random RequestType
     */
    public static RequestType randomType() {
        return TYPES.get(rand.nextInt(SIZE));
    }
}
