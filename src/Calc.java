import java.lang.reflect.Array;
import java.util.*;

public class Calc {
    private LinkedList<String> expression = new LinkedList<String>();

    public Calc() {
        addString();
        Calculation();
    }

    private void addString() {
        boolean flag=true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите выражение (через пробел) :");
            scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            String[] tmp = string.split(" ");
            for (int i = 0; i < tmp.length; i++) {
                expression.add(tmp[i]);
            }
            //expression= Arrays.asList(string.split(" "));
            //Collections.addAll(expression, string.split(" "));//Печаль
            if (!isNumber(expression.getFirst())) {
                System.out.println("Выражение не начинается на число");
                break;
            }
            if (!isNumber(expression.getLast())) {
                System.out.println("Выражение не заканчивается на число");
                break;
            }
            for (String it : expression
            ) {
                if (isNumber(it) || isAction(it)){
                    flag=false;
                    break;}
            }

        } while (flag);
    }

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка во введенных данных");
            return false;
        }
    }

    private boolean isAction(String str) {
        if (str.length() > 1) return false;
        return str == "+" || str == "-" || str == "/" || str == "*";
    }

    private void Calculation() {
        double res = 0, dig = 0;
        while (expression.contains("*")) {
            int index = expression.indexOf("*");
            dig = Double.parseDouble(expression.get(index - 1)) * Double.parseDouble(expression.get(index + 1));
            expression.remove(index - 1);
            expression.remove(index - 1);
            expression.remove(index - 1);
            expression.add(index - 1, Double.toString(dig));
        }

        while (expression.contains("/")) {
            int index = expression.indexOf("/");
            dig = Double.parseDouble(expression.get(index - 1)) / Double.parseDouble(expression.get(index + 1));
            expression.remove(index - 1);
            expression.remove(index - 1);
            expression.remove(index - 1);
            expression.add(index - 1, Double.toString(dig));
        }

        res = Double.parseDouble(expression.getFirst());

        for (int i = 1; i < expression.size(); i++) {
            switch (expression.get(i)) {
                case ("+"):
                    res += Double.parseDouble(expression.get(i + 1));
                    break;
                case ("-"):
                    res -= Double.parseDouble(expression.get(i + 1));
                    break;
            }
            i++;
        }

        System.out.println(" = " + res);

    }

}
