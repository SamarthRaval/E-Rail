package com.erail.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String index(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null){
			return "homepage";
		}else{
			return	"redirect:/login";
		}
	}
	
	//Dhruvi
	@RequestMapping("/trains")
	public String trains (@RequestParam("source") String sourceStation,@RequestParam("destination") String destinationStation,
			 HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null) {
			return "trains";
		}else{
			return	"redirect:/login";
		}
	}

	@RequestMapping("/payment")
	public String payment(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null) {
			return "payment";
		}else{
			return	"redirect:/login";
		}
	}



	@RequestMapping("/booking")
	public String booking(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null) {
			return "booking";
		}else{
			return	"redirect:/login";
		}
	}


	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && session.getAttribute("role").toString().equals("1")) {
			return "dashboard";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}


	//Dhruvi
	@RequestMapping("/adminPanel")
	public String adminPage (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			return "adminPage";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null) {
			return	"redirect:/";
		}else{
			return "login";
		}
	}


	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}

	@RequestMapping("/userpage")
	public String userpage() {
		return "userpage";
	}
	
	@RequestMapping("/forgetuserid")
	public String forgetuserid() {
		return "forgetuserid";
	}
	
	@RequestMapping("/forgetpassword")
	public String forgetpassword() {
		return "forgetpassword";
	}
	
	@RequestMapping("/userregistration")
	public String userregistration() {
		return "userregistration";
	}
	
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/trainStationMapping")
	public String trainStationMapping(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "train_station";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}

	@RequestMapping("/resetPassword")
	public String resetPassword() {
		return "resetPassword";
	}

	//Dhruvi
	@RequestMapping("/adminTrainMgmt")
	public String adminTrainOperations (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "adminTrainOperations";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}
		
	//Dhruvi
	@RequestMapping("/adminStationMgmt")
	public String adminStationOperations (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "adminStationOperations";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}
	
	//Dhruvi
	@RequestMapping("/adminUserMgmt")
	public String adminUserOperations (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "adminUserOperations";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}
	
	//Dhruvi
	@RequestMapping("/report")
	public String report (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "reports";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}

	//Varun
	@RequestMapping("/classMgmt")
	public String classManagement (HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString())==1) {
			return "classManagement";
		}else if(session.getAttribute("name")!= null && Integer.parseInt(session.getAttribute("role").toString()) == 0){
			return	"redirect:/unauthorized";
		}
		else{
			return	"redirect:/login";
		}
	}

	
}
