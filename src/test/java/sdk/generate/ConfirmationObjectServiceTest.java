package sdk.generate;

import com.xuanluan.mc.practices.SdkStarterApplication;
import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.sdk.generate.model.dto.ConfirmationObjectDTO;
import com.xuanluan.mc.sdk.generate.model.entity.ConfirmationObject;
import com.xuanluan.mc.sdk.generate.model.enums.PeriodTime;
import com.xuanluan.mc.sdk.generate.service.IConfirmationObjectService;
import com.xuanluan.mc.sdk.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SdkStarterApplication.class)
public class ConfirmationObjectServiceTest {
    @Autowired
    private IConfirmationObjectService confirmationObjectService;

    @Test
    public void test() {
        ConfirmationObjectDTO<UserPractice> confirmationObjectDTO = ConfirmationObjectDTO.<UserPractice>builder()
                .object(UserPractice.class)
                .objectId(StringUtils.generateId())
                .type("test")
                .expiredNum(30)
                .period(PeriodTime.SECOND)
                .build();

        String token = confirmationObjectService.create(confirmationObjectDTO);
        assertTrue(StringUtils.hasText(token));
        assertEquals(token.length(), confirmationObjectDTO.getLengthDigit());

        ConfirmationObject confirmationObject = confirmationObjectService.validate(confirmationObjectDTO.getObject(), confirmationObjectDTO.getObjectId(), confirmationObjectDTO.getType(), token);
        assertNotNull(confirmationObject);
    }
}
