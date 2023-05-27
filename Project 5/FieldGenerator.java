
import java.util.Random;
import java.util.Scanner;
import java.io.File;
public class FieldGenerator {

    /**
     *
     * @param filename - represents name of file
     * @return - a new field generated based on file content
     */

    public static Field<Block> loadDataFromFile(String filename) {
        Field<Block> field = null;
        int colLength = 0;
        int rowLength = 0;
        int colValue = 0;
        String[] rowVal = null;
        File file = new File(filename);
        try {

            Scanner scanner = new Scanner(file);

            //int fileLength = 6;
            if (colLength == 0) {
                while (scanner.hasNextLine()) {
                    String fileLine = scanner.nextLine();
                    colLength += 1;
                }

            }
            Scanner scanner1 = new Scanner(file);

            while (scanner1.hasNextLine()) {
                String fileLine = scanner1.nextLine();
                rowVal = fileLine.split(" ");
                rowLength = rowVal.length;

            if (field == null) {
                field = new Field<>(colLength, rowLength);
            }
            while (field.getElement(colValue, rowLength - 1) == null) {
                for (int i = 0; i < rowVal.length; i++) {
                    if (!rowVal[i].matches("-")) {
                        int element = Integer.parseInt(rowVal[i]);
                        Passage passage = new Passage(element);
                        field.setElement(colValue, i, passage);


                    } else {
                        Obstacle obstacle = new Obstacle("-");
                        field.setElement(colValue, i, obstacle);
                    }

                }
            }
                colValue++;
            }
        }

        catch (Exception e) {

            System.out.println("Error opening file" + e.getMessage());

        }
        return field;

    }

    /**
     *
     * @param h - height of field
     * @param w - width of field
     * @param l - lowest passage points
     * @param m - highest passage points
     * @param n - number of obstacles
     * @return - generated field based on parameter details
     */
    public static Field<Block> randomIntegers(int h, int w, int l, int m, int n) {
        Field field= new Field(h,w);
        Random random = new Random();
        int obstacleCounter = 0;
        int randomCol = 0;
        int randomRow = 0;
        int randomNum = 0;
        //(int)(Math.random() * ((max - min) + 1))
        //random obstacles generated in different locations of fields
       while (obstacleCounter <= n) {
           randomCol = (int)(Math.random() * (((w - 1) - 0) + 1));
           randomRow = (int)(Math.random() * (((h - 1) - 0) + 1));
           Obstacle obstacle = new Obstacle("-");
           field.setElement(randomRow,randomCol,obstacle);
           obstacleCounter++;
       }
       //looping through matrix and if element is null, I generate a passage there
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if (field.getElement(i,j) == null) {
                    randomNum = (int)(Math.random() * ((m - l) + 1));
                    Passage passage = new Passage(randomNum);
                    field.setElement(i,j,passage);

                }
                else {
                    continue;
                }
            }
        }
        return field;


    }

}
