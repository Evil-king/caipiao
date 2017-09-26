package com.caipiao.servlet;

import javax.servlet.http.HttpServlet;

public class TaskServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskService taskService;
	
    public TaskServlet() {
        super();
        taskService = new TaskService();
        taskService.startAllTask();
    }

}
