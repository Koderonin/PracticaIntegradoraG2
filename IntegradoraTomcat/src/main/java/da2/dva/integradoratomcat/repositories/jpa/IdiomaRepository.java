package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.collections.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, String> {

}
