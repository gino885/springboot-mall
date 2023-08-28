package com.gino.springbootmall.rowmapper;

import com.gino.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(resultSet.getInt("order_id"));
        orderItem.setAmount(resultSet.getInt("amount"));
        orderItem.setOrderId(resultSet.getInt("order_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setProductId(resultSet.getInt("product_id"));

        orderItem.setProductName(resultSet.getString("product_name"));
        orderItem.setImageUrl(resultSet.getString("image_url"));

        return orderItem;
    }

}
