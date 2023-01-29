import java.util.concurrent.*;

public class demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> "hello thread!";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callable);
        System.out.println(future.get());
        executorService.shutdown();
    }

}