package sdk.service;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.sdk.domain.entity.DataSequence;
import com.xuanluan.mc.sdk.domain.enums.SequenceType;
import com.xuanluan.mc.sdk.service.IDataSequenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.xuanluan.mc.practices.SdkStarterApplication;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SdkStarterApplication.class)
public class DataSequenceServiceTest {
    @Autowired
    private IDataSequenceService dataSequenceService;

    @Test
    public void testGenerateNumber() {
        Class<UserPractice> userPracticeClass = UserPractice.class;
        SequenceType type = SequenceType.NUMBER;
        DataSequence dataSequence = dataSequenceService.get(userPracticeClass, type);
        if (dataSequence == null) dataSequence = dataSequenceService.increase(userPracticeClass, type);

        assertNotNull(dataSequence);
        assertTrue(Long.parseLong(dataSequence.getValue()) > 0);
        assertEquals(userPracticeClass.getSimpleName(), dataSequence.getObjectType());
        assertEquals(type, dataSequence.getType());

        // increase value follow number process
        int nProcess = 3;
        AtomicLong preValue = new AtomicLong(Long.parseLong(dataSequence.getValue()));
        DataSequence afterDataSequence = dataSequenceService.increase(userPracticeClass, type, nextSequence -> {
            long nextValue = Long.parseLong(nextSequence);
            assertTrue(preValue.get() < nextValue);
            preValue.set(nextValue);
        }).apply(nProcess);
        assertEquals(Long.parseLong(afterDataSequence.getValue()), preValue.get());
        assertEquals(Long.parseLong(dataSequence.getValue()), preValue.get() - nProcess);

        // get data from db to check
        DataSequence currentDataSequence = dataSequenceService.get(userPracticeClass, type);
        assertNotNull(currentDataSequence);
        assertEquals(afterDataSequence.getValue(), currentDataSequence.getValue());
    }
}
