/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;


import javax.ws.rs.GET;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/WebServer")
public class ControllerMVC {

	@RequestMapping(value = "/{value}", method = RequestMethod.GET)
	public String getValue(@PathVariable("value") String valor, Model model){
            System.out.println("valor: "+valor);
            model.addAttribute("value", valor);
            return "WebServer/hola";      
        }
        
         @RequestMapping(value = "/bro", method = RequestMethod.GET)
        public String showHome(Model model) {
            model.addAttribute("message", "This is my first MVC page");
            return "home";
        }
        
        @RequestMapping(method = RequestMethod.GET)
        public String home(){
            return "home";
        }
       

}
