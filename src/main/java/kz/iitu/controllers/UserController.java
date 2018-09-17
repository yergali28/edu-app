package kz.iitu.controllers;

import kz.iitu.beans.AdminBean;
import kz.iitu.beans.CourseBean;
import kz.iitu.beans.NewsBean;
import kz.iitu.beans.UserBean;
import kz.iitu.dao.*;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Scope("session")
public class UserController {

    private Users currentUser;

    @Autowired
    UserBean userBean;

    @Autowired
    CourseBean courseBean;

    @Autowired
    NewsBean newsBean;

    @Autowired
    AdminBean adminBean;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView news(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView("news");

        List<News> newsList = newsBean.getNewsListWithoutCabinetId();
        view.addObject("newsList", newsList);

        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response
    ) throws Exception{
        request.getSession().setAttribute("currentUser", null);

        response.sendRedirect("/");
    }

    // links
    @RequestMapping("/download-attachment/{attachmentId}")
    public void downloadAttachment(@PathVariable("attachmentId") Long attachmentId,
                                   HttpServletResponse response) {

        LessonAttachements attachements = courseBean.getLAById(attachmentId);
        try {
            response.setHeader("Content-Description", "inline;filename\"" + attachements.getName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(attachements.getMime());
            IOUtils.copy(attachements.getAttachment().getBinaryStream(), out);

            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/adminadduser", method = RequestMethod.GET)
    public ModelAndView addadmin() {

        ModelAndView view = new ModelAndView("adminadduser");
        return view;
    }

    @RequestMapping(value = "/adminaddcourse", method = RequestMethod.GET)
    public ModelAndView addCourse() {

        ModelAndView view = new ModelAndView("adminaddcourse");
        return view;
    }

    @RequestMapping(value = "/adminaddlesson", method = RequestMethod.GET)
    public ModelAndView addLesson() {

        ModelAndView view = new ModelAndView("adminaddlesson");
        List<Courses> coursesList = courseBean.getCoursesList(currentUser.getCabinet().getId());
        view.addObject("coursesList", coursesList);

        return view;
    }

    @RequestMapping(value = "/adminaddgroup", method = RequestMethod.GET)
    public ModelAndView addGroup() {

        ModelAndView view = new ModelAndView("adminaddgroup");

        return view;
    }

    @RequestMapping(value = "/adminaddnews", method = RequestMethod.GET)
    public ModelAndView addNews() {

        ModelAndView view = new ModelAndView("adminaddnews");

        return view;
    }

    @RequestMapping(value = "/admincourses", method = RequestMethod.GET)
    public ModelAndView adminCourses(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView view = new ModelAndView("admincourses");
        currentUser = (Users) request.getSession().getAttribute("currentUser");

        List<Courses> coursesList = courseBean.getCoursesList(currentUser.getCabinet().getId());
        view.addObject("coursesList", coursesList);

        return view;
    }

    @RequestMapping(value = "/adminlessons", method = RequestMethod.GET)
    public ModelAndView adminLessons(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView view = new ModelAndView("adminlessons");
        currentUser = (Users) request.getSession().getAttribute("currentUser");

        List<Lessons> lessonsList = courseBean.getLessonsList(currentUser.getCabinet().getId());
        view.addObject("lessonsList", lessonsList);

        return view;
    }

    @RequestMapping(value = "/admingroups", method = RequestMethod.GET)
    public ModelAndView adminGroups(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView view = new ModelAndView("admingroups");
        currentUser = (Users) request.getSession().getAttribute("currentUser");

        List<Groups> groupsList = courseBean.getgroupsList(currentUser.getCabinet().getId());
        view.addObject("groupsList", groupsList);

        return view;
    }

    @RequestMapping(value = "/adminnews", method = RequestMethod.GET)
    public ModelAndView adminNews(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView view = new ModelAndView("adminnews");
        currentUser = (Users) request.getSession().getAttribute("currentUser");
        List<News> newsList = newsBean.getNewsList(currentUser.getCabinet().getId());
        view.addObject("newsList", newsList);

        return view;
    }

    @RequestMapping(value = "/courseLessons", method = RequestMethod.GET)
    public ModelAndView courseLessons(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "courseId") Long course_id
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("courselessons");
        List<Lessons> lessonsList = courseBean.getCourseLessonsList(currentUser.getCabinet().getId(), course_id);

        view.addObject("lessonsList", lessonsList);

        return view;
    }

    @RequestMapping(value = "/groupsusers", method = RequestMethod.GET)
    public ModelAndView groupsUsers(HttpServletRequest request, HttpServletResponse response
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("groupsusers");
        List<Groups> groupsList = courseBean.getgroupsList(currentUser.getCabinet().getId());
        List<Users> usersList = adminBean.getSomeUsersList(currentUser.getId(), Long.valueOf(3), currentUser.getCabinet().getId());
        List<Users> teachersList = adminBean.getSomeUsersList(currentUser.getId(), Long.valueOf(2), currentUser.getCabinet().getId());
        List<Courses> coursesList = courseBean.getCoursesList(currentUser.getCabinet().getId());

        view.addObject("groupsList", groupsList);
        view.addObject("usersList", usersList);
        view.addObject("teachersList", teachersList);
        view.addObject("coursesList", coursesList);

        return view;
    }

    @RequestMapping(value = "/usercourses", method = RequestMethod.GET)
    public ModelAndView userCourses(HttpServletRequest request, HttpServletResponse response
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("courses");

        List<Groups> groupsList = courseBean.getGroupListOfUserById(currentUser.getId());
        List<GroupCourses> groupCoursesList = new ArrayList<>();

        for(int i=0;i<groupsList.size();i++) {
            List<GroupCourses> list2 = courseBean.getGroupSomeCoursesList(groupsList.get(i).getId());
            groupCoursesList = Stream.concat(groupCoursesList.stream(), list2.stream()).collect(Collectors.toList());
        }

        HashMap<String, Average> map = new HashMap<>();
        for(GroupCourses groupCourses: groupCoursesList) {
            Average average = new Average();
            average.setMark(courseBean.getMarkAvgByUserIdAndCourseId(currentUser.getId(),groupCourses.getCourse_id().getId()));
            average.setAttendance(courseBean.getAttendanceAvgByUserIdAndCourseId(currentUser.getId(),groupCourses.getCourse_id().getId()));
            map.put("" + groupCourses.getCourse_id().getId(), average);
        }
        view.addObject("groupCoursesList", groupCoursesList);
        view.addObject("map", map);

        return view;
    }

    @RequestMapping(value = "/userlessons", method = RequestMethod.GET)
    public ModelAndView userLessons(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "course_id") Long course_id
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("lessons");

        List<Lessons> lessonsList = courseBean.getLessonsListByCourseId(course_id);
        List<Attachment> attachmentList = new ArrayList<>();

        for(int i=0;i<lessonsList.size();i++) {
            Attachment a = new Attachment();
            a.setLesson(lessonsList.get(i));
            a.setAttachements(courseBean.getLessonAttList(lessonsList.get(i).getId()));
            attachmentList.add(a);
        }

        view.addObject("attachment", attachmentList);

        return view;
    }

    @RequestMapping(value = "/singlelesson", method = RequestMethod.GET)
    public ModelAndView singleLesson(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "lesson_id") Long lesson_id,
                                        @RequestParam(value = "course_id") Long course_id
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("singlelesson");

        Lessons singleLesson = courseBean.getLessonById(lesson_id);
        view.addObject("singleLesson", singleLesson);
        List<GroupCourses> groupCoursesList = courseBean.getGroupCoursesListByCourseId(course_id);
        view.addObject("groupCoursesList", groupCoursesList);
        List<GroupUsers> usersList = courseBean.getGroupUsersListByGroupId();
        view.addObject("usersList", usersList);

        return view;
    }

