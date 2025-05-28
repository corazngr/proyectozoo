package tecnm.com.zoo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
//
//	@Autowired
//	private UsuariosService usuariosService;
//
//	@PostMapping("/login")
//	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
//			HttpSession session, Model model) {
//
//		Usuarios usuario = usuariosService.buscarPorUsername(username);
//
//		if (usuario != null && usuariosService.verificarPassword(password, usuario.getPassword())) {
//			// Usuario y contraseña correctos
//			session.setAttribute("usuarioLogueado", usuario);
//
//			if ("Administrador".equalsIgnoreCase(usuario.getRol())) {
//				return "redirect:/admin/inicioadmin";
//			} else if ("Cuidador".equalsIgnoreCase(usuario.getRol())) {
//				return "redirect:/cuidadores/iniciocuidador";
//			} else {
//				return "redirect:/inicio";
//			}
//		} else {
//			model.addAttribute("error", true);
//			return "login";
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/login?logout";
//	}
}
