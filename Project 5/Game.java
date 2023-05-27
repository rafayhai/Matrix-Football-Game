import java.util.Scanner;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

public class Game
{


    /**
     * @param pointsArray - represents an array of total points for each column
     **/

    private static int[] pointsArray = null;
    /**
     * @param col - represents column
     **/
    private static int col = 0;
    /**
     * @param twoValues - represents an object of the static inner class twoValues
     **/

    private static Game.TwoValues twoValues = new Game.TwoValues();
    /**
     * @param row - represents row
     **/

    private static int row = 0;
    /**
     * @param totalPoint - represents totalPoint for each column path
     **/
    private static int totalPoint = 0;
    /**
     * @param blocks - ArrayList that holds the elements of the best path
     **/

    private static ArrayList<Block> blocks = new ArrayList<>();
    /**
     * @param downPoint - represents the points of the element under current element
     **/
    private static int downPoint = -49;
    /**
     * @param leftPoint - represents the points of the element that is left-diagnal current element
     **/
    private static int leftPoint = -49;
    /**
     * @param rightPoint - represents the points of the element that is right-diagnal current element
     **/
    private static int rightPoint = -49;
    /**
     * @param higherPoint - represents the highest point amongst 2-3 elements in the direction of travel
     **/
    private static int higherPoint = 0;
    /**
     * @param highestIndex - represents the index of the column with the best path
     **/
    private static int highestIndex = 0;
    /**
     * @param highestPoints - represents the points of highestIndex field
     **/
    private static int highestPoints = 0;
    /**
     * @param currentPoints - represents the points of the next element of travel
     **/

    private static int currentPoints = -49;
    /**
     * @param colVal - represents column value in iterator
     **/

    private static int colVal = 0;
    /**
     * @param pathRow - represents the row element holder for the best path travel for bestRoute() method
     **/

    private static int pathRow = -1;
    /**
     * @param pathCol - represents the column element holder for the best path travel for bestRoute() method
     **/
    private static int pathCol = -1;



