package overlapping_inheritance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static Utils.Utils.checkIfNull;
import static Utils.Utils.checkIfNullRoles;

public class User implements IBasicUser, IModerator, IAdmin {

    public enum UserRole {
        BASIC,
        MODERATOR,
        ADMIN
    }

    private static int idCounter = 0;
    private static List<User> extent = new ArrayList<>();

    private int id;
    private String username;
    private LocalDate joinDate;
    private List<LocalDate> reports = new ArrayList<>();
    private List<LocalDate> warnings = new ArrayList<>();
    private EnumSet<UserRole> roles = EnumSet.noneOf(UserRole.class);
    private boolean isBanned = false;



    public User(String username, LocalDate joinDate, EnumSet<UserRole> roles) {
        setUsername(username);
        setJoinDate(joinDate);
        checkIfNullRoles(roles);
        for (UserRole role : roles) {
            this.roles.add(role);
        }
        this.id = idCounter;

        idCounter++;
        extent.add(this);
    }

    @Override
    public void banUser(int userId) {
        if (roles.contains(UserRole.ADMIN)) {
            User userToBan = findUser(userId);
            userToBan.setBanned(true);
        } else {
            throw new IllegalArgumentException("Access denied - not an admin account!");
        }

    }

    @Override
    public void promoteUser(int userId, UserRole userRole) {
        checkIfNullRoles(roles);
        if (roles.contains(UserRole.ADMIN)) {
            User userToPromote = findUser(userId);
            userToPromote.addRole(userRole);
        }
        if (roles.contains(UserRole.MODERATOR)) {
            if (userRole.equals(UserRole.ADMIN)) {
                throw new IllegalArgumentException("Moderator cannot promote to admin account!");
            }
            User userToPromote = findUser(userId);
            if (userToPromote.roles.contains(UserRole.ADMIN)) {
                throw new IllegalArgumentException("Cannot degrade an admin account as a moderator!");
            }
            userToPromote.addRole(userRole);
        }
    }

    @Override
    public void reportUser(int userId) {
        if (!roles.contains(UserRole.ADMIN)) {
            User userToReport = findUser(userId);
            userToReport.addReport();
        } else {
            throw new IllegalArgumentException("Admin cant report to himself!");
        }

    }

    @Override
    public void giveWarningToUser(int userId) {
        if (roles.contains(UserRole.MODERATOR)) {
            User userToWarn = findUser(userId);
            userToWarn.addWarning();
        } else {
            throw new IllegalArgumentException("Only moderator can give warnings!");
        }

    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        checkIfNull(username);
        this.username = username;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        checkIfNull(joinDate);
        this.joinDate = joinDate;
    }

    public EnumSet<UserRole> getRoles() {
        return roles;
    }

    public void addRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null");
        }
        if (this.roles.contains(role)) {
            throw new IllegalArgumentException("role already acquired!");
        }
        roles.add(role);

    }

    public boolean isBanned() {
        return isBanned;
    }

    private void setBanned(boolean banned) {
        isBanned = banned;
    }


    public List<LocalDate> getReports() {
        return Collections.unmodifiableList(reports);
    }

    private void addReport() {
        reports.add(LocalDate.now());
    }

    public List<LocalDate> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    private void addWarning() {
        warnings.add(LocalDate.now());
    }

    public static List<User> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    private User findUser(int userId) {
        return getExtent().stream().filter(user -> user.id == userId).findFirst().get();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", joinDate=" + joinDate +
                ", reports=" + reports +
                ", warnings=" + warnings +
                ", roles=" + roles +
                ", isBanned=" + isBanned +
                '}';
    }
}
