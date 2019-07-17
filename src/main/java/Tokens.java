import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public abstract class Tokens {
    public static List<Operations> tokenize(String code) {
        List<Operations> retValue = new ArrayList<Operations>();
        Map<Character,Operations> mMap = new HashMap<Character, Operations>();

        mMap.put('+', new Operations(Operations.Type.ADD, 1));
        mMap.put('-', new Operations(Operations.Type.ADD, -1));
        mMap.put('<', new Operations(Operations.Type.SHIFT, -1));
        mMap.put('>', new Operations(Operations.Type.SHIFT, 1));
        mMap.put('.', new Operations(Operations.Type.OUT));
        mMap.put(',', new Operations(Operations.Type.IN));
        mMap.put('[', new Operations(Operations.Type.WHILE));
        mMap.put(']', new Operations(Operations.Type.END));

        int pos = 0;

        //Приходимся по всем символам
        while (pos < code.length()) {
            char c = code.charAt(pos);
            if (mMap.containsKey(c))
                retValue.add(mMap.get(c));
            pos++;
        }

        return retValue;
    }
}