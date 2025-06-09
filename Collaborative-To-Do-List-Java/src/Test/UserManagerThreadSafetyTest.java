package Test;

import model.User;
import java.util.concurrent.CountDownLatch;

public class UserManagerThreadSafetyTest {
    public static void main(String[] args) throws InterruptedException {
        final UserManager userManager = new UserManager();
        final int threadCount = 100;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        Thread[] threads = new Thread[threadCount];

        // Each thread tries to register a unique user
        for (int i = 0; i < threadCount; i++) {
            final int idx = i;
            threads[i] = new Thread(() -> {
                String username = "user" + idx;
                userManager.register(username);
                latch.countDown();
            });
            threads[i].start();
        }

        latch.await();

        // Now, check that all users exist
        boolean allExist = true;
        for (int i = 0; i < threadCount; i++) {
            String username = "user" + i;
            if (!userManager.userExists(username)) {
                allExist = false;
                System.out.println("Missing: " + username);
            }
        }
        if (allExist) {
            System.out.println("PASS: All users registered concurrently exist in the UserManager.");
        } else {
            System.out.println("FAIL: Some users are missing after concurrent registration.");
        }
    }
}
