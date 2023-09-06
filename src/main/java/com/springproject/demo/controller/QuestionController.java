package com.springproject.demo.controller;
import java.time.*;
import java.util.*;

import com.springproject.demo.dao.QuizDetailsRepo;
import com.springproject.demo.model.QuizDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.demo.dao.QuestionRepo;
import com.springproject.demo.dao.ResultRepo;
import com.springproject.demo.dao.StudentloginRepo;
import com.springproject.demo.model.Question;
import com.springproject.demo.model.Student;
import com.springproject.demo.model.StudentLogin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//******************************************************
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
@Controller
public class QuestionController {
	@Autowired
	QuestionRepo qrepo;
	@Autowired
	StudentloginRepo slrepo;
	@Autowired
	ResultRepo rrepo;
	@Autowired
	QuizDetailsRepo qdrepo;
	private int sizeoflist;
	@RequestMapping("/")
	public String home()
	{
		/*ModelAndView mv = new ModelAndView();
		Iterable<Question> q =  qrepo.findAll();
		for(Question q1: q)
		{
			System.out.println(q1.getQcontent());
		}
		mv.addObject("q",q);
		mv.setViewName("quest");*/
		
		return "studentregister";
	}
	@RequestMapping("/questions")
	public String questions()
	{
		return "questions";
	}
	@RequestMapping("/addquestion")
	public ModelAndView addquestion(HttpServletRequest req)
	{
		ModelAndView mv = new ModelAndView();
		Question q = new Question();
		q.setQno(Integer.parseInt(req.getParameter("qno")));
		q.setQcontent(req.getParameter("question"));
		q.setOp1(req.getParameter("op1"));
		q.setOp2(req.getParameter("op2"));
		q.setOp3(req.getParameter("op3"));
		q.setOp4(req.getParameter("op4"));
		q.setAns(Integer.parseInt(req.getParameter("ans")));
		qrepo.save(q);
     	mv.setViewName("questions");
		return mv;
	}
	
