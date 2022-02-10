package test.junit.youtubestudy;

import org.kohsuke.github.GitHub;

import java.io.IOException;

public interface GithubService {

    GitHub connect() throws IOException;
}
