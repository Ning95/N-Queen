import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 单线程解决 N-Queen
 */
public class SingleThreadQueen {
    private static final int K = 16;        //使用常量来定义，方便之后解N皇后问题
    private static int count = 0;            //结果计数器
    private static int N;

    public static void main(String[] args) {
        File file = new File("data/single_thread.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            for (N = 8; N <= K; N++) {
                Date begin = new Date();
                // chess[n] = X:表示第n行X列有一个皇后
                int chess[] = new int[N];
                putQueenAtRow(chess, 0);
                Date end = new Date();
                String time = String.valueOf(end.getTime() - begin.getTime());
                writer.write(time);
				if (N != K) {
					writer.write(",");
				}
                System.out.println("解决 " + N + " 皇后问题，用时：" + time + "毫秒，计算结果：" + count);
                count = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    private static void putQueenAtRow(int[] chess, int row) {
        // 递归终止判断：如果row==N，则说明已经成功摆放了 N个皇后
        if (row == N) {
            count++;
            return;
        }
        //向这一行的每一个位置尝试排放皇后
        //然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
        for (int i = 0; i < N; i++) {
            //摆放这一行的皇后
            chess[row] = i;

            if (isSafety(chess, row, i)) {
                putQueenAtRow(chess, row + 1);
            }
        }
    }

    private static boolean isSafety(int[] chess, int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col行第row列已经摆放了皇后
            if (chess[i] == col) {
                return false;
            }
            // 第i行的皇后根第row行第col列格子处在同一斜线上
            // 45度角斜线: y-y0 = (x-x0), 则 (y-y0)/(x-x0) = 1, 表示为45度角的斜线
            if (Math.abs(col - chess[i]) == row - i) {
                return false;
            }
        }
        return true;
    }
}