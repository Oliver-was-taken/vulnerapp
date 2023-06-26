package ch.bbw.m183.vulnerapp;

import ch.bbw.m183.vulnerapp.datamodel.BlogEntity;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VulnerApplicationTests implements WithAssertions {

    @Autowired
    WebTestClient webTestClient;

    /**
     * Everyone can get blogs!
     */
    @Test
    void blogsArePublic() {
        webTestClient.get().uri("/api/blog")
                .exchange()
                .expectStatus().isOk();
    }

    /**
     * Admin panel is role secured
     */
    @Test
    void adminIsOnlyAdmins() {
        webTestClient.get().uri("/api/admin123")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    /**
     * Post onto /api/blog without CSRF-Token and Without User
     */
    @Test
    void insertBlogForbidden() {
        webTestClient.post().uri("/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(BlogEntity.builder().id(UUID.randomUUID()).title("Spannender Test Blog").body("Testee").createdAt(LocalDateTime.MIN).build()))
                .exchange()
                .expectStatus().isForbidden();
    }

    /**
     * Post onto /api/blog with CSRF-Token and Without User
     */
    @Test
    void insertBlogUnauthorized() {
        webTestClient.post().uri("/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-XSRF-TOKEN", "Test-token")
                .cookie("XSRF-TOKEN", "Test-token")
                .body(BodyInserters.fromValue(BlogEntity.builder().id(UUID.randomUUID()).title("Spannender Test Blog").body("Testee").createdAt(LocalDateTime.MIN).build()))
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
