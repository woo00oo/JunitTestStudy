package test.junit.youtubestudy;

import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RepositoryRank {

    private final GithubService githubService;

    public int getPoint(String repositoryName) throws IOException {

        //GitHub gitHub = GitHub.connect() 기존코드
        GitHub gitHub = githubService.connect(); // -> PSA를 사용함으로써 테스트 코드를 작성에 수월하다. 테스트 작성시, GitHubService 구현 클래스 생성.
        GHRepository repository = gitHub.getRepository(repositoryName);

        int points = 0;
        if (repository.hasIssues()) {
            points += 1;
        }

        if (repository.getReadme() != null ) {
            points += 1;
        }

        if (repository.getPullRequests(GHIssueState.CLOSED).size() > 0) {
            points += 1;
        }

        points += repository.getStargazersCount();
        points += repository.getForksCount();

        return points;

    }
}
