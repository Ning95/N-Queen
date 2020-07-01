import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 利用多线程来提升 N 皇后问题的运行速度
 */
public class ThreadPoolNQueen {
    private static int N; // 棋盘的大小
    private static int K = 16;

    // 线程池参数的设置
    private static final int CPU_CORE_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_CORE_SIZE;
    private static final int MAX_POOL_SIZE = CPU_CORE_SIZE;
    private static final int BLOCK_QUEUE_SIZE = 1000;


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(BLOCK_QUEUE_SIZE),
                new ThreadPoolExecutor.CallerRunsPolicy());
        File file = new File("data/n_thread.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            for (N = 8; N <= K; N++) {
                long count = 0;
                Date start = new Date();
                List<int[]> chessList = new ArrayList<>(N);
                for (int i = 0; i < N; i++) {
                    int chess[] = new int[N];
                    chess[0] = i;
                    chessList.add(chess);
                }

                int taskSize = N / 2 + (N % 2 == 1 ? 1 : 0);
                // 创建多个有返回值的任务
                List<Future<Long>> futureList = new ArrayList<>(taskSize);
                for (int i = 0; i < taskSize; i++) {
                    Callable<Long> c = new NQueensSolution(chessList.get(i));
                    // 执行任务并获取Future对象
                    Future<Long> f = executor.submit(c);
                    futureList.add(f);
                }

                for (int i = 0; i < taskSize - (N % 2 == 1 ? 1 : 0); i++) {
                    count += futureList.get(i).get();
                }
                count = count * 2;
                if (N % 2 == 1) {
                    count += futureList.get(N / 2).get();
                }
                Date end = new Date();
                String time = String.valueOf((end.getTime() - start.getTime()));
                writer.write(time);
                if (N != K) {
                    writer.write(",");
                }
                System.out.println("解决 " + N + "皇后问题，用时：" + time + "毫秒，计算结果：" + count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
			if (writer != null) {
                writer.close();
            }
            executor.shutdown();
        }
    }
}

class NQueensSolution implements Callable<Long> {
    private int[] chess;
    private int N;

    public NQueensSolution(int[] chess) {
        this.chess = chess;
        this.N = chess.length;
    }


    @Override
    public Long call() throws Exception {
        return putQueenAtRow(chess, 1);
    }

    private Long putQueenAtRow(int[] chess, int row) {
        if (row == N) {
            return (long) 1;
        }

        long sum = 0;
        // 向这一行的每一个位置尝试排放皇后
        // 然后检测状态，如果安全则继续执行递归函数摆放下一行皇
        for (short i = 0; i < N; i++) {
            //摆放这一行的皇后
            chess[row] = i;

            if (isSafety(chess, row, i)) {
                sum += putQueenAtRow(chess, row + 1);
            }
        }

        return sum;
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