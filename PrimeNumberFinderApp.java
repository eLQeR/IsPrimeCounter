import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class PrimeNumberFinderApp {
    private static final Logger log = Logger.getLogger(PrimeNumberFinderApp.class.getName());

    public static void main(String[] args) {
        log.info("Старт програми з пошуку простих чисел.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число N (верхня межа): ");
        int upperLimit = scanner.nextInt();

        System.out.print("Введіть кількість потоків: ");
        int threadCount = scanner.nextInt();

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        List<Future<List<Integer>>> taskResults = new ArrayList<>();
        CopyOnWriteArrayList<Integer> primes = new CopyOnWriteArrayList<>();

        long startTime = System.nanoTime();

        try {
            int rangeSize = (upperLimit + threadCount - 1) / threadCount; // каунтер поділу на потоки

            for (int i = 0; i < threadCount; i++) {
                int start = i * rangeSize;
                int end = Math.min((i + 1) * rangeSize, upperLimit);
                taskResults.add(threadPool.submit(new PrimeRangeFinder(start, end)));
            }

            for (Future<List<Integer>> result : taskResults) {
                if (result.isDone()) {
                    try {
                        primes.addAll(result.get());
                    } catch (ExecutionException | InterruptedException e) {
                        log.log(Level.SEVERE, "Помилка потоку: ", e);
                    }
                }
            }
        } finally {
            threadPool.shutdown();
        }

        long endTime = System.nanoTime();

        log.info("Прості числа: " + primes);
        log.info("Час виконання програми: " + (endTime - startTime) / 1_000_000 + " мс");
    }
}

class PrimeRangeFinder implements Callable<List<Integer>> {
    private final int start;
    private final int end;
    private static final Logger log = Logger.getLogger(PrimeRangeFinder.class.getName());

    public PrimeRangeFinder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() {
        log.info("Обробляю діапазон [" + start + ", " + end + ")");
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        log.info("Завершив діапазон [" + start + ", " + end + ")");
        return primes;
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
