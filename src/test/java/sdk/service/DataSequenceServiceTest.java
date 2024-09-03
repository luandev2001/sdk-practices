package sdk.service;

import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.sdk.model.entity.DataSequence;
import com.xuanluan.mc.sdk.model.enums.SequenceType;
import com.xuanluan.mc.sdk.service.IDataSequenceService;
import com.xuanluan.mc.sdk.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.xuanluan.mc.practices.SdkStarterApplication;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SdkStarterApplication.class)
public class DataSequenceServiceTest {
    @Autowired
    private IDataSequenceService dataSequenceService;

    @Value("${sequence.alphabet_dot_no.suffix.max}")
    private int maxSuffix;

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

    @Test
    public void testGenerateAlphabetDotNumber() {
        Class<UserPractice> userPracticeClass = UserPractice.class;
        SequenceType type = SequenceType.ALPHABET_DOT_NO;
        DataSequence dataSequence = dataSequenceService.get(userPracticeClass, type);
        if (dataSequence == null) dataSequence = dataSequenceService.increase(userPracticeClass, type);

        assertNotNull(dataSequence);
        assertEquals(userPracticeClass.getSimpleName(), dataSequence.getObjectType());
        assertEquals(type, dataSequence.getType());

        String[] alphabetNumber = dataSequence.getValue().split("\\.");
        assertTrue(StringUtils.hasText(dataSequence.getValue()) && alphabetNumber.length == 2);

        // increase value follow number process
        int nProcess = 1000;
        AtomicReference<String> preValue = new AtomicReference<>(dataSequence.getValue());
        DataSequence afterDataSequence = dataSequenceService.increase(userPracticeClass, type, nextSequence -> {
            assertEquals(StringUtils.generateAlphabetDotNoCode(preValue.get(), maxSuffix), nextSequence);
            preValue.set(nextSequence);
        }).apply(nProcess);
        assertEquals(afterDataSequence.getValue(), preValue.get());
        String[] afterAlphabetNumber = preValue.get().split("\\.");
        assertEquals(afterAlphabetNumber.length, 2);

        if (Objects.equals(alphabetNumber[0], afterAlphabetNumber[0])) {
            assertEquals(Long.parseLong(alphabetNumber[1]), Long.parseLong(afterAlphabetNumber[1]) - nProcess);
        } else {
            assertTrue(alphabetNumber[0].length() <= afterAlphabetNumber[0].length());
            long difference = 0;
            for (int i = 0; i < afterAlphabetNumber[0].length(); i++) {
                difference += afterAlphabetNumber[0].charAt(i);
                if (alphabetNumber[0].length() > i) difference -= alphabetNumber[0].charAt(i);
            }
            assertTrue(difference > 0);
        }

        // get data from db to check
        DataSequence currentDataSequence = dataSequenceService.get(userPracticeClass, type);
        assertNotNull(currentDataSequence);
        assertEquals(afterDataSequence.getValue(), currentDataSequence.getValue());
    }
}
