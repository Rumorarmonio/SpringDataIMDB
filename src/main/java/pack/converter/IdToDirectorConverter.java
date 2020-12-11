package pack.converter;

import pack.model.Director;
import pack.repository.DirectorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToDirectorConverter implements Converter<Integer, Director> {

    @Autowired
    private DirectorJpaRepository repositoryDirector;

    @Override
    public Director convert(Integer id) {
        return this.repositoryDirector.getOne(id);
    }
}
