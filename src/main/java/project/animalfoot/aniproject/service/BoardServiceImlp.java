package project.animalfoot.aniproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImlp implements BoardService {

    private final BoardRepository boardMapper;
}
