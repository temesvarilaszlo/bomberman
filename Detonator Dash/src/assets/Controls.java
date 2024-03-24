package assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static assets.AssetLoader.loadTxt;
import java.io.InputStream;

public class Controls {
    public static int[][] controls;
    
    public Controls(){
        controls = loadControls();
    }
    
    private int[][] loadControls(){
        InputStream is = loadTxt("assets/controls.txt");
        int[][] matrix = new int[3][5];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {
                String[] numbersAsString = line.trim().split(",");
                matrix[rowCount] = new int[numbersAsString.length];
                for (int i = 0; i < numbersAsString.length; i++) {
                    matrix[rowCount][i] = Integer.parseInt(numbersAsString[i]);
                }
                rowCount++;
            }
        } catch (IOException e) {
            System.out.println("Controls.txt couldn't load correctly!");
        }
        return matrix;
    }
}
