import java.util.Iterator;
import java.util.NoSuchElementException;

public class FieldIterator<T> implements Iterator<T> {
    /**
     * @param iterableObjectName - a string that represents the iterable object
     **/
    private String iterableObjectName;
    /**
     * @param field - represents a field object
     **/

    private Field field;
    /**
     * @param rowVal - represents the current row value in the iterator
     **/
    private int rowVal = 0;
    /**
     * @param colVal - represents the current column value in the iterator
     **/
    private int colVal = 0;
    /**
     * @param value - represents a value of type object
     **/
    private Object value = null;
    /**
     * @param checker - boolean checker for lastElement() method
     **/

    private boolean checker = true;
    /**
     * @param finalCol - represents last column of iterableStringName in the field
     **/
    private int finalCol = 100;
    /**
     * @param finalRow - represents last row of iterableStringName in the field
     **/
    private int finalRow = 100;
    /**
     * @param className - represents the name of an object in the field
     **/

    private String className = null;
    public FieldIterator(String iterableObjectName,Field field) {
        if (iterableObjectName == null || field == null) {
            throw new IllegalArgumentException();
        }
    this.iterableObjectName = iterableObjectName;
    this.field = field;


    }
    /**
     * @return boolean - returns true or false depending on if there is a next element to iterate on
     **/
    public boolean hasNext() {
        if (iterableObjectName.equals("Obstacle")) {
            if (colVal == 0 && rowVal == 0) {
                lastElement();
            }
            if (rowVal < this.finalRow  || colVal < this.finalCol) {
                return true;
            }
        }
        if (iterableObjectName.equals("Passage")) {
            if ((rowVal < field.getHeight() - 1 || colVal < field.getWidth())) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return T value - returns the next iterable item
     **/

    public T next() {
        if (colVal != field.getWidth()) {

            value = field.getElement(rowVal, colVal);
        }
        else {
            rowVal++;
            colVal = 0;
            value = field.getElement(rowVal, colVal);
        }

        if (hasNext() != false) {
            className = value.getClass().getName();
            if (className.equals(iterableObjectName) == false) {
                    while (!className.equals(iterableObjectName)) {
                        if (hasNext() != false) {
                            if (colVal < field.getWidth()) {
                                value = field.getElement(rowVal, colVal);
                                className = value.getClass().getName();
                                colVal++;
                            } else {
                                rowVal++;
                                colVal = 0;
                                if (rowVal < field.getHeight()) {
                                    value = field.getElement(rowVal, colVal);
                                    className = value.getClass().getName();
                                    colVal++;
                                }
                            }
                        }
                        else {
                            break;
                        }


                    }
                    if (colVal == this.finalCol && rowVal == this.finalRow) {
                    value = field.getElement(rowVal, colVal);
                    return (T) value;
                }
                hasNext();

            } else if (className.equals(iterableObjectName) == true) {

                if (colVal != field.getWidth()) {
                    value = field.getElement(rowVal, colVal);
                    colVal++;
                } else {
                    rowVal++;
                    if (rowVal < field.getHeight()) {
                        colVal = 0;
                        value = field.getElement(rowVal, colVal);
                    }
                }


            }
        }
        else {
            throw new NoSuchElementException();
        }

        return (T) value;


    }
    /**
     * @return void - no return value, checks and assigns the location of last element for iterableStringName
     **/
    private void lastElement() {
        int row = 0;
        int col = 0;
        boolean found = false;
        for (int i = field.getHeight() - 1; i >= 0; i--) {
            if (found == true) {
                break;
            }
            for (int j = field.getWidth() - 1; j >= 0; j--) {
                if (field.getElement(i,j).getClass().getName().equals("Obstacle") == true) {
                    row= i;
                    col= j;
                    setFinalCol(col);
                    setFinalRow(row);
                    found = true;
                    break;

                }

            }
        }

    }
    /**
     * @return void - sets last column value
     **/

    public void setFinalCol(int col) {
        this.finalCol = col;

    }
    /**
     * @return void - sets last row value
     **/
    public void setFinalRow(int row) {
        this.finalRow = row;

    }


    public void remove() {
        throw new UnsupportedOperationException();

    }


}
