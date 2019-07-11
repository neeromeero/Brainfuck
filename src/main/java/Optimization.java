import java.util.List;
import java.util.Stack;

public abstract class Optimization {
    public static List<Operations> optimize(String code) {
        return optimize(Tokens.tokenize(code));
    }

    public static List<Operations> optimize(List<Operations> tokens) {
        Stack<Operations> retValue = new Stack<Operations>();

        //Приходимся по всем командам
        for (Operations token : tokens) {
            switch (token.type) {
                case SHIFT:
                case ADD:
                case OUT:
                case IN:
                case ZERO:
                    //Если это первая итерация, добавляем элемент и переходим к следующему
                    if (retValue.size() == 0) {
                        retValue.push(token.clone());
                        continue;
                    }

                    //Если последняя команда не совпадает с текущей, значит мы закончили сжатие
                    if (retValue.peek().type != token.type) {
                        if (retValue.peek().arg == 0) //если в результате сжатия команда "исчезла"
                            retValue.pop(); //то просто убираем ее

                        if (retValue.peek().type == Operations.Type.ZERO) //если это команда ZERO
                            retValue.peek().arg = 1; //то убираем возможные повторы, ибо они не имеют смысла

                        retValue.push(token.clone()); //добавляем текущую команду
                        continue;
                    }

                    //сюда мы попадет при условии, если команда дальше повторяется
                    //мы просто дополняем текущую команду вместо добавления новой
                    retValue.peek().arg += token.arg;
                    break;

                case WHILE:
                case END:
                    //циклы объединять не надо
                    retValue.add(token.clone());
                    break;
            }
        }

        return retValue;
    }
}