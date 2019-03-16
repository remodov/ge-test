package ru.general.electrics.test.utils;

import ru.general.electrics.test.dto.Message;

import java.util.Random;

/**
 * Some random generates
 */
public class RandomUtil {

    private RandomUtil() {

    }

    public static Message generateMessage() {
        return new Message(RandomUtil.generateRandomString(), RandomUtil.getRandomPrior());
    }

    private static String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;

        Random random = new Random();

        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    private static Integer getRandomPrior() {
        return new Random().nextInt(3);
    }

    public static void randomSleepCurrentThread(){
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
