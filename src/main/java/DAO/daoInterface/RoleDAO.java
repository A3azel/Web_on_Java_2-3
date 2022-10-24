package DAO.daoInterface;

public interface RoleDAO {
    String findUserRoleByID(Long id);
    String findIDByUserRole(String role);
}
