package utils;

import userDecorator.DefaultUser;
import userDecorator.User;

import java.util.stream.Stream;

public class DataUtils {

    private static Stream<User> provideUserData() {
        return Stream.of(new DefaultUser("Arina", "1234ranb")
                , new DefaultUser("Kate", "testfortest!"));
    }
}
