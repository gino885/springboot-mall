package com.gino.springbootmall.rowmapper;

import com.gino.springbootmall.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setCreatedDate(resultSet.getDate("created_date"));
        order.setLastModifiedDate(resultSet.getDate("last_modified_date"));
        order.setTotalAmount(resultSet.getInt("total_amount"));
        order.setUserId(resultSet.getInt("user_id"));

        return order;
    }
}
