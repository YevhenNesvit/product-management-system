package spring.boot.converter;

import java.util.List;

public interface Converter<E, T> {

    E from(T entity);

    List<E> fromList(List<T> list);
}
