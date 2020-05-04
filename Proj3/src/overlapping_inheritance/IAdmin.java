package overlapping_inheritance;

public interface IAdmin {
    void banUser(int userId);

    void promoteUser(int userId, User.UserRole userRole);
}
