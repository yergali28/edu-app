package kz.iitu.controllers;

import kz.iitu.beans.AdminBean;
import kz.iitu.beans.CourseBean;
import kz.iitu.beans.SuperAdminBean;
import kz.iitu.beans.UserBean;
import kz.iitu.dao.Cabinets;
import kz.iitu.dao.Roles;
import kz.iitu.dao.SuperAdmins;
import kz.iitu.dao.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@Scope("session")
public class SuperAdminController {

    private SuperAdmins currentSuperAdmin;

    @Autowired
    UserBean userBean;

    @Autowired
    SuperAdminBean superAdminBean;

    @Autowired
    CourseBean courseBean;

    @Autowired
    AdminBean adminBean;

    @RequestMapping(value = "/superadmin/", method = RequestMethod.GET)
    public ModelAndView auth() {
        ModelAndView view = new ModelAndView("superadminlogin");

        return view;
    }

    @RequestMapping(value = "superadmin/editsuperadmin", method = RequestMethod.GET)
    public ModelAndView editSuperAdmin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "saIdForEdit") Long saIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("superadmineditsuperadmin");

        SuperAdmins a = superAdminBean.getSuperAdminById(saIdForEdit);
        view.addObject("singleSAForEdit", a);

        return view;
    }

    @RequestMapping(value = "superadmin/editadmin", method = RequestMethod.GET)
    public ModelAndView editAdmin(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value = "aIdForEdit") Long saIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("superadmineditadmin");

        Users a = userBean.getUserById(saIdForEdit);
        view.addObject("adminForEdit", a);

        return view;
    }

    @RequestMapping(value = "superadmin/editcabinet", method = RequestMethod.GET)
    public ModelAndView editCabinet(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value = "cIdForEdit") Long cIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("superadmineditcompany");

        Cabinets c = courseBean.getCabinetById(cIdForEdit);
        view.addObject("singleCForEdit", c);

        return view;
    }

    @RequestMapping(value = "superadmin/editownprofile", method = RequestMethod.GET)
    public ModelAndView editsOwnProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView view = new ModelAndView("superadmineditsuperadmin");

        return view;
    }

    @RequestMapping(value = "superadmin/login", method = RequestMethod.POST)
    public void loginUser(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password
    ) throws Exception {

        SuperAdmins admin = superAdminBean.getSuperAdmin(login, password);

        ModelAndView redirect = null;
        if(admin!=null) {
            redirect = indexSuperAdmin(request, response);
            request.getSession().setAttribute("superadmin", admin);
        } else {
            redirect = auth();
        }
        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/insertSuperAdmin", method = RequestMethod.POST)
    public void insertSuperAdmin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password
    ) throws Exception {

        SuperAdmins admin = new SuperAdmins();
        admin.setActive(1);
        admin.setLogin(login);
        admin.setPassword(password);
        superAdminBean.addSuperAdmin(admin);

        ModelAndView redirect = indexSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/insertAdmin", method = RequestMethod.POST)
    public void insertAdmin(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "login") String login,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "surname") String surname,
                                 @RequestParam(value = "company") Long company_id
    ) throws Exception {

        Users admin = new Users();
        admin.setActive(1);
        admin.setLogin(login);
        admin.setPassword(password);
        admin.setName(name);
        admin.setSurname(surname);
        admin.setCabinet(courseBean.getCabinetById(company_id));
        admin.setRole_id(userBean.getRoleById(1));
        superAdminBean.addAdmin(admin);

        ModelAndView redirect = adminsSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/insertCabinet", method = RequestMethod.POST)
    public void insertCabinet(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "description") String description
    ) throws Exception {

        Cabinets cabinet = new Cabinets();
        cabinet.setActive(1);
        cabinet.setName(name);
        cabinet.setDescription(description);
        courseBean.addCabinet(cabinet);

        ModelAndView redirect = companiesSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/saveSuperAdmin", method = RequestMethod.POST)
    public void saveSuperAdmin(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "login") String login,
                                 @RequestParam(value = "id") Long id,
                                 @RequestParam(value = "password") String password
    ) throws Exception {

        SuperAdmins admin = superAdminBean.getSuperAdminById(id);
        admin.setLogin(login);
        if(!password.trim().equals("")) {
            admin.setPassword(password);
        }
        superAdminBean.updateSuperAdmin(admin);

        ModelAndView redirect = indexSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/saveAdmin", method = RequestMethod.POST)
    public void saveAdmin(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "surname") String surname,
                            @RequestParam(value = "id") Long id) throws Exception {

        Users admin = userBean.getUserById(id);
        admin.setLogin(login);
        if(!password.trim().equals("")) {
            admin.setPassword(password);
        }
        admin.setName(name);
        admin.setSurname(surname);
        userBean.updateUser(admin);

        ModelAndView redirect = adminsSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/saveCabinet", method = RequestMethod.POST)
    public void saveCabinet(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "id") Long id,
                               @RequestParam(value = "description") String description
    ) throws Exception {

        Cabinets cabinet = courseBean.getCabinetById(id);
        cabinet.setName(name);
        cabinet.setDescription(description);
        courseBean.updateCabinets(cabinet);

        ModelAndView redirect = companiesSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "superadmin/superadminindex", method = RequestMethod.GET)
    public ModelAndView indexSuperAdmin(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView view = new ModelAndView("superadminindex");
        List<SuperAdmins> superAdmins = superAdminBean.getSuperAdminsList();
        view.addObject("superAdminsList", superAdmins);
        return view;
    }

    @RequestMapping(value = "superadmin/superadmincompanies", method = RequestMethod.GET)
    public ModelAndView companiesSuperAdmin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("superadmincompanies");
        List<Cabinets> cabinetsList = courseBean.getCabinetsList();
        view.addObject("cabinetsList", cabinetsList);
        return view;
    }

    @RequestMapping(value = "superadmin/superadminadmins", method = RequestMethod.GET)
    public ModelAndView adminsSuperAdmin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("superadminadmins");

        List<Users> adminsList = adminBean.getAdminsList();
        view.addObject("adminsList", adminsList);
        return view;
    }

    @RequestMapping(value = "superadmin/superadminaddsuperadmin", method = RequestMethod.GET)
    public ModelAndView addSuperAdmin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("superadminaddsuperadmin");
        return view;
    }

    @RequestMapping(value = "superadmin/superadminaddcabinet", method = RequestMethod.GET)
    public ModelAndView addCabinet(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("superadminaddcompany");
        return view;
    }

    @RequestMapping(value = "superadmin/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        request.getSession().setAttribute("superadmin", null);

        response.sendRedirect("/superadmin/");
    }

    @RequestMapping(value = "/superadmin/addadmin", method = RequestMethod.GET)
    public ModelAndView addadmin() {

        ModelAndView view = new ModelAndView("superadminaddadmin");
        List<Cabinets> cabinetList = courseBean.getCabinetsList();
        view.addObject("cabinetsList", cabinetList);
        return view;
    }



    //Delete methods----------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/superadmin/deleteSuperAdmin", method = RequestMethod.POST)
    public void deleteSuperAdmin(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "saIdForDelete") Long id
    ) throws Exception{
        SuperAdmins super_admin = superAdminBean.getSuperAdminById(id);
        super_admin.setActive(0);
        superAdminBean.updateSuperAdmin(super_admin);
        response.sendRedirect("superadminindex");
    }

    @RequestMapping(value = "/superadmin/deleteAdmin", method = RequestMethod.POST)
    public void deleteAdmin(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "aIdForDelete") Long id
    ) throws Exception{
        Users admin = userBean.getUserById(id);
        admin.setActive(0);
        userBean.updateUser(admin);

        ModelAndView redirect = adminsSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/superadmin/deleteCabinet", method = RequestMethod.POST)
    public void deleteCabinet(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "cIdForDelete") Long id
    ) throws Exception{
        Cabinets cabinet = courseBean.getCabinetById(id);
        cabinet.setActive(0);
        courseBean.updateCabinets(cabinet);
        ModelAndView redirect = companiesSuperAdmin(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    //Edit methods----------------------------------------------------------------------------------------------------

}
