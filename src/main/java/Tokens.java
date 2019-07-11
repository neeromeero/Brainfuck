import java.util.ArrayList;
import java.util.List;

public abstract class Tokens {
    public static List<Operations> tokenize(String code) {
        //Создаем массив лексем (которые уже являются опкодами и готовы к исполнению)
        List<Operations> retValue = new ArrayList<Operations>();
        int pos = 0;

        //Приходимся по всем символам
        while (pos < code.length()) {
            switch (code.charAt(pos++)) {
                //Как и говорилось ранее, некоторые команды эквивалентны
                case '>':
                    retValue.add(new Operations(Operations.Type.SHIFT, +1));
                    break;
                case '<':
                    retValue.add(new Operations(Operations.Type.SHIFT, -1));
                    break;

                case '+':
                    retValue.add(new Operations(Operations.Type.ADD, +1));
                    break;
                case '-':
                    retValue.add(new Operations(Operations.Type.ADD, -1));
                    break;

                case '.':
                    retValue.add(new Operations(Operations.Type.OUT));
                    break;
                case ',':
                    retValue.add(new Operations(Operations.Type.IN));
                    break;
                case '[':
                    char next = code.charAt(pos);

                    //проверяем, является ли это обнулением ячейки ([+] или [-])
                    if ((next == '+' || next == '-') && code.charAt(pos + 1) == ']') {
                        retValue.add(new Operations(Operations.Type.ZERO));
                        pos += 2;
                    } else
                        retValue.add(new Operations(Operations.Type.WHILE));
                    break;
                case ']':
                    retValue.add(new Operations(Operations.Type.END));
                    break;
            }
        }

        return retValue;
    }
}