package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sorter.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
@WebServlet(urlPatterns= {"/admin-new"})
public class NewController extends HttpServlet {

	
	@Inject INewService newService;
	
	@Inject ICategoryService categoryService;
	
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*NewModel model = new NewModel();*/
		/*String page = request.getParameter("page");
		String maxPageItem = request.getParameter("maxPageItem");
		if(page != null) {
			model.setPage(Integer.parseInt(page));
		}else {
			model.setPage(1);
		}
		if(maxPageItem != null) {
			model.setMaxPageItem(Integer.parseInt(maxPageItem));
		}*/
		/*ban dau
		NewModel model = FormUtil.toModel(NewModel.class, request);
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(request, response);*/
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstant.EDIT)){
			view = "/views/admin/new/edit.jsp";
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}else if(model.getId() == null) {
				
			}
			request.setAttribute("categories", categoryService.findAll());
		}else if(model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/new/list.jsp";
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
		}
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
