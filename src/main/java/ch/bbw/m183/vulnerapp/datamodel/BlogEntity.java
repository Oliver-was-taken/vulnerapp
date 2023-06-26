package ch.bbw.m183.vulnerapp.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Builder
@Table(name = "blogs")
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity {

    @Id
    UUID id;

    @Column
    @CreationTimestamp
    LocalDateTime createdAt;

    @NotBlank(message = "Title is mandatory")
    @Column(columnDefinition = "text")
    String title;

    @NotBlank(message = "Text-body is mandatory")
    @Column(columnDefinition = "text")
    String body;
}
