public class Operations {
    public enum Type {
        SHIFT,
        ADD,
        ZERO,
        OUT,
        IN,
        WHILE,
        END
    }

    public Type type; //тип операции
    public int arg = 1; //кол-во повторений

    public Operations(Type type, int arg) {
        this.type = type;
        this.arg = arg;
    }

    public Operations(Type type) {
        this.type = type;
    }

    public Operations clone() {
        return new Operations(type, arg);
    }
}