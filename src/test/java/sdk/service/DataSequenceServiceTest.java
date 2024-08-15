package sdk.service;

import com.xuanluan.mc.sdk.service.IDataSequenceService;
import com.xuanluan.mc.practices.entity.UserPractice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sdk.SdkStarterApplication;

@SpringBootTest(classes = SdkStarterApplication.class)
public class DataSequenceServiceTest {
    @Autowired
    private IDataSequenceService<UserPractice> dataSequenceService;

    @Test
    public void test() {

    }
}
