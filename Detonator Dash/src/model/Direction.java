package model;

public enum Direction {
    DOWN(0, 1), LEFT(-1, 0), UP(0, -1), RIGHT(1, 0), STOPPED(0,0);
    
    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    public final int x, y;
    
    public static Direction oppositeDirection(Direction d){
        if (d == null) {
            return Direction.LEFT;
        }
        return switch (d) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            default -> Direction.LEFT;
        };
    }
}

