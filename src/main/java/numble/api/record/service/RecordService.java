package numble.api.record.service;

import lombok.RequiredArgsConstructor;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.mbti.domain.record.service.RecordCommandService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordCommandService recordCommandService;

    public boolean saveUserResult(Long userId, UserMbtiResultRequest request) {
       return recordCommandService.saveUserResult(userId, request).isPresent();
    }
}
