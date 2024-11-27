package sdk.service;

import com.xuanluan.mc.practices.SdkStarterApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

@SpringBootTest(classes = SdkStarterApplication.class)
public class MessageLocaleTest {
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Test
    void testWhenUseCodeAsDefaultWhenNotFound() {
        String code = "kkk";
        String text = messageSourceAccessor.getMessage("kkk", new Locale("en"));
        Assertions.assertEquals(code, text);
    }
}
