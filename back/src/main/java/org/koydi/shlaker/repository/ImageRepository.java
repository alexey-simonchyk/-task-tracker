package org.koydi.shlaker.repository;

import org.koydi.shlaker.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
