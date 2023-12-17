package mockito.data;

import mockito.model.User;

public interface UsersRepository {
    boolean save(User user);
}
