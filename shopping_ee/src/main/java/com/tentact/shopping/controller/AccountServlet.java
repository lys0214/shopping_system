package com.tentact.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.AlgorithmConstraints;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.alibaba.fastjson.JSON;
import com.tentact.shopping.dao.AccountDAO;
import com.tentact.shopping.entity.Account;
import com.tentact.shopping.entity.LogLogin;
import com.tentact.shopping.entity.Role;
import com.tentact.shopping.entity.Tu;
import com.tentact.shopping.service.AccountService;
import com.tentact.shopping.service.LogLoginService;
import com.tentact.shopping.service.RoleService;

/**
 * java注解  java标注
 * 可以通过反射获取标注的内容
 */
@WebServlet(
		description = "账户控制层", 
		urlPatterns = { "/AccountServlet" }, 
		initParams = { 
				@WebInitParam(name = "pageSize", value = "5", description = "每页条目")
		})
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService = new AccountService();
	LogLoginService logLoginService = new LogLoginService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println(config.getInitParameter("pageSize"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		String accountName = request.getParameter("accountName");
		String accountPwd = request.getParameter("accountPwd");
		// 创建对象  封装属性
		Account account = new Account();
		account.setAccountName(accountName);
		account.setAccountPwd(accountPwd);
		RoleService roleService = new RoleService();
		if ("selectAll".equals(flag)) {
		//	查询所有
			request.setAttribute("list", accountService.selectAll());

		//	查询角色信息
			request.setAttribute("roleList", roleService.selectAll());

		//	转发请求
			request.getRequestDispatcher("account_info.jsp").forward(request, response);
		}
		if ("selectByRoleId".equals(flag)) {
		//	获取要查询的id
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			List<Account> accounts = accountService.selectAllByRoleId(roleId);
			request.setAttribute("list", accounts);
			//	查询角色信息
			request.setAttribute("roleList", roleService.selectAll());
			request.getRequestDispatcher("account_info.jsp").forward(request, response);
		}
		if ("selectAll_json".equals(flag)) {
			String listJson = JSON.toJSONString(accountService.selectAll());
			response.setHeader("Context-type", "text/plain,charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(listJson);
			pw.close();
		}
		if ("getAll".equals(flag)) {
			//    查询数据
			List<Tu> roleCounts = logLoginService.selectRoleCounts();
			List<Tu> selectTimes = logLoginService.selectTimes();
			List<Tu> dayTimes = logLoginService.selectDayTimes();
			HashMap<String, Object> map = new HashMap<>();
			map.put("roleCounts", roleCounts);
			map.put("selectTimes", selectTimes);
			map.put("dayTimes", dayTimes);
			//    将map转成json
			String s = JSON.toJSONString(map);

			//    设置相应头
			response.setHeader("Context-type", "text/plain,charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(s);
			pw.close();
		}

	//	用户信息修改
		if ("update".equals(flag)) {
		//	获取用户id
			String accountId = request.getParameter("accountId");
		//	根据id查找用户
			Account account1 = accountService.checkAccountId(accountId);
			//查询全部角色
			request.setAttribute("roleList", roleService.selectAll());
			request.setAttribute("account", account1);
			request.getRequestDispatcher("account_update.jsp").forward(request, response);
		}
		if("login".equals(flag)) {
			//当点击登录，先找到对应的用户名和密码
			Cookie[] cookies = request.getCookies();
			//循环数组，找到对应的用户名和密码
			String name = "";
			String pwd = "";
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					//	通过name找到对应的value值，
					if (cookie.getName().equals("accountName")) {
						name=cookie.getValue();
					}
					if (cookie.getName().equals("accountPwd")) {
						pwd=cookie.getValue();
					}
				}
				if (!"".equals(name) && !"".equals(pwd)) {
					account.setAccountName(name);
					account.setAccountPwd(pwd);
				}
			}
			// 登录操作
			Account loginAccount = accountService.login(account);
			if (loginAccount == null) {
				request.setAttribute("msg", "用户名或者密码错误!!!!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				//	登录成功
				//	记录登录履历
				LogLogin logLogin = new LogLogin();
				logLogin.setAccountId(Integer.parseInt(loginAccount.getAccountId()));
				//从Request中获取IP地址
				logLogin.setLogIp(request.getRemoteAddr());
				logLoginService.insert(logLogin);
				//	记住密码
				//	cookie保存在客户端的小文本，可以用来存储用户名密码，浏览记录相关信息
				Cookie cname = new Cookie("accountName", accountName);
				Cookie cpwd = new Cookie("accountPwd", accountPwd);
				//设置cookie的有效时间
				cname.setMaxAge(60);
				cpwd.setMaxAge(60);
				//将cookie添加到响应中
				response.addCookie(cname);
				response.addCookie(cpwd);
				//	获取真实名字
				String realName = loginAccount.getAccountRealName();
				//将数据保存到回话中
				HttpSession session=request.getSession();
				session.setAttribute("realName", realName);
				//存放权限
				session.setAttribute("account",loginAccount);

				//将登录统计的次数放置到session中
				/*try {
					session.setAttribute("countTimes", logLoginService.getCountTimes());
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}*/


				//全局获取上下文对象
				ServletContext servletContext = request.getServletContext();

				//获取上下文参数
				servletContext.getInitParameter("pageSize");

				//获取登录次数
				//Integer countTimes = (Integer) servletContext.getAttribute("countTimes");
				/*if (countTimes == null) {
				//	第一次获取
				//	查找数值并放到属性中
					try {
						servletContext.setAttribute("countTimes", logLoginService.getCountTimes());
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}else{
				//	如果查到了该属性，就直接在原有的数量上+1
					countTimes += 1;
					servletContext.setAttribute("countTimes", countTimes);
				}*/
				//countTimes += 1;
				//session.setAttribute("countTimes", countTimes);

				if (loginAccount.getRole().getRoleId() == 1) {
					//	管理员界面
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}/*else if (loginAccount.getRole().getRoleId() == 2){
					//    商城购买界面
				} else if (loginAccount.getRole().getRoleId() == 3) {
					//	卖家管理界面
				}*/ else {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		}

		if ("sure_update".equals(flag)) {
			String accountName1 = request.getParameter("accountName");
			String accountPwd1 = request.getParameter("accountPwd");
			String accountRealName = request.getParameter("accountRealName");
			String accountPhone = request.getParameter("accountPhone");
			String accountBirthday = request.getParameter("accountBirthday");
			String roleId = request.getParameter("roleId");
			String accountId = request.getParameter("accountId");
			Role role = new Role().roleId(Integer.parseInt(roleId));
			//获取当前登录的用户，即修改者
			HttpSession session = request.getSession();
			Account loginAccount = (Account) session.getAttribute("account");
			Account account1 = new Account()
					.accountName(accountName1)
					.accountPwd(accountPwd1)
					.accountRealName(accountRealName)
					.accountPhone(accountPhone)
					.accountBirthday(accountBirthday)
					.roleId(Integer.parseInt(roleId))
					.updateAuthor(loginAccount.getAccountId())
					.accountId(accountId);
			if (accountService.update(account1)) {
				request.setAttribute("msg", "修改成功");
				request.getRequestDispatcher("AccountServlet?flag=selectAll").forward(request, response);
			}else {
				request.setAttribute("msg", "修改失败");
				request.getRequestDispatcher("AccountServlet?flag=update").forward(request, response);
			}
		}

		if ("toInsert".equals(flag)) {
			request.setAttribute("roleList", roleService.selectAll());
			request.getRequestDispatcher("account_insert.jsp").forward(request, response);
		}
		if ("checkName".equals(flag)) {
		//	设置后台数据格式
		//	text/plain 文本
		//	text/html application/json json格式  text/xml

			String checkName = request.getParameter("accountName");
			String result = "ng";
			if (accountService.checkAccountName(checkName)) {
				result = "ok";
			} else {
				result = "ng";
			}
			PrintWriter writer = response.getWriter();
			writer.write(result);
			writer.close();
			/*
			* 回复xml格式内容*/
			/*response.setHeader("Context-type", "text/xml,charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write("<?xml version=\'1.0\'encoding=\'UTF-8\'?");
			writer.write("<response>");
			writer.write("<message>");
			writer.write(result);
			writer.write("<response>");
			writer.write("<message>");
			writer.close();*/
		}
		if ("add".equals(flag)) {
		//	当前管理员
			String manageName = String.valueOf(request.getSession().getAttribute("realName"));
			Account ac = new Account();
			ac.setAccountName(request.getParameter("accountName"));
			ac.setAccountPwd(request.getParameter("accountPwd"));
			ac.setAccountRealName(request.getParameter("accountRealName"));
			ac.setAccountPhone(request.getParameter("accountPhone"));
			ac.setAccountBirthday(request.getParameter("accountBirthday"));
			ac.setRoleId(Integer.parseInt(request.getParameter("roleId")));
			boolean add = accountService.add(ac, manageName);
			if (add) {
			//	添加成功
				request.setAttribute("msg", "添加成功");
				request.getRequestDispatcher("AccountServlet?flag=selectAll").forward(request, response);
			}
			else {
				request.setAttribute("msg", "添加失败");
				request.getRequestDispatcher("AccountServlet?flag=toInsert").forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
