
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

  Type type; //тип операции
  int arg = 1; //кол-во повторений

  Operations(Type type, int arg) {
    this.type = type;
    this.arg = arg;
  }

  Operations(Type type) {
    this.type = type;
  }

  public Operations clone() {
    return new Operations(type, arg);
  }
}