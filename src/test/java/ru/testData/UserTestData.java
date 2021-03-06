package ru.testData;

import ru.model.Role;
import ru.model.User;
import util.TestMatcher;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class UserTestData {

    public static TestMatcher<User> USER_MATCHER = TestMatcher.getTestMatcher("registered", "roles", "statistics");
    public static final Integer USER_ID = 1;
    public static final Integer ADMIN_ID = 2;
    public static final Integer INCORRECT_ID = 25;

    public static final User USER = new User(1,
            "Alex",
            "password",
            "a@gmail.com",
            Role.ROLE_USER);
    public static final User ADMIN = new User(2,
            "Jack",
            "admin",
            "j@gmail.com",
            Role.ROLE_ADMIN);


    public static User getNew() {
        return new User(null, "User", "pass", "u@gmail.com", true, new Date(), Role.ROLE_USER);
    }


}
