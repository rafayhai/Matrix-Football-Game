import java.util.Iterator;
import java.util.NoSuchElementException;

public class Field<T> implements FlexibleIterable<T> {
    /**
     * @param matrix - a 2D matrix that represents the field
     **/

    private T[][] matrix;

    /**
     * @param height- represents height of field
     **/

    private int height;
    /**
     * @param width - represents width of field
     **/
    private int width;

    public Field(int height, int width) {
        this.height =  height;
        this.width =  width;
        matrix = (T[][]) new Object[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                matrix[i][j] = null;

            }
        }

    }
    /**
     * @return matrix - getter method that returns an element in the field
     **/
    public T getElement(int row, int col) {
        return matrix[row][col];
    }
    /**
     * @return matrix - setter method that assigns a block in the field
     **/
    public void setElement(int row, int col, T el) {
        matrix[row][col] = el;

    }
    /**
     * @return height - getter method that returns height of field
     **/
    public int getHeight() {
        return this.height;
    }
    /**
     * @return width - getter method that returns width of field
     **/
    public int getWidth() {
        return this.width;
    }
    /**
     * @return String - returns string representation of field
     **/
    public String toString() {
        String rep = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0 && i == 0) {
                    rep = matrix[i][j] + " ";

                }
                else {
                    rep += matrix[i][j] + " ";
                    if (i == matrix[i].length - 1 && j == matrix[i].length - 1) {
                        rep += matrix[i][j];
                        break;

                    }
                    if (j == matrix[i].length - 1) {
                        rep += "\n";
                    }

                }

            }
        }
        return rep;
    }

    /**
     * @return fieldIterator - returns the object of fieldIterator that passes the iterableObjectName
     **/
    @Override
    public Iterator<T> iterator(String iterableObjectName) {
        if (iterableObjectName == null) {
            throw new IllegalArgumentException();
        }
        FieldIterator fieldIterator = new FieldIterator(iterableObjectName,this);

        return fieldIterator;
    }
    /**
     * @return iterator() - this method is an iterator that loops through entire field,
     * returning each element.
     **/


    public Iterator<T> iterator() {
        T item = null;
        int matrixHeight = getHeight();
        int matrixWidth = getWidth();
        Iterator<T> AnonIterator = new Iterator<>()
        {
            T item = null;
            private int rowVal = 0;
            private int colVal = 0;

            @Override
            public boolean hasNext() {
                if (rowVal < matrixHeight - 1 || colVal < matrixWidth) {
                    return true;
                }
                return false;
            }
            public T next() {
                if (item == null) {
                    item = matrix[rowVal][colVal];

                }
                if (hasNext() != false) {
                    //item = matrix[0][0];
                    if(colVal != matrixWidth) {
                        if (item != null) {
                            item = matrix[rowVal][colVal];
                            colVal++;
                        }
                    }
                    else {
                        rowVal++;
                        if (rowVal < matrixHeight && item != null) {
                            colVal = 0;
                            item = matrix[rowVal][colVal];
                            colVal++;
                        }



                    }


                }
                else {
                    throw new NoSuchElementException();
                }
                return item;


            }


            public void remove() {
                throw new UnsupportedOperationException();

            }


        };
        return AnonIterator;



    }


}
