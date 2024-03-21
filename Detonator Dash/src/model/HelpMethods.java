package model;

import view.GamePanel;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, String[][] map, String... blockTypes){
        if(!IsSolid(x, y, map, blockTypes))//top left
            if(!IsSolid(x+width, y+height, map, blockTypes))//bottom right
                if(!IsSolid(x+width, y, map, blockTypes))//top right
                    if(!IsSolid(x, y+height, map, blockTypes))//bottom left
                        return true;
        return false;
    }
    
    private static boolean IsSolid(float x, float y, String[][] map, String[] blockTypes){
        float xIndex = x / GamePanel.BLOCK_PIXEL_SIZE;
        float yIndex = y / GamePanel.BLOCK_PIXEL_SIZE;
        
        String value = map[(int)yIndex][(int)xIndex];
        
        if (blockTypes.length == 0){
            blockTypes = new String[]{"W", "B", "Bomb"};
        }
        
        for (String blockType : blockTypes) {
            if (value.equals(blockType)) {
                return true;
            }
        }
        return false;
    }
}
