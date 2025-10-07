package lab1;
import java.util.Locale;
import java.util.Scanner;
import java.math.*;



//mac

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        double num = 19;

        //xStart
        System.out.print("Введите начало диапазона (X_start): ");
        while (!scan.hasNextDouble()) {
            System.out.println("Ошибка: введите только число, используя точку.");
            scan.next();
            System.out.print("Повторите ввод X_start: ");

        }
        double xStart = scan.nextDouble();

        //xEnd
        System.out.print("Введите конец диапазона (X_end): ");
        while (!scan.hasNextDouble()) {
            System.out.println("Ошибка: введите только число, используя точку.");
            scan.next();
            System.out.print("Повторите ввод X_end: ");

        }
        double xEnd = scan.nextDouble();

        //deltaX
        System.out.print("Введите шаг deltaX (!= 0): ");
        while (!scan.hasNextDouble()) {
            System.out.println("Ошибка: введите только число, используя точку.");
            scan.next();
            System.out.print("Повторите ввод deltaX: ");
        }
        double deltaX = scan.nextDouble();
        while(deltaX == 0.0){
            System.out.print("Шаг не должен быть 0. Повторите: ");
            deltaX = scan.nextDouble();
        }

        if (xStart < xEnd && deltaX < 0) {
            deltaX = Math.abs(deltaX);
            System.out.println("[info] Шаг сделан положительным.");
        } else if (xStart > xEnd && deltaX > 0) {
            deltaX = -Math.abs(deltaX);
            System.out.println("[info] Шаг сделан отрицательным.");
        }

        //header table

        String line = "--------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-4s | %-16s | %-50s |\n", "N", "X", "F(x)");
        System.out.println(line);

        int row = 1;
        double EPS = 1e-12;

        if (deltaX > 0) {
            for (double x = xStart; x <= xEnd + EPS; x += deltaX) {
                // F1 and F2
                String cell;

                //f1 = log_{|num/10|+4}((1-num)/cos(x-num))

                double base = Math.abs(num / 10.0) + 4.0;

                if (base <= 0.0) {
                    cell = "Ошибка: основание логарифма <= 0";
                    //cell = "Ошибка";
                } else if (base == 1.0){
                    cell = "Ошибка: основание логарифма == 1";
                    //cell = "Ошибка";
                } else {
                    double cos = Math.cos(x - num);
                    if (cos == 0.0) {
                        cell = "Ошибка: cos(x - num) = 0";
                        //cell = "Ошибка";
                    } else {
                        double arg = (1.0 - num) / cos;
                        if(arg <= 0.0) {
                            cell = "Ошибка: логарифм от неположительного";
                            //cell = "Ошибка";
                        } else {
                            double f1 = Math.log(arg) / Math.log(base);

                            // f2 = sin(log(x)) / num
                            if (x <= 0.0) {
                                cell = "Ошибка: ln(x) при x ≤ 0";
                                //cell = "Ошибка";
                            } else if (num == 0.0) {
                                cell = "Ошибка: деление на 0 (num = 0)";
                                //cell = "Ошибка";
                            } else {
                                double f2 = Math.sin(Math.log(x)) / num;

                                // min(f1, f2)
                                double fx = Math.min(f1, f2);
                                cell = String.format("%.15f", fx);
                            }
                        }
                    }
                }

                //print
                if (cell.length() > 40) {
                    cell = cell.substring(0, 50) + "...";
                }
                String formattedX = new BigDecimal(x).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
                System.out.printf("| %-4d | %-16s | %-50s |\n", row++, formattedX, cell);


            }
        } else {
            for (double x = xStart; x >= xEnd - EPS; x += deltaX) {
                String cell;

                double base = Math.abs(num / 10.0) + 4.0;
                if (base <= 0.0) {
                    cell = "Ошибка: основание логарифма ≤ 0";
                    //cell = "Ошибка";
                } else if (base == 1.0) {
                    cell = "Ошибка: основание логарифма = 1";
                    //cell = "Ошибка";
                } else {
                    double cos = Math.cos(x - num);
                    if (cos == 0.0) {
                        cell = "Ошибка: cos(x - num) = 0";
                        //cell = "Ошибка";
                    } else {
                        double arg = (1.0 - num) / cos;
                        if (arg <= 0.0) {
                            cell = "Ошибка: логарифм от неположительного";
                            //cell = "Ошибка";
                        } else {
                            double f1 = Math.log(arg) / Math.log(base);

                            if (x <= 0.0) {
                                cell = "Ошибка: ln(x) при x ≤ 0";
                                //cell = "Ошибка";
                            } else if (num == 0.0) {
                                cell = "Ошибка: деление на 0 (num = 0)";
                                //cell = "Ошибка";
                            } else {
                                double f2 = Math.sin(Math.log(x)) / num;
                                double fx = Math.min(f1, f2);
                                cell = String.format("%.15f", fx);
                            }
                        }
                    }
                }

                if (cell.length() > 50) {
                    cell = cell.substring(0, 50) + "...";
                }
                String formattedX = new BigDecimal(x).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
                System.out.printf("| %-4d | %-16s | %-50s |\n", row++, formattedX, cell);

                //System.out.printf("| %-4d | %-14.6f | %-50s |\n", row++, x, cell);
            }
        }

        // footer
        System.out.println(line);



    }
}