    public static TwoValues bestStartingPoint(Field<Block> board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        int highestIndex = 0;
        int highestPoints = 0;
        boolean allRowsChecked = false;
        if (pointsArray == null) {
            pointsArray = new int[board.getWidth()];
        }

        //if (col == board.getWidth()) {
            //return TwoValues;
        //}
        //when element is in first col, can only move down or right diagnal
        if (colVal < board.getWidth()) {

            if (row == board.getHeight() - 1) {
                row = 0;
                totalPoint = 0;
                colVal++;
                col = colVal;
                if (col == board.getWidth()) {
                    allRowsChecked = true;

                }

            }
            if (row == 0 && allRowsChecked == false) {
                if (board.getElement(row, col).getClass().getName().equals("Passage") == true) {
                    Passage passage = (Passage) board.getElement(row, col);
                    totalPoint = passage.getValue();
                }
            }
            if (allRowsChecked != true) {
                if (col == 0) {
                    if (board.getElement(row + 1, col).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col);
                        downPoint = passage.getValue();


                    }
                    if (board.getElement(row + 1, col + 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col + 1);
                        rightPoint = passage.getValue();

                    }
                    totalPoint += Math.max(downPoint, rightPoint);
                    currentPoints = Math.max(downPoint, rightPoint);
                    if (currentPoints == downPoint) {
                        row = row + 1;
                    }
                    else if (currentPoints == rightPoint) {
                        row = row + 1;
                        col = col + 1;
                    }
                } else if (col == board.getWidth() - 1) {
                    if (board.getElement(row + 1, col).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col);
                        downPoint = passage.getValue();


                    }
                    if (board.getElement(row + 1, col - 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col - 1);
                        leftPoint = passage.getValue();

                    }
                    totalPoint += Math.max(downPoint, leftPoint);
                    currentPoints = Math.max(downPoint, rightPoint);
                    if (currentPoints == downPoint) {
                        row = row + 1;
                    }
                    else if (currentPoints == leftPoint) {
                        row = row + 1;
                        col = col - 1;
                    }

                } else {
                    if (board.getElement(row + 1, col).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col);
                        downPoint = passage.getValue();

                    }
                    if (board.getElement(row + 1, col + 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col + 1);
                        rightPoint = passage.getValue();

                    }
                    if (board.getElement(row + 1, col - 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(row + 1, col - 1);
                        leftPoint = passage.getValue();

                    }
                    higherPoint = Math.max(downPoint, leftPoint);
                    totalPoint += Math.max(rightPoint, higherPoint);
                    currentPoints = Math.max(rightPoint, higherPoint);
                    ;
                    if (currentPoints == downPoint) {
                        row = row + 1;
                    } else if (currentPoints == rightPoint) {
                        row = row + 1;
                        col = col + 1;
                    } else if (currentPoints == leftPoint) {
                        row = row + 1;
                        col = col - 1;
                    }

                }
                pointsArray[colVal] = totalPoint;
                downPoint = -49;
                leftPoint = -49;
                rightPoint = -49;
                higherPoint = -49;
                currentPoints = -49;
                if (col < board.getWidth() && row < board.getHeight()) {
                    return bestStartingPoint(board);
                }
            }
        }
        highestIndex = 0;
        highestPoints = pointsArray[0];

        for (int i = 1; i < pointsArray.length; i++) {
            if (pointsArray[i] > highestPoints) {
                highestPoints = pointsArray[i];
                highestIndex = i;
            }

        }

        twoValues.startingColumn = highestIndex;
        twoValues.totalPoints = highestPoints;



        return twoValues;
    }
    public static class TwoValues
    {
        public int startingColumn;
        public int totalPoints;
    }
    public static ArrayList<Block> bestRoute(Field<Block> board, int col) {
        if (board == null) {
            throw new IllegalArgumentException();
        }

        if (pathRow == -1) {

            pathRow = 0;
            downPoint = 0;
            leftPoint = 0;
            rightPoint = 0;
            higherPoint = 0;
            highestIndex = 0;
            highestPoints = 0;
            currentPoints = 0;
            pathCol = twoValues.startingColumn;
        }
        if (blocks.size() == 0) {
            blocks.add(board.getElement(pathRow, pathCol));

        }

            if (pathRow < board.getHeight() - 1) {
                if (pathRow + 1 == board.getHeight()) {
                    return blocks;
                }
                if (pathCol == 0) {
                    if (board.getElement(pathRow + 1, pathCol).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol);
                        downPoint = passage.getValue();


                    }
                    if (board.getElement(pathRow + 1, pathCol + 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol + 1);
                        rightPoint = passage.getValue();

                    }
                    currentPoints = Math.max(downPoint, rightPoint);
                    if (currentPoints == downPoint) {
                        pathRow = pathRow + 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    }
                    else if (currentPoints == rightPoint) {
                        pathRow = pathRow + 1;
                        pathCol = pathCol + 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    }
                }
                else if (pathCol == board.getWidth() - 1) {
                    if (board.getElement(pathRow + 1, pathCol).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol);
                        downPoint = passage.getValue();


                    }
                    if (board.getElement(pathRow + 1, pathCol - 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol - 1);
                        leftPoint = passage.getValue();

                    }
                    currentPoints = Math.max(downPoint, leftPoint);
                    if (currentPoints == downPoint) {
                        pathRow = pathRow + 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    }
                    else if (currentPoints == leftPoint) {
                        pathRow = pathRow + 1;
                        pathCol = pathCol - 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    }

                } else {
                    if (board.getElement(pathRow + 1, pathCol).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol);
                        downPoint = passage.getValue();

                    }
                    if (board.getElement(pathRow + 1, pathCol + 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol + 1);
                        rightPoint = passage.getValue();

                    }
                    if (board.getElement(pathRow + 1, pathCol - 1).getClass().getName().equals("Passage") == true) {
                        Passage passage = (Passage) board.getElement(pathRow + 1, pathCol - 1);
                        leftPoint = passage.getValue();

                    }
                    higherPoint = Math.max(downPoint, leftPoint);
                    currentPoints = Math.max(rightPoint, higherPoint);
                    ;
                    if (currentPoints == downPoint) {
                        pathRow = pathRow + 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    } else if (currentPoints == rightPoint) {
                        pathRow = pathRow + 1;
                        pathCol = pathCol + 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    } else if (currentPoints == leftPoint) {
                        pathRow = pathRow + 1;
                        pathCol = pathCol - 1;
                        blocks.add(board.getElement(pathRow, pathCol));
                    }

                }
                downPoint = -49;
                leftPoint = -49;
                rightPoint = -49;
                higherPoint = -49;
                return bestRoute(board, col);
            }
        return blocks;

        }

    public static void main(String args[])
    {
        /**
         command line arguments validation
         */
       // if (args.length != 1)
        //{
            //System.err.println("Usage: java " + Game.class.getName() + " <filename>");
           // return;
       // }

        /**
         example of loading data from a file
         */
      Field<Block> field = FieldGenerator.loadDataFromFile("filename.txt");

        /**
         example of generating a random Field
         */
        //Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);

        /**
         print the whole field
         */
        System.out.println(field);

        /**
         Example of running a foreach loop
         This will invoke the default iterator (the one using the anonymous inner class)
         */
        for(Block b :field)
            System.out.println(b);

        /**
         Example of running a while loop
         This will invoke the overloaded iterator (the one using the FieldIterator class)
         */
        Iterator<Block> it = field.iterator("Passage");
      //Iterator<Block> it = field.iterator("Obstacle"); // same thing for Obstacle objects
        while(it.hasNext())
           System.out.println(it.next());

        /**
         find the best starting point and print the results
         */
        TwoValues br = bestStartingPoint(field);
        System.out.println("Best starting point is in column " + br.startingColumn + " and the total points collected from this route is " + br.totalPoints);

        /**
         find the best route and print it
         */
        ArrayList<Block> al = bestRoute(field, br.startingColumn);
        for (Block b : al)
            System.out.println(b);
    }

}
