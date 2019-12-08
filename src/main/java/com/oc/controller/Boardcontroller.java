package com.oc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.service.BoardService;
import com.oc.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class Boardcontroller {

	private static final Logger logger = LoggerFactory.getLogger(Boardcontroller.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("regis post .....");
		logger.info(board.toString());
		
		//service.regist(board);
		
		//model.addAttribute("result", "success");
		rttr.addFlashAttribute("msg", "SUCCESS");
		rttr.addFlashAttribute("paging", 0);
		
		//return "/board/success";
		//return "redirect:/board/listAll";
		return "redirect:/board/moreListAjax";
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list...............");
		
		model.addAttribute("list", service.listAll());
	}
	
	
	@RequestMapping(value = "/moreListAjax", method = RequestMethod.POST)
	public @ResponseBody List<BoardVO>  moreListAjax(@RequestParam("paging") int paging, HttpServletRequest req) throws Exception {
		System.out.println(paging);
		
		
		List<BoardVO> board = service.pagingList(paging);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", board);
		
		return board;
	}
}
