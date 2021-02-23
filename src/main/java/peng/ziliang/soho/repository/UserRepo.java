package peng.ziliang.soho.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import peng.ziliang.soho.model.User;

import java.util.Optional;


public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    @Query(value = UserRepoSql.CUSTOM_SQL)
    Optional<User> queryCustom();

    Optional<User> findUserByEmail(String email);

}