	@RequestMapping("/quest")
	public ModelAndView questions(HttpServletRequest req)
	{
		ModelAndView mv = new ModelAndView();
		//studentregister starts
		 String name;
		 String email;
		 String pass;
		StudentLogin os = new StudentLogin();
		name=req.getParameter("name");
		email=req.getParameter("email");
		pass=req.getParameter("pass");
		StudentLogin mail =  slrepo.findByEmail(email);
		StudentLogin nam =  slrepo.findBySname(name);
		boolean f= false;
		if(nam!=null || mail!=null) {
			mv.setViewName("studentregister");
			f=true;
			mv.addObject("mess",f);
			return mv;
		}
		else {
			os.setSname(name);
			os.setEmail(email);
			os.setPassword(pass);
			slrepo.save(os);
			mv.setViewName("studentregister");
		}		

		//studentregister ends
		//hemanth heroooo
		
//		List<Question> q =  (ArrayList<Question>) qrepo.findAll();
//		sizeoflist = q.size();
//		System.out.println(sizeoflist);
//		//jumbling starts
//		List<Question> q1 = new ArrayList<Question>();
//		Random rand = new Random();
//        while(q1.size()!=q.size())
//        {
//           int x = rand.nextInt(q.size());
//           Question y = q.get(x);
//           if(!q1.contains(y))
//           {
//               q1.add(y);
//           }
//        }
//        //jumbling ends
//		mv.addObject("q",q1);
//		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("/result")
	public ModelAndView result(HttpServletRequest request,HttpServletResponse response)
	{		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Http 1.1
		ModelAndView mv = new ModelAndView();
		Question q;
		int result=0;
		for(int i=1;i<=sizeoflist;i++)
		{
			q=qrepo.findById(i).orElse(new Question());
			if(request.getParameter(i+"")!=null)
			{
			if(q.getAns()==Integer.parseInt(request.getParameter(i+"")))
					{
						result++;
					}
			}
		}
		Student st = new Student();
//		st.setRollno(roll);
//		st.setResult(result);
//		st.setName(name);
//		st.setEmail(email);
//		st.setBranch(branch);
		rrepo.save(st);
		mv.addObject("r",result);
		mv.setViewName("result");
		return mv;
	}
	@RequestMapping("/leaderboard")
	public ModelAndView leaderboard(HttpServletResponse response)
	{
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Http 1.1
		ModelAndView mv = new ModelAndView();
		List<Student> re =  (ArrayList<Student>) rrepo.findAll();
		List<Integer> re1 = new ArrayList<Integer>();
		List<Student> re2 = new ArrayList<Student>();
		for(Student i:re)
		{
			re1.add(i.getResult());
		}
		Collections.sort(re1,Collections.reverseOrder()); 
		//System.out.println(re1.toString());
		for(Integer j:re1)
		{
			for(Student s:re)
			{
				if(s.getResult()==j)
				{
					if(!re2.contains(s))
					re2.add(s);
				}
			}
		}
		mv.addObject("res", re2);
		return mv;
	}
	@RequestMapping("/quizdetails")
	public ModelAndView quizdetails(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("quizdetails");
		return mv;
	}
	@RequestMapping("/quizdetailsstore")
	public ModelAndView quizdetailsstore(HttpServletRequest request,HttpServletResponse response){
		String mt=new String("POST");
		String st=request.getMethod();
		if(mt.equals(st)) {
			String des, duration, mon1, day1, hour1, min1, mon2, day2, hour2, min2, stime, etime;
			des="-";
			duration="0";
			des = request.getParameter("des");
			duration = request.getParameter("duration");
			mon1 = request.getParameter("month1");
			day1 = request.getParameter("day1");
			hour1 = request.getParameter("hour1");
			min1 = request.getParameter("min1");
			mon2 = request.getParameter("month2");
			day2 = request.getParameter("day2");
			hour2 = request.getParameter("hour2");
			min2 = request.getParameter("min2");
			QuizDetails qd = new QuizDetails();
			LocalDateTime date1, date2;
			ZonedDateTime currentDateTime = ZonedDateTime.now();
			LocalDateTime currentLocalDateTime = currentDateTime.toLocalDateTime();
			int yr = currentLocalDateTime.getYear();
			int mon=Integer.parseInt(mon1);
			int day=Integer.parseInt(day1);
			int hour=Integer.parseInt(hour1);
			int min=Integer.parseInt(min1);
			int sec=0;
			date1 = LocalDateTime.of(yr, mon, day, hour, min, sec);
			mon=Integer.parseInt(mon2);
			day=Integer.parseInt(day2);
			hour=Integer.parseInt(hour2);
			min=Integer.parseInt(min2);
			date2 = LocalDateTime.of(yr, mon, day, hour, min, sec);
			int returnVal = date1.compareTo(date2);
			if(returnVal>=0){
				ModelAndView mv=new ModelAndView();
				mv.setViewName("quizdetails");
				return mv;
			}
			stime = mon1 + "," + day1 + "," + hour1 + "," + min1;
			etime = mon2 + "," + day2 + "," + hour2 + "," + min2;
			List<QuizDetails> lst = (ArrayList<QuizDetails>) qdrepo.findAll();
			String n = String.valueOf(lst.size());
			StringBuilder qc = new StringBuilder();
			for (int i = 0; i < 8 - (n.length()); i++) {
				qc.append("0");
			}
			qc.append(n);
			String quizcode = qc.toString();
			qd.setDuration(duration);
			qd.setDescription(des);
			qd.setQuizcode(quizcode);
			qd.setEtime(etime);
			qd.setStime(stime);
			qdrepo.save(qd);
			ModelAndView mv = new ModelAndView();
			mv.setViewName("questions");
			return mv;
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("quizdetails");
		return mv;


	}

}
