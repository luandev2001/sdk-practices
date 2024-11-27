package jpa;

import com.xuanluan.mc.practices.SdkStarterApplication;
import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.practices.repository.UserPracticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SdkStarterApplication.class)
public class JpaBulkDataTest {
    @Autowired
    private UserPracticeRepository userPracticeRepository;

    @Test
    @Transactional
    public void testBulkDataImportWithSaveAll() {
        int size = 1000;
        for (int i = 0; i < size; i++) {
            UserPractice userPractice = new UserPractice();
            userPractice.setUsername(userPractice.getUsername());
        }
    }
}
