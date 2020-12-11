package pack.repository;

import pack.model.DirectorIdNameProb;
import pack.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository /*указывает, что это класс, который выполняет роль или стереотип (объект доступа к данным (DAO)) хранилища*/
public interface DirectorJpaRepository extends JpaRepository<Director, Integer> /*класс-сущность и тип id*/ {
    @Query(value = "select director from Director as director where director.id = ?1" /*:id*/)
    Director findDirectorById(/*@Param("id")*/ Integer id);

    @Query(value = "select distinct genres.genre from Director as director join director.directorsGenres as genres")
    List<String> getGenres();

    @Query(value = "select new pack.model.DirectorIdNameProb(director.id, director.firstName, director.lastName, genres.prob) " +
            "from Director as director inner join director.directorsGenres as genres where genres.genre = ?1" /*:genre*/)
    Page<DirectorIdNameProb> findAllByGenre(/*@Param("genre")*/ String genre, Pageable page);
}