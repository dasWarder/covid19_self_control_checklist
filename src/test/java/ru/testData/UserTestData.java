package ru.testData;

import ru.model.Role;
import ru.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class UserTestData {
    public static final Integer USER_ID = 1;
    public static final Integer ADMIN_ID = 2;

    public static final User USER = new User(1,
            "Alex",
            "password",
            "a@gmail.com",
            Role.USER);
    public static final User ADMIN = new User(2,
            "Jack",
            "admin",
            "j@gmail.com",
            Role.ADMIN);


    public static User getNew() {
        return new User(null, "User", "pass", "u@gmail.com", new Date(), Role.USER);
    }


}
