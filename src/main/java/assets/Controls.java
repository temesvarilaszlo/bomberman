package assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import view.SettingsWindow;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import static assets.AssetLoader.loadTxt;

public class Controls {
    public static int[][] controls;
    
    public Controls(){
        controls = loadControls();
    }
    
    public static void setButtonsText(ArrayList<JButton> buttonList, int row){
        for (int i = 0; i < controls[row].length; i++) {
            buttonList.get(i).setText(KeyEvent.getKeyText(controls[row][i]));
        }
    }
    
    public static boolean isMatchingKey(int keycode){
        for(int i = 0; i < controls.length; i++){
            for(int j = 0; j < controls[i].length; j++){
                if(controls[i][j] == keycode )
                    return true;
            }
        }
        return false;
    }
    
    public static void updateControlsFile(){
        URL url = SettingsWindow.class.getClassLoader().getResource("assets/controls.txt");
        File controlFile = null;        
        try {
            controlFile = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(controlFile))) {
            for (int[] row : controls) {
                StringBuilder rowString = new StringBuilder();
                for (int col : row) {
                    rowString.append(col).append(",");
                }
                // Remove the last comma and write the row to the file
                writer.write(rowString.substring(0, rowString.length() - 1));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing matrix to file: " + e.getMessage());
        }
    }
    
    private int[][] loadControls(){
        InputStream is = loadTxt("assets/controls.txt");
        int[][] matrix = new int[3][6];
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
