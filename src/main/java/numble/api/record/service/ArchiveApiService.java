package numble.api.record.service;

import lombok.RequiredArgsConstructor;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.mbti.domain.archive.service.ArchiveService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveApiService {

    private final ArchiveService archiveService;

    public boolean saveUserResult(Long userId, UserMbtiResultRequest request) {
       return archiveService.saveUserResult(userId, request).isPresent();
    }
}
