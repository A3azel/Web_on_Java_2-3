package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.RoleDAOImpl;
import service.serviceInterfaces.RoleService;

public class RoleServiceI implements RoleService {

    private static RoleServiceI instance;
    private final RoleDAOImpl roleDAO;

    public RoleServiceI() {
        roleDAO = DAOFactory.getInstance().getRoleDAO();
    }

    public static synchronized RoleServiceI getInstance(){
        if(instance==null){
            return new RoleServiceI();
        }
        return instance;
    }

    @Override
    public String findUserRoleByID(Long id) {
        return roleDAO.findUserRoleByID(id);
    }
}
