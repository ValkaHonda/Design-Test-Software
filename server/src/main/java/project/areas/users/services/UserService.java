package project.areas.users.services;

import project.areas.users.models.bidingModels.UserRegisterForm;

public interface UserService {
    void saveUserToDatabase(final UserRegisterForm user);
}
