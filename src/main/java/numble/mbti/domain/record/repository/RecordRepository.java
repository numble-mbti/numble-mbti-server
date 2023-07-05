package numble.mbti.domain.record.repository;

import numble.mbti.domain.record.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<UserRecord, Long> {
}
