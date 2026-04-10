package com.example.simpleblind.application;

import com.example.simpleblind.domain.*;
import com.example.simpleblind.infra.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostViewLogRepository postViewLogRepository;

    public DataInitializer(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           PostRepository postRepository,
                           PostLikeRepository postLikeRepository,
                           PostViewLogRepository postViewLogRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.postViewLogRepository = postViewLogRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return; // 이미 seed 데이터 존재
        }

        // 유저 5명
        List<User> users = userRepository.saveAll(List.of(
                new User("개발자김씨", "kim@example.com"),
                new User("마케터이씨", "lee@example.com"),
                new User("디자이너박씨", "park@example.com"),
                new User("PM최씨", "choi@example.com"),
                new User("인사담당정씨", "jung@example.com")
        ));

        // 카테고리 5개
        List<Category> categories = categoryRepository.saveAll(List.of(
                new Category("자유"),
                new Category("회사생활"),
                new Category("이직/퇴사"),
                new Category("연봉/성과"),
                new Category("개발")
        ));

        // 게시글 25개
        String[] titles = {
                "오늘 점심 뭐 먹었나요?", "회사에서 가장 힘든 순간",
                "이직 준비 어떻게 하시나요?", "연봉 협상 팁 공유합니다", "Spring Boot 4 써보신 분?",
                "퇴근 후 자기계발 어떻게 하세요?", "상사가 너무 힘들어요", "이직 성공 후기",
                "성과급 받으셨나요?", "자바 vs 코틀린 뭐가 나은가요?",
                "워라밸 좋은 회사 추천해주세요", "회의가 너무 많아요", "취업 준비 꿀팁",
                "복지 좋은 회사 어디인가요?", "풀스택 개발자 되고 싶어요",
                "번아웃 극복하는 방법", "야근 수당 제대로 받으시나요?", "직장 동료와 갈등 해결법",
                "주니어 개발자 연봉 현실은?", "MSA 도입 경험 공유",
                "사수 없이 일하는 분들 계신가요?", "사내 정치 어떻게 대처하나요?",
                "개발팀 문화 좋은 회사 특징", "프리랜서 전향 고민 중입니다", "새 프로젝트 투입됐어요"
        };

        String[] contents = {
                "다들 점심 뭐 드셨나요? 저는 된장찌개 먹었는데 맛있었어요.",
                "야근이 너무 잦아서 힘드네요. 다들 어떻게 버티시나요?",
                "이직 준비 중인데 포트폴리오 어떻게 구성하면 좋을까요?",
                "연봉 협상할 때 어떤 근거를 제시하면 좋을지 공유해요.",
                "Spring Boot 4.0.5 드디어 나왔네요. 써보신 분 계신가요?",
                "퇴근 후 사이드 프로젝트 하시는 분들 팁 공유 부탁드려요.",
                "팀장이 매일 야근 강요해요. 어떻게 대처하면 좋을까요?",
                "6개월 준비 끝에 이직 성공했어요! 경험 공유합니다.",
                "올해 성과급 얼마나 받으셨나요? 기대보다 적어서 실망했어요.",
                "자바 오래 했는데 코틀린으로 전환할 만한 가치가 있을까요?",
                "워라밸 좋은 회사 추천해주세요. 대기업 or 중견기업?",
                "주당 회의 시간이 20시간이에요. 이거 정상인가요?",
                "신입 취준생인데 코딩테스트 어떻게 준비했는지 궁금해요.",
                "4대보험, 점심 지원, 유연 출퇴근 중 뭐가 제일 중요한가요?",
                "백엔드 하면서 프론트도 배우고 싶은데 어디서 시작할까요?",
                "번아웃 왔을 때 어떻게 극복하셨나요?",
                "야근 수당 안 주는 회사가 아직도 이렇게 많다니...",
                "입사 6개월 됐는데 사수와 사이가 좋지 않아요. 조언 부탁드려요.",
                "신입 개발자 초봉 3000이면 낮은 건가요?",
                "MSA 전환 프로젝트 진행 중인데 어렵네요. 경험 있으신 분?",
                "사수 없이 혼자 배우는 방법 공유해 주세요.",
                "사내 정치에 치이는데 어떻게 대처하면 좋을까요?",
                "코드 리뷰 문화가 잘 되어 있는 회사 특징이 뭔가요?",
                "프리랜서로 전향한 분들 수입이 어떻게 되시나요?",
                "새 프로젝트 투입됐는데 레거시 코드가 너무 많아요. 도움주세요."
        };

        List<Post> posts = postRepository.saveAll(
                java.util.stream.IntStream.range(0, 25)
                        .mapToObj(i -> new Post(
                                users.get(i % users.size()),
                                categories.get(i % categories.size()),
                                titles[i],
                                contents[i]
                        ))
                        .toList()
        );

        // 전일자 PostLike, PostViewLog 생성 (인기글 API 테스트용)
        LocalDateTime yesterday9am = LocalDateTime.now().minusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            int likeCount = Math.min(i % 5 + 1, users.size()); // 유니크 제약: 유저 수 초과 불가
            int viewCount = (i % 5 + 1) * 10;  // 10 ~ 50개

            for (int j = 0; j < likeCount; j++) {
                postLikeRepository.save(
                        new PostLike(post, users.get(j % users.size()), yesterday9am.plusMinutes(j))
                );
                post.incrementLikeCount();
            }

            for (int j = 0; j < viewCount; j++) {
                postViewLogRepository.save(
                        new PostViewLog(post, users.get(j % users.size()), yesterday9am.plusMinutes(j))
                );
                post.incrementViewCount();
            }

            postRepository.save(post);
        }
    }
}
