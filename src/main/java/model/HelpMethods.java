package model;

import view.GamePanel;

public class HelpMethods {
    /**
     * Decides if a character can move
     * @param x float
     * @param y float
     * @param width float
     * @param height float
     * @param map String[][]
     * @param lastPlacedBlock Block
     * @return boolean
     */
    public static boolean canMoveHere(float x, float y, float width, float height, String[][] map, Block... lastPlacedBlock){
        if(!IsSolid(x, y, map, lastPlacedBlock)) //top left
            if(!IsSolid(x+width, y+height, map, lastPlacedBlock)) //bottom right
                if(!IsSolid(x+width, y, map, lastPlacedBlock)) //top right
                    return !IsSolid(x, y + height, map, lastPlacedBlock); //bottom left
        return false;
    }
    
    /**
     * Checks if a block is solid or not
     * @param x float
     * @param y float
     * @param map String[][]
     * @param lastPlacedBlock Block[]
     * @return  boolean
     */
    private static boolean IsSolid(float x, float y, String[][] map, Block[] lastPlacedBlock){
        float xIndex = x / GamePanel.BLOCK_PIXEL_SIZE;
        float yIndex = y / GamePanel.BLOCK_PIXEL_SIZE;
        
        String value = map[(int)yIndex][(int)xIndex];
        
        if (lastPlacedBlock.length != 0 &&
            lastPlacedBlock[0].currentMatrixPosition().x == (int)yIndex &&
            lastPlacedBlock[0].currentMatrixPosition().y == (int)xIndex)
        {
            return false;
        }
        return value.equals("W")|| value.equals("B") || value.equals("Bomb");
    }
}
