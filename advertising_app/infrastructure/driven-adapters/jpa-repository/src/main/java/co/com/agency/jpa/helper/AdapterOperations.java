package co.com.agency.jpa.helper;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

public abstract class AdapterOperations<E, D, I, R extends CrudRepository<D, I> & QueryByExampleExecutor<D>> {
    protected R repository;
    private Class<D> dataClass;
    private Class<E> entityClass;
    protected SimpleMapper mapper;

    public AdapterOperations(R repository, SimpleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    protected D toData(E entity) {
        return (D) mapper.toData(entity);
    }

    protected E toEntity(D data) {
        return data != null ? (E) mapper.toEntity(data) : null;
    }

    public E save(E entity) {
        D data = toData(entity);
        return toEntity(saveData(data));
    }

    protected List<E> saveAllEntities(List<E> entities) {
        List<D> list = entities.stream().map(this::toData).collect(Collectors.toList());
        return toList(saveData(list));
    }

    public List<E> toList(Iterable<D> iterable) {
        return stream(iterable.spliterator(), false).map(this::toEntity).collect(Collectors.toList());
    }

    protected D saveData(D data) {
        return repository.save(data);
    }

    protected Iterable<D> saveData(List<D> data) {
        return repository.saveAll(data);
    }

    public E findById(I id) {
        return toEntity(repository.findById(id).orElse(null));
    }

    public List<E> findByExample(E entity) {
        return toList(repository.findAll( Example.of(toData(entity))));
    }


    public List<E> findAll(){
        return toList(repository.findAll());
    }
}