    @RequestMapping(value = "/singlestudentlesson", method = RequestMethod.GET)
    public ModelAndView singleStudentLesson(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "lesson_id") Long lesson_id
    ) throws Exception{

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("singlestudentlesson");

        Lessons singleLesson = courseBean.getLessonById(lesson_id);
        view.addObject("singleLesson", singleLesson);
        Marks singleMark = courseBean.getMarkByLessonId(lesson_id);
        view.addObject("singleMark", singleMark);
        Attendances singleAttendance = courseBean.getAttendanceByLessonId(lesson_id);
        view.addObject("singleAttendance", singleAttendance);

        return view;
    }

    @RequestMapping(value = "/usergroups", method = RequestMethod.GET)
    public ModelAndView userGroups(HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        ModelAndView view = new ModelAndView("groups");

        List<GroupCourses> groupCoursesList = courseBean.getGroupCoursesListOfUserById(currentUser.getId());
        List<GroupUsers> usersList = courseBean.getGroupUsersListByGroupId();
        view.addObject("groupCoursesList", groupCoursesList);
        view.addObject("usersList", usersList);
        return view;
    }

    //Login-------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "getuser", method = RequestMethod.POST)
    public void getuser(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(value = "login") String login,
                        @RequestParam(value = "password") String password
    )  throws Exception{
        Users users = userBean.getUser(login, password);
        if(users!=null && users.getRole_id().getName().equals("admin")){
            response.sendRedirect("/adminindex");
            request.getSession().setAttribute("currentUser", users);
        }else if(users!=null && users.getRole_id().getName().equals("teacher")){
            response.sendRedirect("/usercourses");
            request.getSession().setAttribute("currentUser", users);
        } else if(users!=null && users.getRole_id().getName().equals("student")){
            response.sendRedirect("/usercourses");
            request.getSession().setAttribute("currentUser", users);
        } else {
            response.sendRedirect("/login");
        }

    }

    // index

    @RequestMapping(value = "/adminindex", method = RequestMethod.GET)
    public ModelAndView indexAdmin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("adminindex");
        currentUser = (Users) request.getSession().getAttribute("currentUser");

        List<Users> studentsList = adminBean.getSomeUsersList(currentUser.getId(), Long.valueOf(3), currentUser.getCabinet().getId());
        List<Users> teachersList = adminBean.getSomeUsersList(currentUser.getId(), Long.valueOf(2), currentUser.getCabinet().getId());
        List<Users> adminsList = adminBean.getSomeUsersList(currentUser.getId(), Long.valueOf(1), currentUser.getCabinet().getId());

        view.addObject("adminsList", adminsList);
        view.addObject("studentsList", studentsList);
        view.addObject("teachersList", teachersList);
        return view;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("index");

        return view;
    }

    // insert

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void insertAdmin(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "surname") String surname,
                            @RequestParam(value = "role") int role
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Users user = new Users();
        user.setActive(1);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setCabinet(currentUser.getCabinet());
        user.setRole_id(userBean.getRoleById(role));
        adminBean.addUser(user);

        ModelAndView redirect = indexAdmin(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertCourse", method = RequestMethod.POST)
    public void insertCourse(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "description") String description
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Courses course = new Courses();
        course.setActive(1);
        course.setName(name);
        course.setDescription(description);
        course.setCabinet(currentUser.getCabinet());

        courseBean.addCourse(course);

        ModelAndView redirect = adminCourses(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertLesson", method = RequestMethod.POST)
    public void insertLesson(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "course") Long course_id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "content") String content
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Lessons lesson = new Lessons();
        lesson.setActive(1);
        lesson.setCourse(courseBean.getCourseById(course_id));
        lesson.setTitle(title);
        lesson.setContent(content);

        courseBean.addLessons(lesson);

        ModelAndView redirect = adminLessons(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertGroup", method = RequestMethod.POST)
    public void insertGroup(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "name") String name
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Groups group = new Groups();
        group.setActive(1);
        group.setCabinet_id(currentUser.getCabinet());
        group.setName(name);

        courseBean.addGroup(group);

        ModelAndView redirect = adminGroups(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertGroupUsers", method = RequestMethod.POST)
    public void insertGroupUsers(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "group") Long group,
                            @RequestParam(value = "user") Long user
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        GroupUsers groupUsers = new GroupUsers();
        groupUsers.setActive(1);
        groupUsers.setGroup_id(courseBean.getGroupById(group));
        groupUsers.setUser_id(userBean.getUserById(user));

        userBean.addGroupUsers(groupUsers);

        ModelAndView redirect = groupsUsers(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertGroupCourses", method = RequestMethod.POST)
    public void insertGroupCourses(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "group") Long group,
                                 @RequestParam(value = "course") Long course
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        GroupCourses groupCourses = new GroupCourses();
        groupCourses.setActive(1);
        groupCourses.setGroup_id(courseBean.getGroupById(group));
        groupCourses.setCourse_id(courseBean.getCourseById(course));

        userBean.addGroupCourses(groupCourses);

        ModelAndView redirect = groupsUsers(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertNews", method = RequestMethod.POST)
    public void insertNews(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        News news = new News();
        news.setActive(1);
        news.setCabinet_id(currentUser.getCabinet());
        news.setUser_id(currentUser);
        news.setTitle(title);
        news.setContent(content);

        newsBean.addNews(news);

        ModelAndView redirect = adminNews(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/insertAttendance", method = RequestMethod.POST)
    public void insertAttendance(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "attendanceState") int att,
                           @RequestParam(value = "attendanceNote") String attNote,
                           @RequestParam(value = "user_id") Long user_id,
                           @RequestParam(value = "lesson_id") Long lesson_id
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Attendances attendance = new Attendances();
        attendance.setLesson_id(courseBean.getLessonById(lesson_id));
        attendance.setUser_id(userBean.getUserById(user_id));
        attendance.setAttendance_value(att);
        attendance.setAttendance_note(attNote);

        courseBean.addAttendance(attendance);


        response.sendRedirect("/usergroups");

    }

    @RequestMapping(value = "/insertMark", method = RequestMethod.POST)
    public void insertMark(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "grade") int grade,
                                 @RequestParam(value = "markNote") String markNote,
                                 @RequestParam(value = "user_id") Long user_id,
                                 @RequestParam(value = "lesson_id") Long lesson_id
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Marks mark = new Marks();
        mark.setLesson_id(courseBean.getLessonById(lesson_id));
        mark.setUser_id(userBean.getUserById(user_id));
        mark.setMark_notes(markNote);
        mark.setMark_value(grade);

        courseBean.addMarks(mark);


        response.sendRedirect("/usergroups");

    }

    @RequestMapping(value = "/attachfile", method = RequestMethod.POST)
    public void attachFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "file") MultipartFile attachment,
                             @RequestParam(value = "lesson_id") Long lesson_id
    ) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        if (attachment != null) {

                LessonAttachements uploadFile = new LessonAttachements();
                uploadFile.setLesson_id(courseBean.getLessonById(lesson_id));
                uploadFile.setUser_id(currentUser);
                uploadFile.setActive(1);


                try {
                    Blob blob = Hibernate.getLobCreator(courseBean.getSessionFactory().openSession())
                            .createBlob(IOUtils.toByteArray(attachment.getInputStream()));
                    uploadFile.setAttachment(blob);
                    uploadFile.setMime(attachment.getContentType());
                    uploadFile.setName(attachment.getOriginalFilename());

                }catch (Exception e) {
                    e.printStackTrace();
                }

                courseBean.addLesson_attachments(uploadFile);

        }

//        ModelAndView redirect = userGroups(request, response);

        response.sendRedirect("/usergroups");

    }

    // edit

    @RequestMapping(value = "/editownprofile", method = RequestMethod.GET)
    public ModelAndView editsOwnProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView view = new ModelAndView("adminedituser");

        return view;
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public ModelAndView editAdmin(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "uIdForEdit") Long saIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("adminedituser");

        Users a = userBean.getUserById(saIdForEdit);
        view.addObject("userForEdit", a);

        return view;
    }

    @RequestMapping(value = "/editcourse", method = RequestMethod.GET)
    public ModelAndView editCabinet(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "courseIdForEdit") Long cIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("admineditcourse");

        Courses c = courseBean.getCourseById(cIdForEdit);
        view.addObject("singleCourseForEdit", c);

        return view;
    }

    @RequestMapping(value = "/editlesson", method = RequestMethod.GET)
    public ModelAndView editLesson(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "lessonIdForEdit") Long cIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("admineditlesson");

        Lessons lesson = courseBean.getLessonById(cIdForEdit);
        view.addObject("singleLessonForEdit", lesson);

        return view;
    }

    @RequestMapping(value = "/editNews", method = RequestMethod.GET)
    public ModelAndView editNews(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "newsIdForEdit") Long cIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("admineditnews");

        News news = newsBean.getNewsById(cIdForEdit);
        view.addObject("singleNewsForEdit", news);

        return view;
    }


    @RequestMapping(value = "/editgroup", method = RequestMethod.GET)
    public ModelAndView editGroup(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "groupIdForEdit") Long cIdForEdit
    ) throws Exception {

        ModelAndView view = new ModelAndView("admineditgroup");

        Groups group = courseBean.getGroupById(cIdForEdit);
        view.addObject("singleGroupForEdit", group);

        return view;
    }

    @RequestMapping(value = "/groupstudents", method = RequestMethod.GET)
    public ModelAndView groupStudents(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "groupId") Long groupId
    ) throws Exception {

        ModelAndView view = new ModelAndView("groupstudents");

        List<GroupUsers> groupUsersList = userBean.getGroupSomeUsersList(currentUser.getId(), currentUser.getCabinet().getId(), groupId, Long.valueOf(3));

        view.addObject("groupUsersList", groupUsersList);

        return view;
    }

    @RequestMapping(value = "/groupsteachers", method = RequestMethod.GET)
    public ModelAndView groupTeachers(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "groupId") Long groupId
    ) throws Exception {

        ModelAndView view = new ModelAndView("groupstudents");

        List<GroupUsers> groupUsersList = userBean.getGroupSomeUsersList(currentUser.getId(), currentUser.getCabinet().getId(), groupId, Long.valueOf(2));

        view.addObject("groupUsersList", groupUsersList);

        return view;
    }

    @RequestMapping(value = "/groupscourses", method = RequestMethod.GET)
    public ModelAndView groupCourses(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "groupId") Long groupId
    ) throws Exception {

        ModelAndView view = new ModelAndView("groupscourses");

        List<GroupCourses> groupCoursesList = courseBean.getGroupSomeCoursesList(groupId);

        view.addObject("groupCoursesList", groupCoursesList);

        return view;
    }
    // save

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "surname") String surname,
                          @RequestParam(value = "id") Long id) throws Exception {

        currentUser = (Users) request.getSession().getAttribute("currentUser");

        Users user = userBean.getUserById(id);
        user.setLogin(login);
        if(!password.trim().equals("")) {
            user.setPassword(password);
        }
        user.setName(name);
        user.setSurname(surname);
        userBean.updateUser(user);
        if(user.getId()==currentUser.getId()) {
            request.getSession().setAttribute("currentUser", user);
        }

        ModelAndView redirect;
        if(currentUser.getRole_id().getName().equals("admin")) {
            redirect = indexAdmin(request, response);
        }else {
            redirect = index(request, response);
        }

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
    public void saveCourse(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "name") String name,
                            @RequestParam(value = "id") Long id,
                            @RequestParam(value = "description") String description
    ) throws Exception {

        Courses course = courseBean.getCourseById(id);
        course.setName(name);
        course.setDescription(description);
        courseBean.updateCourse(course);

        ModelAndView redirect = adminCourses(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/saveLesson", method = RequestMethod.POST)
    public void saveLesson(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "id") Long id,
                           @RequestParam(value = "content") String content
    ) throws Exception {

        Lessons lesson = courseBean.getLessonById(id);
        lesson.setTitle(title);
        lesson.setContent(content);
        courseBean.updateLessons(lesson);

        ModelAndView redirect = adminLessons(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
    public void savegroup(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "id") Long id
    ) throws Exception {

        Groups group = courseBean.getGroupById(id);
        group.setName(name);
        courseBean.updateGroup(group);

        ModelAndView redirect = adminGroups(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public void saveNews(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "id") Long id
    ) throws Exception {

        News news = newsBean.getNewsById(id);
        news.setTitle(title);
        news.setContent(content);
        newsBean.updateNews(news);

        ModelAndView redirect = adminNews(request, response);

        response.sendRedirect(redirect.getViewName());

    }

    // delete

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "uIdForDelete") Long id
    ) throws Exception{
        Users user = userBean.getUserById(id);
        user.setActive(0);
        userBean.updateUser(user);

        ModelAndView redirect = indexAdmin(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public void deleteCabinet(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "courseIdForDelete") Long id
    ) throws Exception{
        Courses course = courseBean.getCourseById(id);
        course.setActive(0);
        courseBean.updateCourse(course);

        ModelAndView redirect = adminCourses(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteLesson", method = RequestMethod.POST)
    public void deleteLesson(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "lessonIdForDelete") Long id
    ) throws Exception{
        Lessons lesson = courseBean.getLessonById(id);
        lesson.setActive(0);
        courseBean.updateLessons(lesson);

        ModelAndView redirect = adminLessons(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "groupIdForDelete") Long id
    ) throws Exception{
        Groups group = courseBean.getGroupById(id);
        group.setActive(0);
        courseBean.updateGroup(group);

        ModelAndView redirect = adminGroups(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteGroupUser", method = RequestMethod.POST)
    public void deleteGroupUser(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "guIdForDelete") Long id
    ) throws Exception{
        GroupUsers groupUsers = userBean.getGroupUsersById(id);
        groupUsers.setActive(0);
        userBean.updateGroupUsers(groupUsers);

        ModelAndView redirect = adminGroups(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public void deleteNews(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "newsIdForDelete") Long id
    ) throws Exception{
        News news = newsBean.getNewsById(id);
        news.setActive(0);
        newsBean.updateNews(news);

        ModelAndView redirect = adminNews(request, response);

        response.sendRedirect(redirect.getViewName());
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public void deleteFile(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "att_id") Long id
    ) throws Exception{
        LessonAttachements att = courseBean.getLAById(id);
        att.setActive(0);
        courseBean.updateLesson_attachment(att);

        response.sendRedirect("/usergroups");
    }
}
