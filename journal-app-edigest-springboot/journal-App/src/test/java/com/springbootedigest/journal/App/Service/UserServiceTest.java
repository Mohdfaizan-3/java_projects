package com.springbootedigest.journal.App.Service;

import com.springbootedigest.journal.App.Repository.UserRepository;
import com.springbootedigest.journal.App.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;


    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "ravi",
    })
    void findByUserName(String name) {
        assertNotNull(userRepository.findByUsername(name), "failed for "+ name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,8,10",
    })
    void test(int a, int b, int res) {
        assertEquals(res, a+b);
    }
}
