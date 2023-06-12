package com.myjdbc.Customer.repoImpl;
import com.myjdbc.Customer.model.CustomerInfo;
import com.myjdbc.Customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   /* public List<CustomerInfo> findAll() {
        String sql = "SELECT * FROM customer_info";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CustomerInfo customer = new CustomerInfo();

            customer.setName(rs.getString("name"));
            customer.setLast_name(rs.getString("last_name"));
            customer.setCity(rs.getString("city"));
            customer.setCountry(rs.getString("country"));
            customer.setAddress(rs.getString("address"));

            return customer;
        });
    }*/
///// Insert method
    public <S extends CustomerInfo> S save(S customer) {
        // Implement save logic here
        String sql = "INSERT INTO customer_info (name, last_name, city, country, address) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getLast_name(), customer.getCity(), customer.getCountry(), customer.getAddress());
        return customer;
    }

    //// Save All Logic -----Insert method
    public <S extends CustomerInfo> Iterable<S> saveAll(Iterable<S> customers) {

        List<S> savedCustomers = new ArrayList<>();
        for(S customer :customers) {
            String name = customer.getName();
            String last_name = customer.getLast_name();
            String city = customer.getCity();
            String country = customer.getCountry();
            String address = customer.getAddress();
            // Implement save logic here
            String sql = "INSERT INTO customer_info (name, last_name, city, country, address) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, name, last_name, city, country, address);

            savedCustomers.add(customer);
        }
        return savedCustomers;
    }
    /// Id
    public Optional<CustomerInfo> findById(Integer id) {
        String sql = "SELECT * FROM customer_info WHERE id = ?";
        List<CustomerInfo> customers = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            CustomerInfo customer = new CustomerInfo();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setLast_name(rs.getString("last_name"));
            customer.setCity(rs.getString("city"));
            customer.setCountry(rs.getString("country"));
            customer.setAddress(rs.getString("address"));
            return customer;
        });

        return customers.stream().findFirst();
    }



    ///Exist by id
    public boolean existsById(Integer id) {
        // Implement exists by ID logic here
        String sql = "SELECT COUNT(*) FROM customer_info WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }
    ///Find all method
    public List<CustomerInfo> findAll() {
        // Implement find all logic here
        String sql = "SELECT * FROM customer_info";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CustomerInfo customer = new CustomerInfo();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setLast_name(rs.getString("last_name"));
            customer.setCity(rs.getString("city"));
            customer.setCountry(rs.getString("country"));
            customer.setAddress(rs.getString("address"));
            return customer;
        });
    }
    ///Find all by ID
    public Iterable<CustomerInfo> findAllById(Iterable<Integer> ids) {
        // Implement find all by ID logic here
        List<CustomerInfo> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer_info WHERE id IN (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, parameters);
        for (Map<String, Object> row : rows) {
            CustomerInfo customer = new CustomerInfo();
            customer.setId((Integer) row.get("id"));
            customer.setName((String) row.get("name"));
            customer.setLast_name((String) row.get("last_name"));
            customer.setCity((String) row.get("city"));
            customer.setCountry((String) row.get("country"));
            customer.setAddress((String) row.get("address"));
            customers.add(customer);
        }
        return customers;
    }
/////// Count Method
public long count() {
    // Implement count logic here
    String sql = "SELECT COUNT(*) FROM customer_info";
    return jdbcTemplate.queryForObject(sql, Long.class);
}

    ///Delete Method
    public void deleteAll() {
        // Implement delete all logic here
        String sql = "DELETE FROM customer_info";
        jdbcTemplate.update(sql);
    }
    // Delete by ID
    public void deleteById(Integer id) {
        // Implement delete by ID logic here
        String sql = "DELETE FROM customer_info WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    /// Delete (T) method
    public void delete(CustomerInfo customer) {
        // Implement delete logic here
        String sql = "DELETE FROM customer_info WHERE id = ?";
        jdbcTemplate.update(sql, customer.getId());
    }

    //deleteAllById(Iterable<? extends ID>)
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // Implement delete all by ID logic here
        List<Integer> idList = new ArrayList<>();
        ids.forEach(idList::add);
        String sql = "DELETE FROM customer_info WHERE id IN (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", idList);
        jdbcTemplate.update(sql, parameters);
    }
    ////deleteAll(Iterable<? extends T>)

    public void deleteAll(Iterable<? extends CustomerInfo> customers) {
        // Implement delete all logic here
        List<Integer> ids = new ArrayList<>();
        for (CustomerInfo customer : customers) {
            ids.add(customer.getId());
        }
        String sql = "DELETE FROM customer_info WHERE id IN (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);
        jdbcTemplate.update(sql, parameters);
    }





}
