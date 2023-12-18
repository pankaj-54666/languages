package mockito.service;


import mockito.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
