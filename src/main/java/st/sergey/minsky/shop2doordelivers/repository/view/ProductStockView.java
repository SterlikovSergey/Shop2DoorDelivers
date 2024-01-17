package st.sergey.minsky.shop2doordelivers.repository.view;

import java.math.BigDecimal;

public interface ProductStockView {
    Long getProductId();

    String getProductName();

    BigDecimal getProductPrice();

    Long getStockQuantity();

    Long getStoreId();

    String getStoreName();
}
