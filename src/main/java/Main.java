import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static final int STACK_LENGTH = 10000;
  private static short[] arr = new short[STACK_LENGTH];

  //добавить чтение из файла
  public static void main(String[] args) throws Exception {

    FileReader fr = new FileReader(
        "C:\\Users\\v_dansker\\Desktop\\Лучшая Морковь\\BRAIN_FUCK\\Brainfuck\\src\\main\\resources\\test.txt");
    Scanner scan = new Scanner(fr);

    while (scan.hasNextLine()) {
      System.out.println(run(scan.nextLine()));
    }

    fr.close();

    /*System.out.println(run("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n" +
        ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n" +
        "------.--------.>+.>."));*/
  }

  private static String run(String strCommand) {
    StringBuilder retString = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<Operations> Lexemes = Optimization.optimize(strCommand);

    int pointer = 0;
    List<Integer> queueLoop = new ArrayList<Integer>();
    for (int i = 0; i < Lexemes.size(); i++) {
      Operations command = Lexemes.get(i);

      switch (command.type) {
        case ADD: {
          if (command.arg > 0) {
            if ((arr[pointer] + 1) > 255) {
              arr[pointer] = 0;
            } else {
              arr[pointer] += command.arg;
            }
          } else if ((arr[pointer] - 1) < 0) {
            arr[pointer] = 255;
          } else {
            arr[pointer] += command.arg;
          }
          break;
        }
        case SHIFT: {
          pointer += command.arg;
          break;
        }
        case IN: {
          try {
            arr[pointer] = (short) Integer.parseInt(reader.readLine());
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        }
        case OUT: {
          for (int k = 0; k < command.arg; k++) {
            retString.append((char) arr[pointer]);
          }
          break;
        }
        case ZERO: {
          arr[pointer] = 0;
          break;
        }
        case WHILE: {
          queueLoop.add(i);
          break;
        }
        case END: {
          if (arr[pointer] > 0) {
            i = queueLoop.remove(queueLoop.size() - 1);
            i--;
          }
          break;
        }
        default:
          break;
      }
    }
    return retString.toString();
  }
}
