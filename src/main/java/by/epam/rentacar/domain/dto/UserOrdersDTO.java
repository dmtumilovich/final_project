package by.epam.rentacar.domain.dto;

import by.epam.rentacar.domain.entity.Order;

import java.util.List;

public class UserOrdersDTO {

    private List<UserOrderDTO> userOrderList;

    public UserOrdersDTO() {

    }

    public List<UserOrderDTO> getUserOrderList() {
        return userOrderList;
    }

    public void setOrderList(List<UserOrderDTO> userOrderList) {
        this.userOrderList = userOrderList;
    }
}
