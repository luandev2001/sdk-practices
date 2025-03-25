package sdk.jpa;

import com.github.javafaker.Faker;
import com.xuanluan.mc.practices.SdkStarterApplication;
import com.xuanluan.mc.practices.entity.UserPractice;
import com.xuanluan.mc.practices.repository.user_practice.UserPracticeRepository;
import com.xuanluan.mc.practices.request.page.UserPracticePrefixSearchParameter;
import com.xuanluan.mc.practices.request.page.UserPracticeSuffixSearchParameter;
import com.xuanluan.mc.sdk.model.enums.AttributeAction;
import com.xuanluan.mc.sdk.model.request.page.FilterParameter;
import com.xuanluan.mc.sdk.model.request.page.SortParameter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SdkStarterApplication.class)
public class JpaBulkDataTest {
    @Autowired
    private UserPracticeRepository userPracticeRepository;

    @Test
    void testBulkDataImportWithSaveAll() {
        int size = 100;
        LinkedList<UserPractice> userPractices = new LinkedList<>();
        Instant currentDate = Instant.now();
        for (int i = 0; i < size; i++) {
            UserPractice userPractice = new UserPractice();
//            userPractice.setUsername("test" + i);
            userPractice.setUsername(Faker.instance().name().username());
            userPractice.setCreatedAt(currentDate.plus(5, ChronoUnit.MINUTES));
            userPractices.add(userPractice);
        }
        userPracticeRepository.saveAll(userPractices);
        System.out.println("testBulkDataImportWithSaveAll DONE!!!");
    }

    @Nested
    class BaseRepositoryV2Test {
        @Test
        void testDynamicFilterAndSort() {
            UserPracticePrefixSearchParameter parameter = new UserPracticePrefixSearchParameter();
            parameter.setSize(10);
            parameter.setSorts(Collections.singletonList(
                    SortParameter.builder()
                            .name("createdAt")
                            .direction(Sort.Direction.DESC)
                            .build()
            ));

            Set<String> usernameFilter = Set.of("test", "test1", "test4");
            parameter.setFilters(Collections.singletonList(
                    FilterParameter.builder()
                            .name("username")
                            .operator(AttributeAction.Operator.in)
                            .value(usernameFilter)
                            .build()
            ));

            Instant indexDate = null;
            Page<UserPractice> userPracticePage = userPracticeRepository.getPage(parameter);
            Assertions.assertEquals(userPracticePage.getSize(), parameter.getSize());
            for (UserPractice userPractice : userPracticePage) {
                Assertions.assertTrue(usernameFilter.contains(userPractice.getUsername()));

                Assertions.assertNotNull(userPractice.getCreatedAt());
                if (indexDate != null) {
                    Assertions.assertTrue(indexDate.compareTo(userPractice.getCreatedAt()) >= 0);
                }
                indexDate = userPractice.getCreatedAt();
            }
            System.out.println("testDynamicFilterAndSort DONE!!!");
        }

        @Test
        void testPrefixTextSearch() {
            UserPracticePrefixSearchParameter parameter = new UserPracticePrefixSearchParameter();
            parameter.setSize(10);
            parameter.setKeyword("ar");

            Page<UserPractice> userPracticePage = userPracticeRepository.getPage(parameter);
            Assertions.assertEquals(userPracticePage.getSize(), parameter.getSize());
            for (UserPractice userPractice : userPracticePage) {
                Assertions.assertNotNull(userPractice.getUsername());

                boolean isMatchKeyword = userPractice.getUsername().matches("(?i)^" + parameter.getKeyword() + ".*");
                Assertions.assertTrue(isMatchKeyword);
            }
        }

        @Test
        void testSuffixTextSearch() {
            UserPracticeSuffixSearchParameter parameter = new UserPracticeSuffixSearchParameter();
            parameter.setSize(10);
            parameter.setKeyword("ar");

            Page<UserPractice> userPracticePage = userPracticeRepository.getPage(parameter);
            Assertions.assertEquals(userPracticePage.getSize(), parameter.getSize());
            for (UserPractice userPractice : userPracticePage) {
                Assertions.assertNotNull(userPractice.getUsername());

                boolean isMatchKeyword = userPractice.getUsername().matches("(?i).*" + parameter.getKeyword() + "$");
                Assertions.assertTrue(isMatchKeyword);
            }
        }
    }
}
