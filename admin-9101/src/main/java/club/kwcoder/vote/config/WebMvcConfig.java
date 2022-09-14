package club.kwcoder.vote.config;

import club.kwcoder.vote.Interceptor.CandidateInterceptor;
import club.kwcoder.vote.Interceptor.PollInterceptor;
import club.kwcoder.vote.Interceptor.VoteInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private VoteInterceptor voteInterceptor;

    @Autowired
    private PollInterceptor pollInterceptor;

    @Autowired
    private CandidateInterceptor candidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(voteInterceptor)
                .addPathPatterns("/vote/stop", "/vote/info", "/vote/sort", "/vote/delete");

        registry.addInterceptor(pollInterceptor)
                .addPathPatterns("/poll/info");

        registry.addInterceptor(candidateInterceptor)
                .addPathPatterns("/candidate/update", "/candidate/delete", "/candidate/recovery");
    }
}
