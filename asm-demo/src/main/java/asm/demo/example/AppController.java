package asm.demo.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private PostService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Post> listPosts = service.listAll();
		model.addAttribute("listPosts", listPosts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewPostForm(Model model) {
	    Post post = new Post();
	    model.addAttribute("posts", post);
	     
	    return "new_post";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("posts") Post posts) {
	    service.save(posts);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{idpost}")
	public ModelAndView showEditPostPage(@PathVariable(name = "idpost") Long idpost) {
	    ModelAndView mav = new ModelAndView("edit_post");
	    Post post = service.get(idpost);
	    mav.addObject("post", post);
	     
	    return mav;
	}
	@RequestMapping("/delete/{idpost}")
	public String deletePost(@PathVariable(name = "idpost") Long idpost) {
	    service.delete(idpost);
	    return "redirect:/";       
	}
	
	@GetMapping("/403")
	public String erro403() {
		return "403";
	}
	

}
