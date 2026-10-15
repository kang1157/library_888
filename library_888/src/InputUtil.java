import java.util.Scanner;

public class InputUtil {

    /**
     * 安全读取整数：非法输入时提示并重新读取，直到拿到合法整数
     * @param scanner 输入流
     * @param prompt 提示信息
     * @return 读取到的整数
     */
    static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.next(); // 丢弃非法 token，防止死循环
            System.out.println("输入错误，请输入一个整数！");
        }
    }

    /**
     * 安全读取浮点数：非法输入时提示并重新读取，直到拿到合法浮点数
     * @param scanner 输入流
     * @param prompt 提示信息
     * @return 读取到的浮点数
     */
    static float readFloat(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextFloat()) {
                return scanner.nextFloat();
            }
            scanner.next();
            System.out.println("输入错误，请输入一个浮点数！");
        }
    }


}
