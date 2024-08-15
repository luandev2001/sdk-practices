package repository;

import com.xuanluan.mc.sdk.repository.JpaMultipleRepository;
import model.entity.UserPractice;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPracticeRepository extends JpaMultipleRepository<UserPractice, String> {
}
