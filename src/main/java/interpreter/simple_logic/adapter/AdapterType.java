package interpreter.simple_logic.adapter;

import javax.annotation.Nullable;

public enum AdapterType {
    IN(0, "in"),
    OUT(1, "out"),
    IO(2, "io");

    private final int index;
    private final String name;

    AdapterType(int index, String name){
        this.index = index;
        this.name = name;
    }

    public boolean isIn() {
        return this == IN || this == IO;
    }

    public boolean isOut() {
        return this == OUT || this == IO;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public static @Nullable AdapterType getType(int index) {
        return switch (index) {
            case 0 -> IN;
            case 1 -> OUT;
            case 2 -> IO;
            default -> null;
        };
    }

    public static @Nullable AdapterType getType(String name) {
        return switch (name) {
            case "in" -> IN;
            case "out" -> OUT;
            case "io" -> IO;
            default -> null;
        };
    }
}
