package by.kharitonov.day4.task1.entity;

public enum SortDirection {
    UP("Up"), DOWN("Dwon");

    private String direction;

    SortDirection(String direction) {
        this.direction=direction;
    }

    public String getDirection() {
        return direction;
    }

}
