public class Passage extends Block {
    /**
     * @param value - represents the numeric value of the passage
     **/
     private int value;

     public Passage(int value) {
         this.value = value;
     }
    /**
     * @return returns string representation of field
     **/

     public String toString() {

         return Integer.toString(this.value);
     }
    /**
     * @return value - returns the numeric value of the passage
     **/
     @Override
     public int getValue() {
         return this.value;
     }

}
