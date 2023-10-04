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
	private int islogin=0;
	String name,uname;
	@RequestMapping("/login")
	public ModelAndView login1(HttpServletRequest request){
		String mt=new String("POST");
		String st=request.getMethod();
		if(mt.equals(st)){
			String name=request.getParameter("name");
			String pass=request.getParameter("password");
			StudentLogin nam =  slrepo.findBySname(name);
			String pas1=nam.getPassword();
			if(nam!=null) {
				if (pass.equals(pas1)) {
					islogin=1;
					uname=name;
					ModelAndView mv = new ModelAndView();
					System.out.println("sucess login **********");
					List<QuizDetails> quizdetails=qdrepo.findByUname(uname);
					List<Student> resdetails=rrepo.findByName(uname);
					mv.addObject("qd",quizdetails);
					mv.addObject("res",resdetails);
					for(QuizDetails i: quizdetails){
						System.out.println(i.getQuizcode());
					}
					mv.setViewName("mianpage");
					return mv;
				}
				else{
					ModelAndView mv = new ModelAndView();
					mv.setViewName("login");
					return mv;
				}
			}

		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
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
		
		return "studentregister.html";
	}
	@RequestMapping("/questions")
	public String questions()
	{
		return "questions";
	}
//	@RequestMapping("/addquestion")
//	public ModelAndView addquestion(HttpServletRequest req)
//	{
//		if(islogin==1){
//		ModelAndView mv = new ModelAndView();
//		Question q = new Question();
//		q.setQno(Integer.parseInt(req.getParameter("qno")));
//		q.setQcontent(req.getParameter("question"));
//		q.setOp1(req.getParameter("op1"));
//		q.setOp2(req.getParameter("op2"));
//		q.setOp3(req.getParameter("op3"));
//		q.setOp4(req.getParameter("op4"));
//		q.setAns(Integer.parseInt(req.getParameter("ans")));
//		qrepo.save(q);
//     	mv.setViewName("questions");
//		return mv;
//		}
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("login");
//		return mv;
//	}
	
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
	@RequestMapping("/storequestions")
	public ModelAndView storequestions(HttpServletRequest request){
		String qcode,noqs;
		qcode=request.getParameter("qcode");
		noqs=request.getParameter("noqs");
		int n=Integer.parseInt(noqs);
		for(int i=1;i<=n;i++){

			Question q = new Question();
			q.setQno(i);
			q.setQcontent(request.getParameter("qname"+i));
			q.setOp1(request.getParameter("op1"+i));
			q.setOp2(request.getParameter("op2"+i));
			q.setOp3(request.getParameter("op3"+i));
			q.setOp4(request.getParameter("op4"+i));
			q.setQuizcode(qcode);
			q.setAns(Integer.parseInt(request.getParameter("ans"+i)));
			qrepo.save(q);
		}
		ModelAndView mv=new ModelAndView();

		List<QuizDetails> quizdetails=qdrepo.findByUname(uname);
		List<Student> resdetails=rrepo.findByName(uname);
		mv.addObject("qd",quizdetails);
		mv.addObject("res",resdetails);
		for(QuizDetails i: quizdetails){
			System.out.println(i.getQuizcode());
		}
		mv.setViewName("mianpage");
		return mv;
	}
	
	
	@RequestMapping("/quizdetails")
	public ModelAndView quizdetails(HttpServletRequest request){
		if(islogin==1) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("quizdetails");
			return mv;
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;

	}
	@RequestMapping("/quizdetailsstore")
	public ModelAndView quizdetailsstore(HttpServletRequest request,HttpServletResponse response){
		if(islogin==1){
		String mt=new String("POST");
		String st=request.getMethod();
		if(mt.equals(st)) {
			String des, duration, mon1, day1, hour1, min1, mon2, day2, hour2, min2, stime, etime,noqs,noqspp;
			des="-";
			duration="0";
			des = request.getParameter("des");
			noqs=request.getParameter("noqs");
			noqspp=request.getParameter("noqspp");
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
			qd.setNoqs(noqs);
			qd.setUname(uname);
			qd.setNoqspp(noqspp);
			qdrepo.save(qd);
			ModelAndView mv = new ModelAndView();
			int no=Integer.parseInt(noqs);
			int[] arr = new int[no];
			for(int i=0;i<no;i++){
				arr[i]=i+1;
			}
			mv.addObject("arr",arr);
			mv.addObject("qcode",quizcode);
			mv.addObject("noqs",no);
			mv.setViewName("questions");
			return mv;
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("quizdetails");
		return mv;
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;


	}
	@RequestMapping("enterquizcode")
	public ModelAndView enterquizcode(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("enterquizcode");
		return mv;
	}
	@RequestMapping("/getquizcode")
	public ModelAndView getquizcode(HttpServletRequest request) {

		String qcode = request.getParameter("qcode");
		ModelAndView mv = new ModelAndView();
		List<Student> students = rrepo.findByName(uname);
		for (Student i : students) {
			if (i.getQuizcode().equals(qcode)) {
				System.out.println(" ----------#############");
				mv.addObject("mess", "you have already attempted the exam");
				mv.setViewName("enterquizcode");
				return mv;
			}
		}
			System.out.println(" ----------#############");
			ZonedDateTime currentDateTime = ZonedDateTime.now();
			LocalDateTime currentLocalDateTime = currentDateTime.toLocalDateTime();
//			System.out.println("Using the current date and time as input");

			// Use the current year, month, day, hour, minute, and second for date2
			LocalDateTime date1, date2, date;
			int yr = currentLocalDateTime.getYear();
			int month = currentLocalDateTime.getMonthValue();
			int day = currentLocalDateTime.getDayOfMonth();
			int hour = currentLocalDateTime.getHour();
			int min = currentLocalDateTime.getMinute();
			int sec = currentLocalDateTime.getSecond();
			date = LocalDateTime.of(yr, month, day, hour, min, sec);
			QuizDetails ls = qdrepo.findByQuizcode(qcode);
			String[] s1 = ls.getStime().split(",");
			String[] s2 = ls.getEtime().split(",");
			date1 = LocalDateTime.of(yr, Integer.parseInt(s1[0]), Integer.parseInt(s1[1]), Integer.parseInt(s1[2]), Integer.parseInt(s1[3]), 0);
			date2 = LocalDateTime.of(yr, Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), Integer.parseInt(s2[2]), Integer.parseInt(s2[3]), 0);
			int r1 = date.compareTo(date1);
			int r2 = date.compareTo(date2);
			System.out.println(r1 + " r1 r2  " + r2);
			if ((r1>=0)&&(r2<=0)) {



				List<Question> lst = (ArrayList<Question>) qrepo.findByQuizcode(qcode);
				int num=Integer.parseInt(ls.getNoqspp());
				Collections.shuffle(lst);
				List<Question> rlst = lst.subList(0, num);
				Set<Question> set = new HashSet<Question>(rlst);
				String qsno=new String();
				for(Question i:rlst){
					qsno+=i.getQno()+",";
				}
				//System.out.println(lst);
				System.out.println("****************************");

				mv.addObject("qcode", qcode);
				mv.addObject("q", set);
				mv.addObject("qsno",qsno);
				mv.addObject("duration",ls.getDuration());
				mv.setViewName("exam");
				return mv;
			}
			else{
				mv.addObject("mess", "time out or quiz not yet started.");
				mv.setViewName("enterquizcode");
				return mv;
			}

	}
	@RequestMapping("/result")
	public ModelAndView result(HttpServletRequest request,HttpServletResponse response)
	{	if(islogin==1){
		System.out.println("form submitted succesfully****************");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Http 1.1
		ModelAndView mv = new ModelAndView();
		String qcode=request.getParameter("qcode");
		String qsno=request.getParameter("qsno");
		List<Question> ans = qrepo.findByQuizcode(qcode);
		int result=0;
		for(int i=1;i<=ans.size();i++)
		{
			Question q = ans.get(i-1);
			if(request.getParameter(""+i)!=null)
			{
			if(q.getAns()==Integer.parseInt(request.getParameter(""+i)))
					{
						result++;
					}
			}
		}
		Student st = new Student();
		st.setResult(result);
		st.setQuizcode(qcode);
		st.setName(uname);
		st.setQsno(qsno);
		rrepo.save(st);
		mv.addObject("r",result);
		mv.addObject("qcode",qcode);
		mv.setViewName("result");
		return mv;
	}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping("viewanswers")
	public ModelAndView viewanswers(HttpServletResponse response,HttpServletRequest request) {
		if(islogin==1){
			String qcode=request.getParameter("qcode");
			Student s=rrepo.findByQuizcodeAndName(qcode,uname);
			List<Question> lst = (ArrayList<Question>) qrepo.findByQuizcode(qcode);

			String qsno1=s.getQsno();
			String qsno2=qsno1.substring(0,qsno1.length()-1);
			String qsno[]=qsno2.split(",");
			List<List<String>> ans = new ArrayList<>();
			int c=1;
			for(Question i:lst){
				for(String j:qsno){
					if(i.getQno()==Integer.parseInt(j)){

						List<String> a=new ArrayList<>();
						a.add(c+" ");
						c++;
						a.add(i.getQcontent());
						a.add(i.getOp1());
						a.add(i.getOp2());
						a.add(i.getOp3());
						a.add(i.getOp4());
						int ano=i.getAns();
						if(ano==1){
							a.add(i.getOp1());
						}
						else if(ano==2){
							a.add(i.getOp2());
						}
						else if(ano==3){
							a.add(i.getOp3());
						}
						else{
							a.add(i.getOp4());
						}

						ans.add(a);


//						a.add(c+". "+i.getQcontent());
//						int ano=i.getAns();
//						if(ano==1){
//							a.add(i.getOp1());
//						}
//						else if(ano==2){
//							a.add(i.getOp2());
//						}
//						else if(ano==3){
//							a.add(i.getOp3());
//						}
//						else{
//							a.add(i.getOp4());
//						}
//						c++;
//						ans.add(a);

					}
				}
			}
//			System.out.println(c+"*************************************************");
//			for(List<String> i1:ans){
//				for(String i2: i1){
//					System.out.print(i2+" ");
//				}
//				System.out.println();
//			}
			ModelAndView mv = new ModelAndView();
			mv.addObject("ans",ans);
			mv.setViewName("showanswer");
			return mv;

		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;

	}
	@RequestMapping("/leaderboard")
	public ModelAndView leaderboard(HttpServletResponse response,HttpServletRequest request)
	{
		if(islogin==1){
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Http 1.1
		ModelAndView mv = new ModelAndView();
		String qcode=request.getParameter("qcode");
		List<Student> re =  rrepo.findByQuizcode(qcode);
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
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
}
