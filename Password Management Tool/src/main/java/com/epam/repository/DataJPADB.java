package com.epam.repository;

import com.epam.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJPADB extends CrudRepository<User, Long>
{

}
