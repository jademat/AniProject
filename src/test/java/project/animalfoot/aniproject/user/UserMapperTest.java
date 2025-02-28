package project.animalfoot.aniproject.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.TestConstructor;

@Slf4j
@MybatisTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserMapperTest {
}
