package demo.controller;

import demo.Return.ProjectBean;
import demo.Return.ResultCode;
import demo.model.*;
import demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@RequestMapping ("/project")
public class ProjectController {
    @Autowired
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    CompanyJpaRepository companyJpaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskJpaRepository taskJpaRepository;

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    ProjectorEmployeeRepository projectorEmployeeRepository;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ProjectBean Add(@RequestParam("projectid")String projectid,
                    @RequestParam("name")String name,
                    HttpSession session) {
        if(Check.Check(projectid)) {
            User user = (User) session.getAttribute("abc");
            if (user != null) {
                if (projectJpaRepository.findByProjectid(projectid) != null) {
                    return new ProjectBean(ResultCode.RESULT_CODE_ADD_FAIL);
                } else {
                    Project project = new Project(projectid, name);
                    projectJpaRepository.save(project);
                    return new ProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
                }
            } else {
                return new ProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
            }
        }else {
                return new ProjectBean(ResultCode.RESULT_CODE_ERROR);
            }
    }

    @RequestMapping(value = "/editname",method = RequestMethod.POST)
    public ProjectBean Edit(@RequestParam("name")String name,
                          @RequestParam("projectid")String projectid,
                          HttpSession session) {
        Project project = projectJpaRepository.findByProjectid(projectid);
        User user = (User) session.getAttribute("abc");
        if(user!=null){
            if (project != null) {
                project.setName(name);
                projectJpaRepository.save(project);
                return new ProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new ProjectBean(ResultCode.RESULT_CODE_PROJECT_DOES_NOT_EXISTS);
            }
        }else{
            return new ProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }


    @RequestMapping(value = "/editprojectid",method = RequestMethod.POST)
    public ProjectBean EditProjectId(@RequestParam("projectid")String projectid,
                                   @RequestParam("newprojectid")String newprojectid,
                                   HttpSession session) {
        if (Check.Check(projectid)) {
            Project project = projectJpaRepository.findByProjectid(projectid);
            User user = (User) session.getAttribute("abc");
            if (user != null) {
                if (project != null) {
                    List<Task> taskList = taskJpaRepository.findByParentid(projectid);  //tìm task có chung mã dự án
                    int count = taskList.size();
                    for (int i = 0; i <= count; i++) {
                        Task task = taskJpaRepository.findByProjectid(taskList.get(i).getProjectid());
                        task.setProjectid(newprojectid);  // set mã dự án mới
                        taskJpaRepository.save(task);
                    }
                    project.setProjectid(newprojectid);
                    projectJpaRepository.save(project);
                    return new ProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
                } else {
                    return new ProjectBean(ResultCode.RESULT_CODE_PROJECT_DOES_NOT_EXISTS);
                }
            } else {
                return new ProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
            }
        } else {
            return new ProjectBean(ResultCode.RESULT_CODE_ERROR);
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ProjectBean Del(@RequestParam("projectid")String projectid,
                           HttpSession session) {
        User user = (User) session.getAttribute("boss");
        if(user!=null) {
            Project project = projectJpaRepository.findByProjectid(projectid);
            if (project != null) {
                taskJpaRepository.deleteByProjectid(projectid);
                projectJpaRepository.delete(project);
                return new ProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
            }else{
                return new ProjectBean(ResultCode.RESULT_CODE_PROJECT_DOES_NOT_EXISTS);
            }
        }else{
            return new ProjectBean(ResultCode.RESULT_CODE_ACCESSDENIED);
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ProjectBean List(@RequestParam("projectid")String projectid){
            Project project = projectJpaRepository.findByProjectid(projectid);
        if(project !=null) {
            project.setListNV(projectorEmployeeRepository.findByProjectid(projectid));
            project.setListTask(taskJpaRepository.findByParentid(projectid));
            List<Task> taskList = taskJpaRepository.findByParentid(projectid);
            for (Task taskE : taskList) {
                taskE.setTaskChild(taskJpaRepository.findByParentid(taskE.getParentid()));
                taskJpaRepository.save(taskE);
            }
            projectJpaRepository.save(project);
            return new ProjectBean(ResultCode.RESULT_CODE_SUCCESSFUL);
        }else{
            return new ProjectBean(ResultCode.RESULT_CODE_PROJECT_DOES_NOT_EXISTS);
        }
    }

}

