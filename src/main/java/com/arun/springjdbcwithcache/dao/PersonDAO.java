package com.arun.springjdbcwithcache.dao;

import com.arun.springjdbcwithcache.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {

    Logger logger = LoggerFactory.getLogger(PersonDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable(value = "person")
    public List<Person> findById(final int id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StopWatch stopWatch = new StopWatch(Integer.toString(id));
        stopWatch.start();
        List<Person> persons = jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new PersonRowMapper());
        stopWatch.stop();
        logger.info("Time taken is {}", stopWatch);
        return persons;
    }

    @CacheEvict(value = "person", key = "#person.id")
    public int updateById(final Person person){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StopWatch stopWatch = new StopWatch(person.getName());
        stopWatch.start();
        int result = jdbcTemplate.update("UPDATE person SET name = ?,location = ? ,birth_date = ? WHERE id = ?", new Object[]{
                person.getName(), person.getLocation(), person.getBirth_date(), person.getId()});
        stopWatch.stop();
        logger.info("Time taken is {}", stopWatch);
        return result;
    }


    private static final class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt(1));
            person.setName(rs.getString(2));
            person.setLocation(rs.getString(3));
            person.setBirth_date(rs.getDate(4));
            return person;
        }
    }
}
