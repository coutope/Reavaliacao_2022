package br.edu.infnet.tp3aws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.infnet.tp3aws.dtos.EmailDto;
import br.edu.infnet.tp3aws.dtos.UsuarioDto;
import br.edu.infnet.tp3aws.models.Usuario;
import br.edu.infnet.tp3aws.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/")
	public String index(Model model)
	{
		EmailDto emailDto = new EmailDto();
		model.addAttribute("emailDto",emailDto);
		return "index";
	}
	
	@GetMapping("/usuario/detalhe")
	public String buscarPorEmail(@ModelAttribute("email")EmailDto emailDto, Model model, BindingResult result)
	{
		Usuario usuario = usuarioService.buscarUsuario(emailDto.email);
		
		model.addAttribute("usuario", usuario);
		return "detalhe";
	}
	
	@GetMapping("/usuario/editar")
	public String atualizar(@ModelAttribute("email")EmailDto emailDto, Model model, BindingResult result)
	{
		UsuarioDto dados = new UsuarioDto();
		Usuario usuario = usuarioService.buscarUsuario(emailDto.email);
		
		model.addAttribute("dados", dados);
		model.addAttribute("usuario", usuario);
		return "atualizar";
	}
	
	@GetMapping("/usuario/cadastrar")
	public String criar(Model model)
	{		
		model.addAttribute("usuario",new UsuarioDto());
		return "cadastro";
	}
	
	
	@PostMapping("/usuario")
	public String criarUsuario(@ModelAttribute("usuario")UsuarioDto usuario, BindingResult bindingResult, Model model)
	{
		usuarioService.criarUsuario(usuario);
		
		EmailDto emailDto = new EmailDto();
		model.addAttribute("emailDto",emailDto);
		return "index";
	}
	
	@PostMapping("/usuario/editar")
	public String atualizarUsuario(@ModelAttribute("usuario")UsuarioDto usuario, BindingResult bindingResult, Model model)
	{
		usuarioService.atualizarUsuario(usuario);
		
		EmailDto emailDto = new EmailDto();
		model.addAttribute("emailDto",emailDto);
		return "index";
	}
	
}
