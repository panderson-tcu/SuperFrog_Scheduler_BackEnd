package edu.tcu.cs.superfrogscheduler.appearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppearanceRepository extends JpaRepository<Appearance, String> {

    List<Appearance> findAppearancesByIdIn(List<String> appearanceRequestIdList);

}
