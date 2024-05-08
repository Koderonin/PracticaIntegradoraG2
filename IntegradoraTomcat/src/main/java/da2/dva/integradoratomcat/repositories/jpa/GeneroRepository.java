package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.collections.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, String> {

    Genero findBySiglas(String siglas);
}