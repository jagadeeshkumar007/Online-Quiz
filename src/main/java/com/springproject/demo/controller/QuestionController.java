package com.springproject.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class QuestionController {
	@Autowired
	QuestionRepo qrepo;
	@Autowired
	StudentloginRepo slrepo;
	@Autowired
	ResultRepo rrepo;
	private String roll;
	private String name;
	private String email;
	private String branch;
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
		
		return "studentlogin";
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
		//studentlogin starts
		StudentLogin os = new StudentLogin();
		roll=req.getParameter("rollno");
		name=req.getParameter("name");
		email=req.getParameter("email");
		branch=req.getParameter("branch");
		os.setSname(name);
		os.setEmail(email);
		os.setRollno(roll);
		os.setBranchsec(branch);
		slrepo.save(os);
		//studentlogin ends
		ModelAndView mv = new ModelAndView();
		List<Question> q =  (ArrayList<Question>) qrepo.findAll();
		sizeoflist = q.size();
		System.out.println(sizeoflist);
		//jumbling starts
		List<Question> q1 = new ArrayList<Question>();
		Random rand = new Random();
        while(q1.size()!=q.size())
        {
           int x = rand.nextInt(q.size());
           Question y = q.get(x);
           if(!q1.contains(y))
           {
               q1.add(y);
           }
        }
        //jumbling ends
		mv.addObject("q",q1);
		mv.setViewName("home");
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
		st.setRollno(roll);
		st.setResult(result);
		st.setName(name);
		st.setEmail(email);
		st.setBranch(branch);
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

}
