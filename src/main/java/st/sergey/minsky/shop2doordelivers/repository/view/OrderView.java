package st.sergey.minsky.shop2doordelivers.repository.view;


import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderView {
    String getUsername();

    List<Product> getProducts();

    String getDate();
    String getOrderStatus();



}
