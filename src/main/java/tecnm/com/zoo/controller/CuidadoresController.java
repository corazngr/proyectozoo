package tecnm.com.zoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tecnm.com.zoo.model.Alimentacion;
import tecnm.com.zoo.model.Animales;
import tecnm.com.zoo.model.Cuidadores;
import tecnm.com.zoo.model.Especies;
import tecnm.com.zoo.model.Habitats;
import tecnm.com.zoo.model.Salud;
import tecnm.com.zoo.services.AlimentacionService;
import tecnm.com.zoo.services.AnimalesService;
import tecnm.com.zoo.services.CuidadoresService;
import tecnm.com.zoo.services.EspeciesService;
import tecnm.com.zoo.services.HabitatsService;
import tecnm.com.zoo.services.SaludService;

@Controller
@RequestMapping("/cuidadores")
public class CuidadoresController {

	@Autowired
	private AnimalesService serviceAnimales;

	@Autowired
	private EspeciesService serviceEspecies;

	@Autowired
	private HabitatsService serviceHabitats;

	@Autowired
	private AlimentacionService serviceAlimentacion;

	@Autowired
	private CuidadoresService serviceCuidadores;

	@Autowired
	private SaludService serviceSalud;

	@GetMapping("/iniciocuidador")
	public String mostrarInicio(Model model) {
		return "cuidador/iniciocuidador";
	}

	@GetMapping("/animalescuidador")
	public String mostrarFormulario(Model model) {
		List<Animales> listaAnimales = serviceAnimales.buscarAnimales();
		model.addAttribute("animales", listaAnimales);
		model.addAttribute("especies", serviceEspecies.obtenerTodas());
		model.addAttribute("habitats", serviceHabitats.buscarHabitats());
		model.addAttribute("cuidadores", serviceCuidadores.buscarCuidadores());
		model.addAttribute("animales", new Animales());

		return "cuidador/animalescuidador";
	}

	@GetMapping("/alimentacioncuidador")
	public String gestionAlimentacion(Model model) {
		model.addAttribute("alimentacion", new Alimentacion());
		model.addAttribute("alimentaciones", serviceAlimentacion.buscarAlimentacion());
		model.addAttribute("animales", serviceAnimales.buscarAnimales());
		return "cuidador/alimentacioncuidador";
	}

	@GetMapping("/editaranimalescuidador")
	public String gestionAnimales(Model model) {
		return "cuidador/editaranimalescuidador";
	}

	@GetMapping("/especiescuidador")
	public String gestionEspecies(Model model) {
		List<Especies> listaEspecies = serviceEspecies.buscarEspecies();
		model.addAttribute("especies", listaEspecies);
		return "/cuidador/especiescuidador";
	}

	@GetMapping("/habitatscuidador")
	public String gestionHabitats(Model model) {
		List<Habitats> listaHabitats = serviceHabitats.buscarHabitats();
		model.addAttribute("habitats", listaHabitats);
		return "/cuidador/habitatscuidador";
	}

	@GetMapping("/saludcuidador")
	public String gestionSalud(Model model) {
		List<Salud> listaSalud = serviceSalud.buscarSalud();
		model.addAttribute("salud", listaSalud);
		List<Animales> listaAnimales = serviceAnimales.buscarAnimales();
		List<Cuidadores> listaCuidadores = serviceCuidadores.buscarCuidadores();

		model.addAttribute("saludForm", new Salud());
		model.addAttribute("animales", listaAnimales);
		model.addAttribute("cuidadores", listaCuidadores);
		return "/cuidador/saludcuidador";
	}

	@PostMapping("/agregaranimal")
	public String guardarAnimal(Animales animales, BindingResult result, RedirectAttributes atributos) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "/cuidador/animalescuidador";
		}
		serviceAnimales.guardarAnimal(animales);
		atributos.addFlashAttribute("msg", "Animal guardado correctamente. 😊");
		System.out.println("Nuevo animal agregado: " + animales);
		return "redirect:iniciocuidador";
	}

	@PostMapping("/agregarespecie")
	public String guardarEspecie(Especies especies, BindingResult result, RedirectAttributes atributos) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "/cuidador/especiescuidador";
		}
		serviceEspecies.guardarEspecie(especies);
		atributos.addFlashAttribute("msg", "Especie guardada correctamente. 😊");
		System.out.println("Nueva especie agregada: " + especies);
		return "redirect:iniciocuidador";
	}

	@PostMapping("/agregarhabitat")
	public String guardarHabitat(@ModelAttribute Habitats habitats, BindingResult result, RedirectAttributes atributos,
			@RequestParam String accion) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "/cuidador/habitatscuidador";
		}

		switch (accion) {
		case "agregar":
			serviceHabitats.guardarHabitat(habitats);
			atributos.addFlashAttribute("msg", "Hábitat agregada correctamente. 😊");
			System.out.println("Nueva hábitat agregada: " + habitats);
			break;
		case "modificar":
			serviceHabitats.guardarHabitat(habitats);
			atributos.addFlashAttribute("msg", "Hábitat modificada correctamente. 😊");
			System.out.println("Hábitat modificada: " + habitats);
			break;
		default:
			atributos.addFlashAttribute("msg", "Acción no reconocida.");
			break;
		}

		return "redirect:iniciocuidador";
	}

	@PostMapping("/agregaralimentacion")
	public String guardarAlimentacion(Alimentacion alimentacion, BindingResult result, RedirectAttributes atributos) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "/cuidador/alimentacioncuidador";
		}
		serviceAlimentacion.guardarAlimentacion(alimentacion);
		atributos.addFlashAttribute("msg", "Alimentación guardada correctamente. 😊");
		System.out.println("Nueva alimentación agregada: " + alimentacion);
		return "redirect:/cuidador/iniciocuidador";
	}

	@GetMapping("/eliminarhabitat/{id}")
	public String eliminarHabitat(@PathVariable("id") Integer id_habitat, RedirectAttributes atributos) {
		serviceHabitats.eliminarHabitat(id_habitat);
		atributos.addFlashAttribute("msg", "Hábitat eliminada correctamente. 😊");
		return "/cuidador/iniciocuidador";
	}

	@GetMapping("/eliminaralimentacion/{id}")
	public String eliminarAlimentacion(@PathVariable("id") Integer id_alimentacion, RedirectAttributes atributos) {
		serviceAlimentacion.eliminarAlimentacion(id_alimentacion);
		atributos.addFlashAttribute("msg", "Alimentación eliminada correctamente. 😊");
		return "/cuidador/iniciocuidador";
	}

	@GetMapping("/eliminarespecie/{id}")
	public String eliminarEspecie(@PathVariable("id") Integer id_especie, RedirectAttributes atributos) {
		serviceEspecies.eliminarEspecie(id_especie);
		atributos.addFlashAttribute("msg", "Especie eliminada correctamente. 😊");
		return "/cuidador/iniciocuidador";
	}
}
