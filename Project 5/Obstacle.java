public class Obstacle extends Block {
    /**
     * @param label - string that represents label of obstacle
     **/

    private String label;
    public Obstacle(String label) {
        if (label == null ) {
            throw new IllegalArgumentException();
        }
        this.label = label;

    }
    /**
     * @return label - returns the label of the obstacle
     **/

    public String toString() {
        return this.label;

    }
    /**
     * @return 0- obstacle has 0 value
     **/
    @Override
    public int getValue() {
        return 0;
    }
}
