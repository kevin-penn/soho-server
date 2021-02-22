package peng.ziliang.soho.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import peng.ziliang.soho.model.User;

import java.util.Optional;


public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    @Query(value = "select * from `user` where tid='100'")
    User queryCustom();

    Optional<User> findUserByEmail(String email);

}
