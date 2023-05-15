package ch.bbw.m183.vulnerapp.repository;

import ch.bbw.m183.vulnerapp.datamodel.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, UUID> {

}
