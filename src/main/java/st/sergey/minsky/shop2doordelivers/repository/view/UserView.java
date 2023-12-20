package st.sergey.minsky.shop2doordelivers.repository.view;

import java.time.LocalDateTime;

public interface UserView {
    String getUsername();
    String getEmail();
    LocalDateTime createdDate();
}
