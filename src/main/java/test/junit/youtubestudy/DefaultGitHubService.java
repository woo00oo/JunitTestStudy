package test.junit.youtubestudy;

import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultGitHubService implements GithubService{
    @Override
    public GitHub connect() throws IOException {
        return GitHub.connect();
    }
}
