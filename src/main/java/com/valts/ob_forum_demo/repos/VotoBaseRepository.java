package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoBaseRepository<T extends Voto> extends JpaRepository<T, Long> {
}
