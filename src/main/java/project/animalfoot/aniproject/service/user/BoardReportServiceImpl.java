package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.BoardReportDTO;
import project.animalfoot.aniproject.repository.BoardReportRepository;
import project.animalfoot.aniproject.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardReportServiceImpl implements BoardReportService {

    private final BoardReportRepository boardReportRepository;

    private final BoardRepository boardRepository;

    @Override
    public void reportBoard(BoardReportDTO reportDTO) {

        boardReportRepository.insertReport(reportDTO);

        boardRepository.updateReportStatus(reportDTO.getBdNo());
    }
}
