package ru.otus.dao.impl;

import ru.otus.dao.GoodDAO;
import ru.otus.exception.ProductNotFoundException;
import ru.otus.model.Good;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class GoodDAOImpl implements GoodDAO {

    private static final String QUERY_SELECT_FROM_GOOD = "from Good";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Good> getAll() {
        return entityManager.createQuery(QUERY_SELECT_FROM_GOOD).getResultList();
    }

    @Override
    public Good getByTitleAndPrice(String title, String price) {
        return getAll().stream()
                .filter(good -> title.equals(good.getTitle())
                        && price.equals(String.valueOf(good.getPrice())))
                .findAny()
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with title %s and price %s not found", title, price)));
    }
}
