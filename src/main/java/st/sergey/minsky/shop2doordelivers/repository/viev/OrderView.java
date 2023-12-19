package st.sergey.minsky.shop2doordelivers.repository.viev;


import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderView {

    LocalDateTime getCreatedOrder();
    OrderStatus getOrderStatus();
    List<Product> getProducts();
    String getUsername();


}
