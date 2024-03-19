package model;

import view.GamePanel;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, String[][] map){
        if(!IsSolid(x, y, map))//top left
            if(!IsSolid(x+width, y+height, map))//bottom right
                if(!IsSolid(x+width, y, map))//top right
                    if(!IsSolid(x, y+height, map))//bottom left
                        return true;
        return false;
    }
    
    private static boolean IsSolid(float x, float y, String[][] map){
        float xIndex = x / GamePanel.BLOCK_PIXEL_SIZE;
        float yIndex = y / GamePanel.BLOCK_PIXEL_SIZE;
        
        String value = map[(int)yIndex][(int)xIndex];

        return value.equals("W") || value.equals("B");
    }
}
