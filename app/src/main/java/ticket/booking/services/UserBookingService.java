package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService() throws IOException {
        userList = loadUsers();
    }

    public UserBookingService(User providedUser) throws IOException {
        this.user = providedUser;
        userList = loadUsers();
    }

    public List<User> loadUsers () throws IOException {
        File users = new File(USERS_PATH);
       return objectMapper.readValue(users, new TypeReference<List<User>>(){});
    }

    public Boolean loginUser () {
        Optional<User> foundUser = userList.stream().filter(eachUser -> {
            return eachUser.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), eachUser.getHashedPassword());
        }).findFirst();

        return foundUser.isPresent();
    }

    public Boolean signUp(User signupUser) {
        try {
                userList.add(signupUser);
                saveUserListToFile();
                return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

}
