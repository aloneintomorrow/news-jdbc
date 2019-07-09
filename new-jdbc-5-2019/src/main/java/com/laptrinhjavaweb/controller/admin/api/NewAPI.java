package com.laptrinhjavaweb.controller.admin.api;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*StringBuilder sb = new StringBuilder();
    	ObjectMapper mapper = new ObjectMapper();        
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine())!= null) {
			sb.append(line);
		}
        NewModel newModel = mapper.readValue(sb.toString(), NewModel.class); 
        newService.save(newModel);
        mapper.writeValue(response.getOutputStream(), newModel);
    }*/
    	ObjectMapper mapper = new ObjectMapper();
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
    	newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
    	newModel = newService.save(newModel);
  
    	mapper.writeValue(response.getOutputStream(), newModel);
    } 
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
    	newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
    	
    	newModel = newService.update(newModel);
    	
    	mapper.writeValue(response.getOutputStream(), newModel);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
    	newService.delete(newModel.getIds());
    	mapper.writeValue(response.getOutputStream(), "{ }");
    }
}
